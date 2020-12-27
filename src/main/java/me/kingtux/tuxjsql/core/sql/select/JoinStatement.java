package me.kingtux.tuxjsql.core.sql.select;

import me.kingtux.tuxjsql.core.sql.SQLColumn;

public interface JoinStatement {

    JoinStatement on(String tableOneColumn, SQLColumn tableTwo);


    SelectStatement build();

    JoinStatement joinType(JoinType type);
}
