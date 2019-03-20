package me.kingtux.tuxjsql.core;

public interface TableBuilder {

    TableBuilder name(String s);

    TableBuilder addColumn(Column column);

    Table build();

    static TableBuilder create() {
        return TuxJSQL.getBuilder().createTable();
    }

}
