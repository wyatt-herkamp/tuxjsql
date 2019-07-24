package dev.tuxjsql.basic.response;

import dev.tuxjsql.core.response.DBDelete;
import dev.tuxjsql.core.sql.SQLTable;

public class BasicDBDelete implements DBDelete {
    private SQLTable table;
    private int numberOfRowsEffected;

    public BasicDBDelete(SQLTable table, int numberOfRowsEffected) {
        this.table = table;
        this.numberOfRowsEffected = numberOfRowsEffected;
    }

    @Override
    public SQLTable tableEffected() {
        return table;
    }

    @Override
    public int numberOfRowsEffected() {
        return numberOfRowsEffected;
    }
}
