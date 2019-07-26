package dev.tuxjsql.core.response;

import dev.tuxjsql.core.sql.SQLTable;

/**
 * This is the basic data that all DBResults will have
 */
public interface DBResult {
    /**
     * Was the query a success
     *
     * @return the status
     */
    boolean success();

    /**
     * The number of rows affected or collected
     *
     * @return the number of rows affected or collected
     */
    int numberOfRows();

    /**
     * This is the table that was affected
     *
     * @return the table
     */
    SQLTable tableAffected();
}