package me.kingtux.tuxjsql.core;

public enum CommonDataTypes implements DataType {

    TEXT,
    VARCHAR,
    TINYINT,
    MEDIUMINT,
    INT,
    BIGINT,
    DOUBLE;

    @Override
    public String type() {
        return name();
    }
}
