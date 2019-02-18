package me.kingtux.tuxjsql.core;

public enum CommonDataTypes implements ColumnType {
    TEXT,
    INT;

    @Override
    public String type() {
        return name();
    }
}
