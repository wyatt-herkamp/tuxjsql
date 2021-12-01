package me.kingtux.tuxjsql.basic.sql.where;

import me.kingtux.tuxjsql.core.sql.where.Where;

public class BasicWhere implements Where {
    private String key;
    private Object value;
    private String comparator;

    public BasicWhere(String key, Object value, String comparator) {
        this.key = key;
        this.value = value;
        this.comparator = comparator;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public String getComparator() {
        return comparator;
    }
}
