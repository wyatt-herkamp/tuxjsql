package dev.tuxjsql.core.sql.select;

import dev.tuxjsql.core.response.DBAction;
import dev.tuxjsql.core.response.DBSelect;
import dev.tuxjsql.core.sql.SQLColumn;
import dev.tuxjsql.core.sql.SQLTable;
import dev.tuxjsql.core.sql.where.WhereStatement;

import java.util.function.Consumer;

public interface SelectStatement {

    WhereStatement<SelectStatement> where();

    SelectStatement where(Consumer<WhereStatement> whereStatement);

    SelectStatement limit(int i);

    SelectStatement column(String s);

    SelectStatement column(SQLColumn s);

    SelectStatement column(String... s);


    JoinStatement join();

    SelectStatement join(Consumer<JoinStatement> consumer);


    DBAction<DBSelect> execute();

    SelectStatement setTable(SQLTable basicSQLTable);
}
