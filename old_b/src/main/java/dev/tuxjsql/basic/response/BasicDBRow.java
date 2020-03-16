package dev.tuxjsql.basic.response;

import dev.tuxjsql.core.exceptions.ColumnNotFoundException;
import dev.tuxjsql.core.response.DBColumnItem;
import dev.tuxjsql.core.response.DBRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasicDBRow implements DBRow {
    private List<DBColumnItem> items;

    public BasicDBRow(List<DBColumnItem> items) {
        this.items = items;
    }


    @Override
    public Optional<DBColumnItem> getColumn(String name) {
        for (DBColumnItem columnItem : items) {
            if (name.contains(".")) {
                if (columnItem.getName().equals(name)) {
                    return Optional.of(columnItem);
                }
            } else {

                if (columnItem.getName().endsWith(name)) {
                    return Optional.of(columnItem);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<DBColumnItem> getColumn(int i) {
        if (i < items.size()) {
            return Optional.of(items.get(i));
        }
        return Optional.empty();
    }

    @Override
    public List<DBColumnItem> getColumns() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean doesColumnExist(String name) {
        return getColumn(name).isPresent();
    }

    @Override
    public int numberOfColumns() {
        return items.size();
    }
}
