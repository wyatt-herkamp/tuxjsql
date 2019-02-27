package me.kingtux.tuxjsql.core;

public interface Column {
    String getName();

    boolean isUnique();

    boolean isPrimary();

    @Deprecated
    boolean isNullable();

    boolean isNotNull();

    ColumnType getType();

    boolean isAutoIncrement();

    Object defaultValue();

    String build();
}
