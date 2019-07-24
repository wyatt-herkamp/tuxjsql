package dev.tuxjsql.basic.response;

import dev.tuxjsql.core.response.DBInsert;
import dev.tuxjsql.core.sql.SQLTable;

public class BasicDBInsert implements DBInsert {
    private SQLTable table;
    private Object primaryKey;

    public BasicDBInsert(SQLTable table, Object primaryKey) {
        this.table = table;
        this.primaryKey = primaryKey;
    }

    @Override
    public SQLTable tableEffected() {
        return table;
    }

    @Override
    public Object primaryKey() {
        return primaryKey;
    }
}
