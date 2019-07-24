package dev.tuxjsql.basic;

import dev.tuxjsql.core.TableCollection;
import dev.tuxjsql.core.sql.SQLTable;

import java.util.ArrayList;

public class BasicTableCollection extends ArrayList<SQLTable> implements TableCollection {

    @Override
    public SQLTable getTableByName(String name) {
        for (SQLTable table : this) {
            if (table.getName().equals(name)) {
                return table;
            }
        }
        return null;
    }

}
