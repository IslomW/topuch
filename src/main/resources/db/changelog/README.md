# Database migrations

Liquibase is the only owner of the PostgreSQL schema. Hibernate runs with
`ddl-auto: validate` and must never create or update production tables.

## Layout

```text
db/changelog/
├── db.changelog-master.yaml
└── releases/
    └── 2026.09/
        ├── 001-create-initial-schema.yaml
        ├── 002-add-domain-constraints.yaml
        └── 003-create-schema-indexes.yaml
```

The master changelog discovers files exactly two levels below `releases/`.
Liquibase processes the `YYYY.MM` directories and numbered YAML files in
lexicographical order.

## Adding a migration

1. Never edit a changeset that has been applied to a shared environment.
2. Create the next numbered YAML file in the current `YYYY.MM` directory, for
   example `releases/2026.09/004-add-post-status.yaml`. Numbering starts at
   `001` for each new month.
3. Use a globally unique changeset id such as `topuch:002-add-post-status`.
4. Set `dbms:postgresql` and `runInTransaction:true` when supported.
5. Add a concise `comment` and an explicit `rollback` block.
6. For a new month, create the next `releases/YYYY.MM/` directory. The master
   changelog discovers it automatically; do not add another include.
7. Never rename or move an applied migration: its path is part of its identity.
8. Test update and rollback against a disposable PostgreSQL database before
   deployment.

Example:

```yaml
databaseChangeLog:
  - changeSet:
      id: 2026.09-004-add-post-status
      author: topuch
      dbms: postgresql
      runInTransaction: true
      comment: Add lifecycle status to posts.
      changes:
        - addColumn:
            tableName: posts
            columns:
              - column:
                  name: status
                  type: varchar(32)
      rollback:
        - dropColumn:
            tableName: posts
            columnName: status
```

## Deployment rules

- Back up production data before structural or destructive migrations.
- Prefer expand/contract migrations for zero-downtime deployments: add the new
  schema first, deploy compatible application code, backfill data, and remove
  the old schema in a later release.
- Do not use `runOnChange` for schema changes.
- Do not use `drop-first` outside disposable local/test databases.
- Supply `DB_PASSWORD` as an environment secret. Do not commit credentials.
- Liquibase uses `DATABASECHANGELOG` and `DATABASECHANGELOGLOCK`; only one
  application instance applies a migration while the others wait for the lock.

## Existing legacy database

`2026.09/001-create-initial-schema.yaml` is a baseline for an empty database. A database created
by the old Hibernate `ddl-auto: update` configuration has `BIGINT` identifiers
and must not run this baseline directly. Create and test a dedicated data
migration to UUID before pointing a production deployment at that database.
