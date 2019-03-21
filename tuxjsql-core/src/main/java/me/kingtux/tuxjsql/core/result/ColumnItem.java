package me.kingtux.tuxjsql.core.result;

/**
 * This represents a Column value.
 *
 */
public class ColumnItem {
    private Object object;
    private String name;

    public ColumnItem(Object object, String name) {
        this.object = object;
        this.name = name;
    }

    /**
     * Get as object
     * @return get the value as a object
     */
    public Object getAsObject() {
        return object;
    }

    public String getAsString() {
        if (object == null) return null;
        return ((String) object);
    }

    /**
     * Column Name
     * @return the column name
     */
    public String getName() {
        return name;
    }

    /**
     * Get as integer
     * @return get as integer
     */
    public int getAsInt() {
        if (object == null) return 0;
        return ((int) object);
    }
}
