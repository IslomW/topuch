package com.sharipov.topuch;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.Test;

class LiquibaseChangelogTest {

    @Test
    void changelogIsValidForPostgresql() throws Exception {
        try (ClassLoaderResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();
             Database database = DatabaseFactory.getInstance().openDatabase(
                     "offline:postgresql?changeLogFile=target/liquibase-changelog.csv",
                     null, null, null, resourceAccessor
             );
             Liquibase liquibase = new Liquibase(
                     "db/changelog/db.changelog-master.yaml", resourceAccessor, database
             )) {
            liquibase.validate();
        }
    }
}
