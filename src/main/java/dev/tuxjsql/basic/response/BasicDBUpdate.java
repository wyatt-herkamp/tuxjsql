package dev.tuxjsql.basic.response;

import dev.tuxjsql.core.response.DBUpdate;
import dev.tuxjsql.core.sql.SQLTable;

public class BasicDBUpdate implements DBUpdate {
    private SQLTable table;
    private int numberOfRowsEffected;

    public BasicDBUpdate(SQLTable table, int numberOfRowsEffected) {
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
