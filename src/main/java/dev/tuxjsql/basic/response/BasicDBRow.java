package dev.tuxjsql.basic.response;

import dev.tuxjsql.core.exceptions.ColumnNotFoundException;
import dev.tuxjsql.core.response.DBColumnItem;
import dev.tuxjsql.core.response.DBRow;

import java.util.List;

public class BasicDBRow implements DBRow {
    private List<DBColumnItem> items;

    public BasicDBRow(List<DBColumnItem> items) {
        this.items = items;
    }

    @Override
    public DBColumnItem getRow(String name) {
        for (DBColumnItem columnItem : items) {
            if (name.contains(".")) {
                if (columnItem.getName().equals(name)) {
                    return columnItem;
                }
            } else {

                if(columnItem.getName().endsWith(name)){
                    return columnItem;
                }
            }
        }
        throw new ColumnNotFoundException(name);
    }
}
