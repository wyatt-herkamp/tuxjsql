package me.kingtux.tuxjsql.core.sql.select;

import me.kingtux.tuxjsql.core.response.DBAction;
import me.kingtux.tuxjsql.core.response.DBSelect;
import me.kingtux.tuxjsql.core.sql.SQLColumn;
import me.kingtux.tuxjsql.core.sql.SQLTable;
import me.kingtux.tuxjsql.core.sql.where.Whereable;

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
