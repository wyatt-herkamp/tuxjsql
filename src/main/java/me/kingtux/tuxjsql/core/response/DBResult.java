package me.kingtux.tuxjsql.core.response;

import me.kingtux.tuxjsql.core.sql.SQLTable;

import java.util.Optional;

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
     * Returns an Optional of the exception thrown.
     *
     * @return The Exception Thrown
     */
    Optional<Exception> getExceptionThrown();


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