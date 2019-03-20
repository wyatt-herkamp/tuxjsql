package me.kingtux.tuxjsql.core.result;

public class ColumnItem {
    private Object object;
    private String name;

    public ColumnItem(Object object, String name) {
        this.object = object;
        this.name = name;
    }

    public Object getAsObject() {
        return object;
    }

    public String getAsString() {
        if (object == null) return null;
        return ((String) object);
    }

    public String getName() {
        return name;
    }

    public int getAsInt() {
        return ((int) object);
    }
}
