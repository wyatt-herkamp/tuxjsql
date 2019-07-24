package dev.tuxjsql.core.response;

import dev.tuxjsql.core.sql.SQLTable;

public interface DBUpdate {


    SQLTable tableEffected();

    int numberOfRowsEffected();


}
