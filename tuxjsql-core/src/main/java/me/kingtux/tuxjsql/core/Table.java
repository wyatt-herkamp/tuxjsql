package me.kingtux.tuxjsql.core;

import me.kingtux.tuxjsql.core.result.DBResult;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.core.statements.WhereStatement;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This represents a table.
 * This also has all the methods you could need to interact with said table.
 */
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

    /**
     * Insert using the following format
     * column-name value, column-name value, column-name value
     *
     * @param items Insert to the database by just providing name and value
     */
    public default void insert(Object... items) {
        Map.Entry<List<Column>, List<Object>> entry = seperateThing(items);
        insert(entry.getKey(), entry.getValue().toArray());
    }

    /**
     * This is pretty fragile
     * If you use please include a value for Columns with Default Values. Or this will fail
     * @param items all the values for that table row
     */
    public default void insertAll(Object... items) {
        List<Column> c = getInsertableColumns();
        if (items.length != c.size()) throw new IllegalArgumentException("Not Enough items");
        insert(c, items);
    }

    default List<Column> getInsertableColumns() {
        return getColumns().stream().filter(c -> (c.isPrimary() == false && c.isAutoIncrement() == false)).collect(Collectors.toList());
    }

    static TableBuilder create() {
        return TuxJSQL.getBuilder().createTable();
    }

    default DBResult select(WhereStatement whereStatement) {
        return select(whereStatement,getColumns());
    }

    default DBResult select(WhereStatement whereStatement, List<Column> columns) {
        return select(SelectStatement.create().where(whereStatement).setColumns(columns.stream().map(Column::getName).collect(Collectors.toList())));
    }

    DBResult select(SelectStatement statement);

    void update(WhereStatement whereStatement, List<Column> columns, Object... values);


    default <T> void update(T primaryKeyValue, Object... keyValues) {
        Map.Entry<List<Column>, List<Object>> entry = seperateThing(keyValues);
        update(primaryKeyValue, entry.getKey(), entry.getValue().toArray());
    }

    default <T> void update(T primaryKeyValue, List<Column> columnsToUpdate, Object... vales) {
        update(TuxJSQL.getBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue), columnsToUpdate, vales);
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

    default <T> void update(T primaryKeyValue, Map<Column, Object> kv) {
        update(TuxJSQL.getBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue), kv);
    }

    int max(Column c);

    int min(Column c);

    default int max(String s) {
    return max(getColumnByName(s));
    }

    default int min(String s) {
        return min(getColumnByName(s));
    }
    Table createIfNotExists();
    void delete(WhereStatement whereStatement);

    public default <T> T delete(T primaryKeyValue) {
        delete(TuxJSQL.getBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue));
        return primaryKeyValue;
    }

    String getName();

    void drop();

    void dropColumn(Column column);

    void addColumn(Column column);

    void modifyColumn(Column column);

    public default <T> DBResult select(T primaryKeyValue) {
        return select(TuxJSQL.getBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue));
    }
}
