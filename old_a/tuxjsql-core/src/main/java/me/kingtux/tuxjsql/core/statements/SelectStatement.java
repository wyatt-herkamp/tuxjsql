package me.kingtux.tuxjsql.core.statements;

import me.kingtux.tuxjsql.core.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SelectStatement {

    protected List<String> columns = new ArrayList<>();
    protected WhereStatement whereStatement = null;
    protected int limit = 0;
    protected ORDERBy orderBy = null;
    protected List<String> columnsToOrderBy = new ArrayList<>();
    public static SelectStatement create() {
        return TuxJSQL.getSQLBuilder().createSelectStatement();
    }

    public SelectStatement orderBy(ORDERBy by, String... s) {
        orderBy = by;
        columnsToOrderBy.addAll(Arrays.asList(s));
        return this;
    }

    public SelectStatement addColumn(Column column) {
        columns.add(column.getName());
        return this;
    }

    public SelectStatement limit(int i) {
        limit = i;
        return this;
    }
    public SelectStatement where(WhereStatement whereStatement) {
        this.whereStatement = whereStatement;
        return this;
    }

    public abstract Query build(Table table);

    public SelectStatement addColumn(String name) {
        columns.add(name);
        System.out.println(columns.size()+columns.get(0));
        return this;
    }

    public List<String> getColumns() {
        return columns;
    }

    public SelectStatement setColumns(List<String> columns) {
        this.columns = columns;
        return this;
    }
}
