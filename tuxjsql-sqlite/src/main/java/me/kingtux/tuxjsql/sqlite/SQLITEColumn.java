package me.kingtux.tuxjsql.sqlite;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.ColumnType;
import me.kingtux.tuxjsql.core.Table;

@SuppressWarnings("Duplicates")
public class SQLITEColumn implements Column {
    private String name;
    private boolean unique, primary, notNull, autoIncrement;
    private ColumnType type;
    private Object defaultValue;
    private Table table;

    SQLITEColumn(String name, boolean unique, boolean primary, boolean nullable, boolean autoIncrement, ColumnType type, Object defaultValue) {
        this.name = name;
        this.unique = unique;
        this.primary = primary;
        this.notNull = nullable;
        this.autoIncrement = autoIncrement;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    @Override
    public Table getTable() {
        return table;
    }

    void setTable(Table table) {
        this.table = table;
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
    public boolean isNotNull() {
        return notNull;
    }

    @Override
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    @Override
    public Object defaultValue() {
        return defaultValue;
    }

    @Override
    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append("`").append(name).append("`");
        builder.append(" ").append(buildType());;
        builder.append(isPrimary() ? " PRIMARY KEY" : "");
        builder.append(isAutoIncrement() ? " AUTOINCREMENT" : "");
        if (!isAutoIncrement()) {
            builder.append(isNotNull() ? " NOT NULL" : "");
            builder.append(isUnique() ? " UNIQUE" : "");
        }
        if (defaultValue != null) {
            builder.append(" DEFAULT ");
            if (defaultValue instanceof String) {
                builder.append("'").append(defaultValue).append("'");
            } else {
                builder.append(defaultValue);
            }
        }
        return builder.toString();
    }

    @Override
    public ColumnType getType() {
        return type;
    }

    private String buildType() {
        if (getType().getRules().size() == 0) return getType().getType().type();
        StringBuilder builder = new StringBuilder(getType().getType().type());
        builder.append("(");
        for (String s : getType().getRules()) {
            if (!builder.toString().endsWith("(")) {
                builder.append(",");
            }
            builder.append(s);
        }
        builder.append(")");
        return builder.toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SQLITEColumn)) return false;
        if (table == null) return ((SQLITEColumn) obj).name.equals(name);

        return ((SQLITEColumn) obj).name.equals(name) && table.equals(((SQLITEColumn) obj).getTable());
    }
}
