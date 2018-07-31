package io.debezium;

import io.debezium.config.Configuration;
import io.debezium.embedded.EmbeddedEngine;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.concurrent.Executor;


public class App {

    public static void main(String[] args) {
        org.apache.log4j.BasicConfigurator.configure(); //comment this line to hide the basic execution details

        App app = new App();
        app.runOracleCDC();
    }

    public void runOracleCDC() {

        // Define the configuration for the embedded and Oracle connector ...
        Configuration config = Configuration.create()
                .with("offset.storage",
                        "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename",
                        "/home/chathuranga/oracleLogs/offset.dat")
                .with("offset.flush.interval.ms", 60000)
                .with("database.out.server.name", "0e834c15eb32")
                .with("database.server.name", "0e834c15eb32")
                .with("database.history",
                        "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename",
                        "/home/chathuranga/oracleLogs/dbhistory.dat")
                .with("name", "new1OracleConnector")
                .with("connector.class", "io.debezium.connector.oracle.OracleConnector")
                .with("database.port", "1521")
                .with("database.user", "sys")
                .with("database.password", "Oradoc_db1")
                .with("database.sid", "ORCLCDB")
                .with("database.dbname", "ORCLCDB")
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