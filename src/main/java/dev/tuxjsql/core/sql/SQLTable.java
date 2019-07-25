package dev.tuxjsql.core.sql;

import dev.tuxjsql.core.response.*;
import dev.tuxjsql.core.sql.select.SelectStatement;

import java.util.List;
import java.util.Map;

/**
 * This represents a Database Table
 */
public interface SQLTable {
    /**
     * This executes a statement
     *
     * @param string the query string
     */
    void executeStatement(String string);

    /**
     * This returns a SelectStatement for this Table
     *
     * @return the new select statement
     */
    SelectStatement select();

    /**
     * Select by the primary key
     *
     * @param primaryKey the primary key
     * @return the DBAction
     */
    DBAction<DBSelect> select(Object primaryKey);

    /**
     * Select by a column value
     *
     * @param columnToLookFor the column to look for Either a SQLColumn or column name
     * @param value           The value for this
     * @return the DBAction
     */
    DBAction<DBSelect> select(Object columnToLookFor, Object value);

    UpdateStatement update();

    DBAction<DBUpdate> update(Object primaryKey, Map<?, Object> values);

    DeleteStatement delete();

    DBAction<DBDelete> delete(Object primarykey);


    InsertStatement insert();

    DBAction<DBInsert> insert(Map<?, Object> values);

    /**
     * Returns the table name
     *
     * @return the column name
     */
    String getName();

    /**
     * Gets a specific column by name
     *
     * @param s the column name
     * @return the column null if not found
     */
    SQLColumn getColumn(String s);

    /**
     * Returns and List of columns for this table
     *
     * @return the list of columns
     */
    List<SQLColumn> getColumns();

    /**
     * Gets the primaryColumn for this table
     *
     * @return the PrimaryColumn
     */
    SQLColumn getPrimaryColumn();

}
