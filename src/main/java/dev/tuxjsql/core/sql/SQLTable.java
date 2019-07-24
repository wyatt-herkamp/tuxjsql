package dev.tuxjsql.core.sql;

import dev.tuxjsql.core.sql.select.SelectStatement;

public interface SQLTable {

    void executeStatement(String string);

    SelectStatement select();

    UpdateStatement update();

    DeleteStatement delete();

    InsertStatement insert();

    String getName();

    SQLColumn getColumn(String s);
}
