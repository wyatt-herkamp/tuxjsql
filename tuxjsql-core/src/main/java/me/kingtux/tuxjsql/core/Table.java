package me.kingtux.tuxjsql.core;

import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("All")
public interface Table {
    /**
     * This returns all the columns
     *
     * @return columns
     */
    List<Column> getColumns();

    default Column getColumnByName(String s) {
        for (Column c : getColumns()) {
            if (c.getName().equals(s)) {
                return c;
            }
        }
        return null;
    }

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

    public default Map.Entry<List<Column>, List<Object>> seperateThing(Object... items) {
        List<String> names = new ArrayList<>();
        List<Object> value = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            if ((i % 2) == 0) {
                names.add((String) items[i]);
            } else {
                value.add(items[i]);
            }
        }
        return new AbstractMap.SimpleEntry<List<Column>, List<Object>>(names.stream().map(this::getColumnByName).collect(Collectors.toList()), value);
    }


    public default void insert(Object... items) {
        Map.Entry<List<Column>, List<Object>> entry = seperateThing(items);
        insert(entry.getKey(), entry.getValue().toArray());
    }

    public default void insertAll(Object... items) {
        List<Column> c = getInsertableColumns();
        if (items.length != c.size()) throw new IllegalArgumentException("Not Enough items");
        insert(c, items);
    }

    default List<Column> getInsertableColumns() {
        return getColumns().stream().filter(c -> c.isPrimary() == false && c.isAutoIncrement() == false).collect(Collectors.toList());
    }
    default ResultSet select(WhereStatement whereStatement) {
        return select(whereStatement,getColumns());
    }

    ResultSet select(WhereStatement whereStatement,List<Column> columns);

    public default <T> ResultSet select(T primaryKeyValue) {
        return select(TuxJSQL.getBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue));
    }

    void update(WhereStatement whereStatement, List<Column> columns, Object... values);

    default <T> T update(T primaryKeyValue, Object... keyValues) {
        Map.Entry<List<Column>, List<Object>> entry = seperateThing(keyValues);
        return update(primaryKeyValue, entry.getKey(), entry.getValue().toArray());
    }

    default <T> T update(T primaryKeyValue, List<Column> columnsToUpdate, Object... vales) {
        update(TuxJSQL.getBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue), columnsToUpdate, vales);
        return primaryKeyValue;
    }

    default Column getPrimaryColumn() {
        return getColumns().stream().filter(Column::isPrimary).findFirst().orElse(null);
    }
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

    void createIfNotExists();
    void delete(WhereStatement whereStatement);

    public default <T> T delete(T primaryKeyValue) {
        delete(TuxJSQL.getBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue));
        return primaryKeyValue;
    }

    String getName();
}
