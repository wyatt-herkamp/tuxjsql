package dev.tuxjsql.core.sql;

import dev.tuxjsql.core.response.DBAction;
import dev.tuxjsql.core.response.DBDelete;
import dev.tuxjsql.core.sql.where.WhereStatement;

import java.util.function.Consumer;

/**
 * This is an Object Representation of the SQL delete statement;
 */
public interface DeleteStatement {
    /**
     * Where would you like to delete;
     *
     * @return WhereStatement with a return type of this
     */

    WhereStatement<DeleteStatement> where();

    /**
     * Where would you like to delete
     *
     * @param whereStatement the consumer for the wherestatement we provide
     * @return the DeleteStatement
     */
    DeleteStatement where(Consumer<WhereStatement> whereStatement);

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
