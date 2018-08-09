package io.debezium;

import io.debezium.config.Configuration;
import io.debezium.embedded.EmbeddedEngine;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.concurrent.Executor;


public class App {

    public static void main(String[] args) {
//        org.apache.log4j.BasicConfigurator.configure(); //uncomment this line to show the basic execution details

        App app = new App();
        app.runMysqlCDC();
    }

    public void runOracleCDC() {

        // Define the configuration for the embedded and Oracle connector ...
        Configuration config = Configuration.create()
                .with("connector.class", "io.debezium.connector.oracle.OracleConnector")
                .with("offset.storage",
                        "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename",
                        "/home/chathuranga/oracleLogs/offset.dat")
                .with("offset.flush.interval.ms", 60000)
                .with("name", "newnew1oracleConnector")
                .with("database.hostname","localhost")
                .with("database.port", "1521")
                .with("database.user", "c##xstrm")
                .with("database.password", "xs")
                .with("database.sid", "ORCLCDB")
                .with("database.server.name", "server1")
                .with("database.out.server.name", "dbzxout")
                .with("database.history",
                        "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename",
                        "/home/chathuranga/oracleLogs/dbhistory.dat")
                .with("database.dbname", "ORCLCDB")
                .with("database.pdb.name", "ORCLPDB1")
                .build();

        // Create the engine with this configuration ...
        EmbeddedEngine engine = EmbeddedEngine.create()
                .using(config)
                .notifying(this::handleEvent)
                .build();

        // Run the engine asynchronously ...
        Executor executor = new Executor() {
            public void execute(Runnable command) {
                command.run();
            }
        };
        executor.execute(engine);

    }

    public void runMysqlCDC() {
        // Define the configuration for the embedded and MySQL connector ...
        Configuration config = Configuration.create()
                /* begin engine properties */
                .with("connector.class",
                        "io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage",
                        "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename",
                        "/home/chathuranga/mysqlLogs/offset1.dat")
                .with("offset.flush.interval.ms", 60000)
                /* begin connector properties */
                .with("name", "my-sql-connector")
                .with("database.hostname", "localhost")
                .with("database.port", 3306)
                .with("database.user", "debezium")
                .with("database.password", "dbz")
                .with("server.id", 85743)
                .with("database.server.name", "my-app-connector")
                .with("database.history",
                        "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename",
                        "/home/chathuranga/mysqlLogs/dbhistory1.dat")
                .build();

        // Create the engine with this configuration ...
        EmbeddedEngine engine = EmbeddedEngine.create()
                .using(config)
                .notifying(this::handleEvent)
                .build();

        // Run the engine asynchronously ...
        Executor executor = new Executor() {
            public void execute(Runnable command) {
                command.run();
            }
        };
        executor.execute(engine);

    }

    private void handleEvent(SourceRecord sourceRecord) {
        System.out.println(sourceRecord); //print the source record to observe.
    }

}