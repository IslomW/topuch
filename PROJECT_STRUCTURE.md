# TopUch: карта проекта

> Актуально на 2026-09-07. Этот файл фиксирует фактическое состояние репозитория и служит контекстом для дальнейшей разработки. После существенных изменений архитектуры его следует обновлять.

## 1. Назначение

TopUch — backend для сервиса объявлений/маркетплейса. Реализуются профили пользователей, объявления, категории, лайки и поиск объявлений через Elasticsearch. В коде также заложены, но пока не закончены, жалобы и загрузка изображений в S3.

## 2. Технологический стек (по фактическому `pom.xml`)

- Java 26
- Spring Boot 4.1.1
- Spring Web
- Spring Data JPA + PostgreSQL
- Liquibase 5.0.3 (через Spring Boot dependency management)
- Spring Data Elasticsearch
- MapStruct 1.5.5.Final
- Lombok 1.18.46
- JUnit 5 / Spring Boot Test
- Maven Wrapper 3.9.9

README дополнительно заявляет Redis, Kafka, MailSender, Telegram Bot API, Keycloak, Spring Security, Prometheus, Grafana и Swagger, но соответствующих зависимостей и реализаций в текущем коде нет.

## 3. Структура каталогов

```text
topuch/
├── pom.xml                         # Maven-конфигурация и зависимости
├── mvnw, mvnw.cmd                  # Maven Wrapper
├── README.md                       # исходное описание проекта (частично устарело)
├── PROJECT_STRUCTURE.md            # текущая карта проекта
├── docs/                           # изображения архитектуры и ER-диаграмм
└── src/
    ├── main/
    │   ├── java/com/sharipov/topuch/
    │   │   ├── TopuchApplication.java
    │   │   ├── common/
    │   │   │   ├── exception/      # ApiException, status exceptions и global handler
    │   │   │   └── response/       # единый формат API-ошибок
    │   │   ├── application/
    │   │   │   ├── converter/      # MapStruct-мапперы Entity <-> DTO
    │   │   │   └── dto/            # request/response DTO и общие DTO
    │   │   ├── config/              # заготовки конфигурации S3
    │   │   ├── domain/
    │   │   │   ├── document/       # Elasticsearch-документы
    │   │   │   ├── entity/         # JPA-сущности и enum
    │   │   │   ├── exception/      # domain-исключения NotFound
    │   │   │   ├── repository/     # JPA/Elasticsearch repositories
    │   │   │   └── service/        # интерфейсы и реализации сервисов
    │   │   └── web/controller/      # REST API
    │   └── resources/
    │       ├── application.yml      # PostgreSQL, Liquibase и Elasticsearch
    │       ├── db/changelog/        # master и YAML-миграции releases/YYYY.MM
    │       └── profile.http         # ручные HTTP-запросы
    └── test/java/com/sharipov/topuch/
        └── TopuchApplicationTests.java
```

## 4. Слои и поток данных

```text
HTTP request
  -> web/controller
  -> application/converter (DTO -> Entity)
  -> domain/service
  -> domain/repository
  -> PostgreSQL или Elasticsearch
  -> converter (Entity -> DTO)
  -> HTTP response
```

- `web` отвечает за HTTP-маршруты.
- `application` содержит контракты API (DTO) и преобразования.
- `domain` содержит модель, бизнес-сервисы и доступ к данным.
- `common/exception` централизованно преобразует API, validation и системные исключения в единый JSON-контракт.
- Строгой чистой/hexagonal-архитектуры нет: репозитории и Spring-аннотации находятся внутри `domain`.

## 5. Доменная модель

### PostgreSQL/JPA

- Все primary key (`Profile`, `Address`, `Post`, `Image`, `Category`, `Report`) имеют тип `java.util.UUID` и генерируются как UUIDv7 через встроенную стратегию Hibernate `UuidVersion7Strategy` (RFC 9562).
- `Post.seller` является обязательной JPA-связью `@ManyToOne` с `Profile`; `Category.parentId` пока остаётся скалярным UUID.
- `Profile`: пользователь; имеет `Address` через `@OneToOne` и серверный `trustFactor` от 0 до 10 для отображения доверия к продавцу. Новый профиль начинает с нейтрального значения 5, клиент не может менять рейтинг через `ProfileRequestDTO`.
- `Address`: адрес пользователя.
- `Post`: объявление; содержит title, description, price, createdAt, condition, обязательного продавца `seller`, категорию, изображения и лайки. API и Elasticsearch возвращают ID продавца и его `trustFactor`.
- `Image`: изображение объявления; `@ManyToOne` к `Post`.
- `Category`: категория или подкатегория; иерархия задана простым полем `parentId`, а не JPA-связью.
- `Report`: жалоба на объявление от профиля; модель существует, но рабочий сервис/repository/controller отсутствуют.
- `Condition`: `NEW`, `GOOD`, `ACCEPTABLE`.
- `Abuse`: `CLICKBAIT`, `SCAM`, `SPAM`.
- `Status`: пустой enum, пока не используется.

