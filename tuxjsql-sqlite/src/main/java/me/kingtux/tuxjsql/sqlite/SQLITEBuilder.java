package me.kingtux.tuxjsql.sqlite;

import me.kingtux.tuxjsql.core.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@SuppressWarnings("Duplicates")
public class SQLITEBuilder implements Builder {
    @Override
    public TableBuilder createTable() {
        return new SQLITETableBuilder();
    }

    @Override
    public WhereStatement createWhere() {
        return new SQLiteWhereStatement();
    }

    @Override
    public SubWhereStatement createSubWhere() {
        return new SQLITESubWhere();
    }

    @Override
    public ColumnBuilder createColumn() {
        return new SQLiteColumnBuilder();
    }

    @Override
    public Connection createConnection(Properties properties) {
        return createSQLiteConnection(new File(properties.getProperty("db.file")));
    }

    private Connection createSQLiteConnection(File file) {
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
