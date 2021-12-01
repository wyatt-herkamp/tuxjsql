package me.kingtux.tuxjsql.core.sql;

import me.kingtux.tuxjsql.core.response.DBAction;
import me.kingtux.tuxjsql.core.response.DBInsert;

import java.util.Map;

public interface InsertStatement {

    InsertStatement values(Map<String, Object> map);


    InsertStatement value(String string, Object o);


    DBAction<DBInsert> execute();

    InsertStatement setTable(SQLTable basicSQLTable);
}
