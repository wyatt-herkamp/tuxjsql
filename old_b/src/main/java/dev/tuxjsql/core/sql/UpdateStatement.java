package dev.tuxjsql.core.sql;

import dev.tuxjsql.core.response.DBAction;
import dev.tuxjsql.core.response.DBUpdate;
import dev.tuxjsql.core.sql.where.WhereStatement;
import dev.tuxjsql.core.sql.where.Whereable;

import java.util.function.Consumer;

public interface UpdateStatement extends Whereable<UpdateStatement> {


    UpdateStatement value(String key, Object value);


    DBAction<DBUpdate> execute();

    UpdateStatement setTable(SQLTable basicSQLTable);
}
