package me.kingtux.tuxjsql.core.sql;

import me.kingtux.tuxjsql.core.response.DBAction;
import me.kingtux.tuxjsql.core.response.DBUpdate;
import me.kingtux.tuxjsql.core.sql.where.Whereable;

public interface UpdateStatement extends Whereable<UpdateStatement> {


    UpdateStatement value(String key, Object value);


    DBAction<DBUpdate> execute();

    UpdateStatement setTable(SQLTable basicSQLTable);
}
