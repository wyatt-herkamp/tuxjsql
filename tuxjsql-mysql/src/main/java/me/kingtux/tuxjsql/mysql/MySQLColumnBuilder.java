package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.builders.ColumnBuilder;
import me.kingtux.tuxjsql.core.ColumnType;
@SuppressWarnings("Duplicates")
public class MySQLColumnBuilder implements ColumnBuilder {
    private boolean notNull, autoIncrement, primary, unique;
    private String name;
    private Object defaultValue = null;
    private ColumnType type;

    @Override
    public ColumnBuilder notNull(boolean nullable) {
        notNull = nullable;
        return this;
    }

    @Override
    public ColumnBuilder name(String s) {
        name = s;
        return this;
    }

    @Override
    public ColumnBuilder autoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
        return this;
    }

    @Override
    public ColumnBuilder primary(boolean primary) {
        this.primary = primary;
        return this;
    }

    @Override
    public ColumnBuilder unique(boolean unique) {
        this.unique = unique;
        return this;
    }

    @Override
    public ColumnBuilder defaultValue(Object value) {
        defaultValue = value;
        return this;
    }

    @Override
    public ColumnBuilder type(ColumnType value) {
        type = value;
        return this;
    }

    @Override
    public Column build() {
        return new SQLColumn(name, unique, primary, notNull, autoIncrement, type, defaultValue);
    }


}
