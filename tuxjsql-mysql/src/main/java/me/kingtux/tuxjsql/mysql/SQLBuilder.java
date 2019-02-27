package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SQLBuilder implements Builder {

    @Override
    public TableBuilder createTable() {
        return new MySQLTableBuilder();
    }

    @Override
    public WhereStatement createWhere() {
        return new SQLWhereStatement();
    }

    @Override
    public SubWhereStatement createSubWhere() {
        return new SQLSubWhereStatement();
    }

    @Override
    public ColumnBuilder createColumn() {
        return new MySQLColumnBuilder();
    }


    @Override
    public Connection createConnection(Properties properties) {
        return createMysqlConnection(properties.getProperty("db.username"), properties.getProperty("db.password"), properties.getProperty("db.host"), properties.getProperty("db.database"));
    }


    private Connection createMysqlConnection(String username, String password, String host, String database) {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
