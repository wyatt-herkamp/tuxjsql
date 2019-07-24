package dev.tuxjsql.core.response;

import dev.tuxjsql.core.sql.SQLTable;

public interface DBInsert {

    SQLTable tableEffected();

    Object primaryKey();


}
