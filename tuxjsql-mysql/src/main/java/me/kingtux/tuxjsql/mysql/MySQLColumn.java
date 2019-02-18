package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.ColumnType;

public class MySQLColumn implements Column {
    private String name;
    private boolean unique, primary, nullable, autoIncrement;
    private ColumnType type;

    public MySQLColumn(String name, boolean unique, boolean primary, boolean nullable, boolean autoIncrement, ColumnType type) {
        this.name = name;
        this.unique = unique;
        this.primary = primary;
        this.nullable = nullable;
        this.autoIncrement = autoIncrement;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isUnique() {
        return unique;
    }

    @Override
    public boolean isPrimary() {
        return primary;
    }

    @Override
    public boolean isNullable() {
        return nullable;
    }

    @Override
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    @Override
    public ColumnType getType() {
        return type;
    }
}