### Elasticsearch

- `PostDocument`, индекс `posts`.
- Поля: postId, title, description, price, createdAt, introductionImageUrl, condition, categoryName, subcategoryName.
- Поиск реализован через `ElasticsearchOperations`; также существует `PostDocumentRepository`.
- Автоматическая синхронизация изменений `Post` из PostgreSQL с индексом Elasticsearch не реализована.

## 6. REST API (фактические маршруты)

### Profiles — `/api/users`

- `GET /api/users` — список профилей.
- `GET /api/users/{id}` — профиль по ID.
- `POST /api/users` — создать профиль.
- `PUT /api/users/{id}` — обновить профиль.
- `DELETE /api/users/{id}` — удалить профиль.

### Posts — `/api/posts`

- `GET /api/posts` — список объявлений.
- `GET /api/posts/{id}` — объявление по ID.
- `POST /api/posts` — создать объявление.
- `PUT /api/posts/{id}` — обновить объявление.
- `DELETE /api/posts/{id}` — удалить объявление.
- `POST /api/posts/{postId}/like?userId={id}` — переключить лайк.
- `GET /api/posts/{postId}/likes` — количество лайков.

### Categories — `/api/categories`

- `GET /api/categories` — только корневые категории (`parentId IS NULL`).
- `GET /api/categories/{parentId}/subcategories` — подкатегории.
- `POST /api/categories` — создать категорию.
- `PUT /api/categories/{id}` — обновить категорию.
- `DELETE /api/categories/{id}` — удалить категорию.

Метод `CategoryService.createSubcategory(...)` есть, но отдельного endpoint для него нет.

### Search — `/api/posts/search`

- `GET /by-keyword?keyword=&page=0&size=10`
- `GET /by-category?category=&page=0&size=10`
- `GET /by-subcategory?subcategory=&page=0&size=10`
- `GET /price?min=&max=&page=0&size=10`
- `GET /price/sort?keyword=&ascending=&page=0&size=10`
- `GET /highlight?keyword=&page=0&size=10` — endpoint есть, реализация возвращает `null`.
- `GET /all?page=0&size=10`
- `GET /count?keyword=`

## 7. Конфигурация и локальный запуск

`application.yml` ожидает:

- PostgreSQL: `DB_URL` (default `jdbc:postgresql://localhost:5432/top3`), `DB_USERNAME` (default `postgres`) и обязательный секрет `DB_PASSWORD`.
- Elasticsearch: `ELASTICSEARCH_URIS` (default `http://localhost:9200`).
- Hibernate: `ddl-auto: validate`
- Liquibase master: `classpath:/db/changelog/db.changelog-master.yaml`

Команда запуска с JDK 26:

```bash
./mvnw spring-boot:run
```

Liquibase запускает миграции автоматически перед инициализацией JPA. Master через `includeAll` загружает последовательные immutable YAML changesets из календарных каталогов `releases/YYYY.MM`. Начальная серия миграций создаёт таблицы и foreign keys, затем check constraints и индексы текущей модели. Правила дальнейших миграций находятся в `db/changelog/README.md`. Docker Compose и профили окружений отсутствуют.

Начальная миграция рассчитана на пустую базу. Для старой схемы с числовыми ID нужна отдельная legacy-миграция с новыми UUID-колонками и переносом внешних ключей.

## 8. Текущее состояние сборки и тестов

- Git-ветка: `main`, до создания этого файла рабочее дерево было чистым.
- Есть загрузка Spring context, offline-проверка Liquibase и unit-тесты global exception handler.
- Команда проверки: `./mvnw test`.
- Проект обновлён до Java 26, Spring Boot 4.1.1 и Lombok 1.18.46.
- Lombok обновлён, поскольку поддержка JDK 26 появилась только в ветке 1.18.46+.
- Исходный код успешно компилируется с `--release 26`.
- `contextLoads` доходит до инициализации Spring, но без локального PostgreSQL падает с `Connection to localhost:5432 refused`.
- Интеграционный `contextLoads` после успешной компиляции потребует доступных PostgreSQL и Elasticsearch либо отдельной test-конфигурации/mocks.

