package dev.tuxjsql.core.sql;

import java.util.List;

public interface SQLColumn {


    String getName();

    Object defaultValue();


    boolean unique();

    boolean notNull();

    boolean autoIncrement();

    boolean primaryKey();

    boolean isForeignKey();

    SQLColumn foreignKey();

    SQLTable getTable();


    SQLDataType getDataType();

    List<String> dataTypeRules();

    /**
     * This takes all the rules. And makes a string for building the column
     * @return the column and its rules;
     */
    String build();

    String foreignBuild();
}
