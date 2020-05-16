package dev.tuxjsql.basic.response;

import dev.tuxjsql.core.response.DBUpdate;
import dev.tuxjsql.core.sql.SQLTable;

import java.util.Optional;

public class BasicDBUpdate implements DBUpdate {
    private SQLTable table;
    private int numberOfRowsEffected;
    private boolean success;

    private Exception exception;
    public BasicDBUpdate(SQLTable table, int numberOfRowsEffected, boolean success) {
        this.table = table;
        this.numberOfRowsEffected = numberOfRowsEffected;
        this.success = success;
    }

    public BasicDBUpdate(SQLTable table, Exception exception) {
        this.table = table;
        this.exception = exception;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int numberOfRows() {
        return numberOfRowsEffected;
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
