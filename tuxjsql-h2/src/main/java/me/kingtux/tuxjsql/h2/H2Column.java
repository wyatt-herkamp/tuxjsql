package me.kingtux.tuxjsql.h2;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.ColumnType;
import me.kingtux.tuxjsql.core.Table;
@SuppressWarnings("Duplicates")
public class H2Column implements Column {
    private String name;
    private boolean unique, primary, notNull, autoIncrement;
    private ColumnType type;
    private Object defaultValue;
    private Table table;

    H2Column(String name, boolean unique, boolean primary, boolean nullable, boolean autoIncrement, ColumnType type, Object defaultValue) {
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
        builder.append(" ").append(buildType());
        builder.append(isAutoIncrement() ? " AUTO_INCREMENT" : "");
        builder.append(isPrimary() ? " PRIMARY KEY" : "");
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

    public void setTable(Table table) {
        this.table = table;
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
    public String toString() {
        return "H2Column{" +
                "name='" + name + '\'' +
                ", unique=" + unique +
                ", primary=" + primary +
                ", notNull=" + notNull +
                ", autoIncrement=" + autoIncrement +
                ", type=" + type +
                ", defaultValue=" + defaultValue +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof H2Column)) return false;
        if (table == null) return ((H2Column) obj).name.equals(name);

        return ((H2Column) obj).name.equals(name) && table.equals(((H2Column) obj).getTable());
    }
}
