package dev.tuxjsql.core.sql;

import dev.tuxjsql.basic.sql.BasicSQLTable;
import dev.tuxjsql.core.response.DBAction;
import dev.tuxjsql.core.response.DBInsert;

import java.util.Map;

public interface InsertStatement {

    InsertStatement values(Map<String, Object> map);

    InsertStatement value(String string, Object o);

    DBAction<DBInsert> execute();

    InsertStatement setTable(SQLTable basicSQLTable);
}
