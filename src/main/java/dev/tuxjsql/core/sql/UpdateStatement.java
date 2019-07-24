package dev.tuxjsql.core.sql;

import dev.tuxjsql.core.response.DBAction;
import dev.tuxjsql.core.response.DBUpdate;
import dev.tuxjsql.core.sql.where.WhereStatement;

import java.util.function.Consumer;

public interface UpdateStatement {


    UpdateStatement value(String key, Object value);

    WhereStatement<UpdateStatement> where();

    UpdateStatement where(Consumer<WhereStatement> consumer);

    DBAction<DBUpdate> execute();

    UpdateStatement setTable(SQLTable basicSQLTable);
}
