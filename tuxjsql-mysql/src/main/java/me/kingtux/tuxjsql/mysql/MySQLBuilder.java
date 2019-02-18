package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.*;

import java.util.List;

public class MySQLBuilder implements Builder {


    @Override
    public Table createTable(String name, List<Column> columns) {
        return null;
    }

    @Override
    public WhereStatement createWhere(String start) {
        return null;
    }

    @Override
    public WhereStatement createWhere(SubWhereStatement start) {
        return null;
    }

    @Override
    public SubWhereStatement createSubWhere() {
        return null;
    }

    @Override
    public Column createColumn(String name, ColumnType type, boolean primary, boolean nullable, boolean unqiue, boolean isAutoIncreament) {
        return null;
    }
}
