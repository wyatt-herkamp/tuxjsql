package dev.tuxjsql.core.sql.select;

import dev.tuxjsql.core.response.DBAction;
import dev.tuxjsql.core.response.DBSelect;
import dev.tuxjsql.core.sql.SQLColumn;
import dev.tuxjsql.core.sql.SQLTable;
import dev.tuxjsql.core.sql.where.WhereStatement;
import dev.tuxjsql.core.sql.where.Whereable;

import java.util.List;
import java.util.function.Consumer;

public interface SelectStatement extends Whereable<SelectStatement> {


    SelectStatement limit(int i);

    SelectStatement column(String s);

    SelectStatement column(SQLColumn s);

    SelectStatement column(String... s);


    JoinStatement join();

    SelectStatement join(Consumer<JoinStatement> consumer);


    DBAction<DBSelect> execute();

    SelectStatement setTable(SQLTable basicSQLTable);
}
