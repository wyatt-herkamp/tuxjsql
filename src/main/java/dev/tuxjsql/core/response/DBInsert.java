package dev.tuxjsql.core.response;

import dev.tuxjsql.core.sql.SQLTable;

public interface DBInsert extends DBResult{

    @Override
    default int numberOfRows() {
        //This returns one since you can only insert one item at a time
        return 1;
    }

    Object primaryKey();


}
