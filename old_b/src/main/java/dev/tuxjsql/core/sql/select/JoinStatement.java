package dev.tuxjsql.core.sql.select;

import dev.tuxjsql.core.sql.SQLColumn;

public interface JoinStatement {

    JoinStatement on(String tableOneColumn, SQLColumn tableTwo);


    SelectStatement build();

    JoinStatement joinType(JoinType type);
}
