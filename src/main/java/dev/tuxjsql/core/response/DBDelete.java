package dev.tuxjsql.core.response;

import dev.tuxjsql.core.sql.SQLTable;

public interface DBDelete {

    SQLTable tableEffected();

    int numberOfRowsEffected();

}
