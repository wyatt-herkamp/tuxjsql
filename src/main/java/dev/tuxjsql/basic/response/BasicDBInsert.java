package dev.tuxjsql.basic.response;

import dev.tuxjsql.core.response.DBInsert;
import dev.tuxjsql.core.sql.SQLTable;

public class BasicDBInsert implements DBInsert {
    private SQLTable table;
    private Object primaryKey;
    private boolean success;

    public BasicDBInsert(SQLTable table, Object primaryKey, boolean success) {
        this.table = table;
        this.primaryKey = primaryKey;
        this.success = success;
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
}
