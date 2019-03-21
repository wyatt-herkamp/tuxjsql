package me.kingtux.tuxjsql.core.builders;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TuxJSQL;

public interface TableBuilder {

    TableBuilder name(String s);

    TableBuilder addColumn(Column column);

    Table build();

    static TableBuilder create() {
        return TuxJSQL.getSQLBuilder().createTable();
    }

}
