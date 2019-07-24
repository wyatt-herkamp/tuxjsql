package dev.tuxjsql.core.sql;

import dev.tuxjsql.core.response.DBAction;
import dev.tuxjsql.core.response.DBDelete;
import dev.tuxjsql.core.sql.where.WhereStatement;
import dev.tuxjsql.core.sql.where.Whereable;

import java.util.function.Consumer;

/**
 * This is an Object Representation of the SQL delete statement;
 */
public interface DeleteStatement extends Whereable<DeleteStatement> {


    /**
     * Creates a DBAction
     *
     * @return the DBAction
     * @see DBAction
     */
    DBAction<DBDelete> execute();

    /**
     * The Table you would like to deleteon
     *
     * @param table the table you would like to delete at
     * @return the DeleteStatement
     */
    DeleteStatement setTable(SQLTable table);
}
