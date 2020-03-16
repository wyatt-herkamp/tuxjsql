package me.kingtux.tuxjsql.core.result;

import java.util.List;

/**
 * This represents a row ina DBResult
 */
public class DBRow {
    private List<ColumnItem> items;

    public DBRow(List<ColumnItem> items) {
        this.items = items;
    }

    public List<ColumnItem> getItems() {
        return items;
    }

    /**
     * Get row item by name
     * @param name the name
     * @return the ColumnItem
     */
    public ColumnItem getRowItem(String name) {
        return items.stream().filter(ri -> name.equalsIgnoreCase(ri.getName())).findFirst().orElse(null);
    }
}
