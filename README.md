
# Embedded-Debezium-CDC

In this repository, you will find an implementation of change data capturing using embedded debezium.

To try, download or clone this repository and open `io.debezium.App.java` file.

# MySQL CDC

Prerequesites:
* Make sure you have MySQL installed.
* [Enable MySQL binary logging](https://debezium.io/docs/connectors/mysql/#enabling-the-binlog) and restart mysql service.

Running:
call `runMysqlCDC()` from main method.

Configuration and fine-tuning:

Find the [MySQL connector properties](https://debezium.io/docs/connectors/mysql/#connector-properties) and input configurations as key-value pairs.


Currently this feature is operating fine.

# Oracle CDC

Prerequesites:
* Install and Configure Oracle database. Please find the instructions [here](https://github.com/debezium/oracle-vagrant-box#setting-up-oracle-db).

Running:
call `runOracleCDC()` from main method and run.

Configuration and fine-tuning:

Find the [Oracle connector properties](https://debezium.io/docs/connectors/oracle/#connector-properties) and input configurations as key-value pairs.


Currently this feature is not producing change events.

# How to Contribute

-   Report issues at  [GitHub Issue Tracker](https://github.com/chathuranga95/Embedded-Debezium-CDC/issues).
    
-   Send your contributions as pull requests to the  [master branch](https://github.com/chathuranga95/Embedded-Debezium-CDC/tree/master).