## 9. Известные проблемы и технический долг

### Критичные

1. Пароль PostgreSQL вынесен в обязательную переменную `DB_PASSWORD`; старый ранее закоммиченный пароль нужно сменить, если он где-либо реально использовался.
2. `StorageService.java` ранее имел неверный package и отсутствующий import; это исправлено при переходе на Java 26.
3. Для существующих таблиц с `BIGINT` ID ещё не создана миграция на PostgreSQL `uuid`.

### Функциональные пробелы

1. S3-классы, request/response DTO и controller — пустые заготовки; AWS SDK в зависимостях отсутствует.
2. `ReportService` и `ReportMapper` имеют только интерфейсы; repository, implementation и controller отсутствуют. `ReportMapper` не помечен `@Mapper`.
3. `PostDocumentService`/`PostDocumentServiceImpl` пустые; синхронизации PostgreSQL -> Elasticsearch нет.
4. `searchWithHighlighting` возвращает `null`.
5. Аутентификация, авторизация и валидация request DTO отсутствуют, несмотря на описание в README.
6. Нет Swagger/OpenAPI, Redis, Kafka, observability и других заявленных в README интеграций.

### Модель данных и API

1. `Post.seller` — обязательная LAZY-связь `@ManyToOne`; миграция останавливается, если в старой базе есть объявления без продавца.
2. Колонка таблицы лайков стандартизирована как `profile_id` в entity и initial migration.
3. Двусторонняя связь `Post.images`/`Image.post` не настраивается в сервисе при создании объявления.
4. DTO создания `PostRequestDTO` содержит `profileId`, но пока не содержит category.
5. `PostResponseDTO` содержит `postId` и краткую информацию о продавце с `trustFactor`.
6. `CategoryDTO` содержит только `name`: ID и `parentId` теряются в ответах и обновлениях.
7. `Report` не содержит getters/setters, что осложнит JPA/маппинг/API.
8. `Report.postId` и `Report.profileId` названы как ID, но имеют типы `Post` и `Profile`.
9. `Report.postId` задан как `@OneToOne`; обычно одно объявление может иметь несколько жалоб, поэтому вероятнее нужна связь `@ManyToOne`.
10. Для `page`, `size`, диапазона цен и остальных входов нет валидации.

### Качество и сопровождение

1. Нет unit-тестов сервисов, controller-тестов и repository/integration-тестов.
2. Начальная Liquibase-миграция есть, но миграция существующих legacy-таблиц с `BIGINT` на `UUID` ещё не реализована.
3. Есть неиспользуемые imports, logger-поля и закомментированный код.
4. Сообщение `CategoryNotFound` ошибочно говорит `Post with Id`.
5. README содержит несуществующие endpoints, технологии и ссылку на отсутствующий `docs/sequence.png`.

## 10. Рекомендуемый порядок дальнейших работ

1. Поддерживать сборку на JDK 26 и совместимые версии Spring Boot/Lombok/MapStruct.
2. Сменить ранее закоммиченный пароль PostgreSQL и добавить изолированный `application-test.yml`.
3. Продолжать улучшать исключения и global exception handler.
4. Уточнить оставшуюся доменную модель (категории и reports) и сопровождать изменения новыми Liquibase changesets.
5. Добавить Bean Validation и единый формат ошибок.
6. Покрыть unit/controller/integration-тестами основные CRUD и likes.
7. Реализовать надёжную синхронизацию PostgreSQL -> Elasticsearch и highlighting.
8. Завершить S3 upload flow и затем Report flow.
9. После стабилизации добавить Security/OpenAPI/observability только в соответствии с реальными требованиями.
10. Обновить README и диаграммы по фактической реализации.

## 11. Правила для следующих изменений

- Перед изменением проверять этот файл и затрагиваемые entity/DTO/mapper/service/controller вместе.
- При изменении API обновлять раздел 6 и `README.md`.
- При изменении модели данных добавлять миграцию, а не полагаться только на `ddl-auto`.
- Секреты и локальные credentials не коммитить.
- После каждой задачи запускать минимум `./mvnw test` на JDK 26.
- Не считать README источником истины без сверки с кодом.
