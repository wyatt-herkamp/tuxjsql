package me.kingtux.tuxjsql.core;

import java.util.Arrays;
import java.util.List;

public interface Builder {
    default Table createTable(String name, Column... columns) {
        return createTable(name, Arrays.asList(columns));
    }

    Table createTable(String name, List<Column> columns);

    default WhereStatement createWhere() {
        return createWhere("");
    }

    WhereStatement createWhere(String start);

    WhereStatement createWhere(SubWhereStatement start);

    SubWhereStatement createSubWhere();

    default Column createColumn(String name, ColumnType type) {
        return createColumn(name, type, false);
    }

    default Column createColumn(String name, ColumnType type, boolean primary) {
        return createColumn(name, type, primary, false);
    }

    default Column createColumn(String name, ColumnType type, boolean primary, boolean nullable) {
        return createColumn(name, type, primary, nullable, false);
    }

    default Column createColumn(String name, ColumnType type, boolean primary, boolean nullable, boolean unqiue) {
        return createColumn(name, type, primary, nullable, unqiue, primary);
    }

    Column createColumn(String name, ColumnType type, boolean primary, boolean nullable, boolean unqiue, boolean isAutoIncreament);
}
