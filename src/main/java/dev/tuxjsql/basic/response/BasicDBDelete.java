package dev.tuxjsql.basic.response;

import dev.tuxjsql.core.response.DBDelete;
import dev.tuxjsql.core.sql.SQLTable;

public class BasicDBDelete implements DBDelete {
    private SQLTable table;
    private int numberOfRowsEffected;
    private boolean success;

    public BasicDBDelete(SQLTable table, int numberOfRowsEffected, boolean success) {
        this.table = table;
        this.numberOfRowsEffected = numberOfRowsEffected;
        this.success = success;
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
}
