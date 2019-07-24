package dev.tuxjsql.basic.response;

import dev.tuxjsql.basic.utils.BasicUtils;
import dev.tuxjsql.core.response.DBColumnItem;

import java.math.BigInteger;
import java.util.UUID;

public class BasicDBColumnItem implements DBColumnItem {
    private Object value;
    private String name;

    public BasicDBColumnItem(Object value, String columnName) {
        this.value = value;
        this.name = columnName;
    }

    @Override
    public <T> T getAsEnum() {
        if (value == null) return null;

        if (value instanceof Enum) {
            return (T) value;
        }
        String string = getAsString();
        if (string == null) return null;
        return BasicUtils.getAsEnum(string);
    }

    @Override
    public String getAsString() {
        if (value == null) return null;
        if (value instanceof String) return (String) value;
        return value.toString();
    }

    @Override
    public UUID getAsUUID() {
        if (value == null) return null;
        if (value instanceof String) UUID.fromString((String) value);
        return null;
    }

    @Override
    public int getAsInt() {
        if (value == null) return 0;
        if (value instanceof BigInteger) return ((BigInteger) value).intValue();
        if (value instanceof Integer) return (int) value;
        return Integer.parseInt((String) value);
    }

    @Override
    public long getAsLong() {
        if (value == null) return 0L;
        if (value instanceof Long) return (long) value;
        return Long.parseLong((String) value);
    }

    @Override
    public double getAsDouble() {
        if (value == null) return 0D;
        if (value instanceof Double) return (double) value;
        return Double.parseDouble((String) value);
    }

    @Override
    public Object getAsObject() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }
}
