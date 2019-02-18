package me.kingtux.tuxjsql.core;

public interface Column {
    String getName();

    boolean isUnique();

    boolean isPrimary();

    boolean isNullable();

    ColumnType getType();

    boolean isAutoIncrement();


    String build();
}
