package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.*;

import java.util.List;

public class MySQLBuilder implements Builder {


    @Override
    public Table createTable(String name, List<Column> columns) {
        return new MySQLTable(name, columns);
    }

    @Override
    public WhereStatement createWhere() {
        return new MySQLWhereStatement();
    }

    @Override
    public SubWhereStatement createSubWhere() {
        return new MysqlSubWhereStatement();
    }

    @Override
    public Column createColumn(String name, ColumnType type, boolean primary, boolean nullable, boolean unqiue, boolean isAutoIncreament) {
        return new MySQLColumn(name, unqiue, primary, nullable, isAutoIncreament, type);
    }
}
