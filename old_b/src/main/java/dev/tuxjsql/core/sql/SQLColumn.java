package dev.tuxjsql.core.sql;

import java.util.List;

/**
 * This represents a column
 */
public interface SQLColumn {

    /**
     * returns the column name
     *
     * @return the name of this column
     */
    String getName();

    Object defaultValue();


    boolean unique();

    boolean notNull();

    boolean autoIncrement();

    boolean primaryKey();

    boolean isForeignKey();

    SQLColumn foreignKey();

    /**
     * This is the table its apart of
     *
     * @return the table
     */
    SQLTable getTable();


    SQLDataType getDataType();

    List<String> dataTypeRules();

    /**
     * This takes all the rules. And makes a string for building the column
     *
     * @return the column and its rules;
     */
    String build();

    String foreignBuild();
}
