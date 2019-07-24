package dev.tuxjsql.core;

import dev.tuxjsql.core.sql.SQLTable;

import java.util.List;

public interface TableCollection extends List<SQLTable> {

    SQLTable getTableByName(String name);
}
