package me.kingtux.tuxjsql.core;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@SuppressWarnings("All")
public interface Table {
    /**
     * This returns all the columns
     *
     * @return columns
     */
    List<Column> getColumns();


    void insert(List<Column> columns, Object... values);

    default void insert(Map<Column, Object> kv) {
        List<Column> columns = new ArrayList<>();
        List<Object> items = new ArrayList<>();
        for (Map.Entry<Column, Object> e : kv.entrySet()) {
            if (e.getKey().isPrimary()) continue;
            columns.add(e.getKey());
            items.add(e.getValue());
        }
        insert(columns, items.toArray());
    }

    default ResultSet select(WhereStatement whereStatement) {
        return select(whereStatement,getColumns());
    }

    ResultSet select(WhereStatement whereStatement,List<Column> columns);

    void update(WhereStatement whereStatement, List<Column> columns, Object... values);

    default void update(WhereStatement whereStatement, Map<Column, Object> kv) {
        List<Column> columns = new ArrayList<>();
        List<Object> items = new ArrayList<>();
        for (Map.Entry<Column, Object> e : kv.entrySet()) {
            if (e.getKey().isPrimary()) continue;
            columns.add(e.getKey());
            items.add(e.getValue());
        }
        update(whereStatement, columns, items.toArray());
    }

    void delete(WhereStatement whereStatement);
}
