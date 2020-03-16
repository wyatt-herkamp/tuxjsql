package me.kingtux.tuxjsql.core;

import me.kingtux.tuxjsql.core.builders.ColumnBuilder;

/**
 * An Object to represent a column
 */
public interface Column {
    /**
     * The Column Name
     *
     * @return the name
     */
    String getName();

    /**
     * A keyword safe name
     * @return adds ` to the name
     */
    default String getSafeName() {
        return "`" + getName() + "`";
    }

    /**
     * Is it unique
     *
     * @return the unique
     */
    boolean isUnique();

    /**
     * Is it a primary key
     *
     * @return status
     */
    boolean isPrimary();

    /**
     * Is it not Null
     * @return status
     */
    boolean isNotNull();

    /**
     * The Type
     * @return the column Type
     */
    ColumnType getType();

    /**
     * Is it an AUTO INCREMENT
     * @return is it auto increment
     */
    boolean isAutoIncrement();

    /**
     * The default value.
     * Can be null!
     * @return the default value
     */
    Object defaultValue();

    /**
     * Build the Column String to be used in execution
     * @return the the string
     */
    String build();

    /**
     * The Table that this column is apart of
     * @return the table
     */
    Table getTable();


    static ColumnBuilder create() {
        return TuxJSQL.getSQLBuilder().createColumn();
    }
}
