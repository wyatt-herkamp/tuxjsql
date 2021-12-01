package me.kingtux.tuxjsql.basic.response;

import me.kingtux.tuxjsql.core.response.DBInsert;
import me.kingtux.tuxjsql.core.sql.SQLTable;

import java.util.Optional;

public class BasicDBInsert implements DBInsert {
    private SQLTable table;
    private Object primaryKey;
    private final boolean success;
    private Exception exception;

    public BasicDBInsert(SQLTable table, Object primaryKey, boolean success) {
        this.table = table;
        this.primaryKey = primaryKey;
        this.success = success;
    }

    public BasicDBInsert(Exception exception, SQLTable sqlTable) {
        this.exception = exception;
        this.table = sqlTable;
        success = false;
    }

    @Override
    public Object primaryKey() {
        return primaryKey;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public SQLTable tableAffected() {
        return table;
    }

    @Override
    public Optional<Exception> getExceptionThrown() {
        return Optional.ofNullable(exception);
    }
}
