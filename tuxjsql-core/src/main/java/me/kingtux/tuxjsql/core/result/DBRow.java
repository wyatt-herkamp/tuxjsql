package me.kingtux.tuxjsql.core.result;

import java.util.List;

public class DBRow {
    private List<ColumnItem> items;

    public DBRow(List<ColumnItem> items) {
        this.items = items;
    }

    public List<ColumnItem> getItems() {
        return items;
    }

    public ColumnItem getRowItem(String name) {
        return items.stream().filter(ri -> name.equalsIgnoreCase(ri.getName())).findFirst().orElse(null);
    }
}
