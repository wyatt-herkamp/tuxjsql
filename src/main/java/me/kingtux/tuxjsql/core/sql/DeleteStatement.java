package me.kingtux.tuxjsql.core.sql;

import me.kingtux.tuxjsql.core.response.DBAction;
import me.kingtux.tuxjsql.core.response.DBDelete;
import me.kingtux.tuxjsql.core.sql.where.Whereable;

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
