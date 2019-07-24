package dev.tuxjsql.core.sql;

/**
 * This represents common SQLActions this will allow you to test to see if an action is support. And if not work around it.
 */
public enum SQLAction {
    /**
     * the SQLAction of INSERT
     */
    INSERT,
    /**
     * The SQLAction of delete
     */
    DELETE,
    UPDATE,
    SELECT,
    MAX,
    MIN,
    ADD_COLUMN,
    REMOVE_COLUMN,
    UPDATE_COLUMN,
    WHERE_AND,
    WHERE_OR,
    WHERE_NOT;
}
