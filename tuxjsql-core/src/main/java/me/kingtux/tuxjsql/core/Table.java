package me.kingtux.tuxjsql.core;

import me.kingtux.tuxjsql.core.result.DBResult;
import me.kingtux.tuxjsql.core.builders.TableBuilder;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.core.statements.WhereStatement;
import org.slf4j.Logger;

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
public abstract class Table {
    /**
     * This returns all the columns
     *
     * @return columns
     */
    public abstract List<Column> getColumns();

    public Column getColumnByName(String s) {
        for (Column c : getColumns()) {
            if (c.getName().equals(s)) {
                return c;
            }
        }
        return null;
    }

    public abstract void insert(List<Column> columns, Object... values);

    public void insert(Map<Column, Object> kv) {
        List<Column> columns = new ArrayList<>();
        List<Object> items = new ArrayList<>();
        for (Map.Entry<Column, Object> e : kv.entrySet()) {
            if (e.getKey().isPrimary()) continue;
            columns.add(e.getKey());
            items.add(e.getValue());
        }
        insert(columns, items.toArray());
    }

    public Map.Entry<List<Column>, List<Object>> seperateThing(Object... items) {
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
    public void insert(Object... items) {
        Map.Entry<List<Column>, List<Object>> entry = seperateThing(items);
        insert(entry.getKey(), entry.getValue().toArray());
    }

    /**
     * This is pretty fragile
     * If you use please include a value for Columns with public Values. Or this will fail
     * @param items all the values for that table row
     */
    public void insertAll(Object... items) {
        List<Column> c = getInsertableColumns();
        if (items.length != c.size()) throw new IllegalArgumentException("Not Enough items");
        insert(c, items);
    }

    public List<Column> getInsertableColumns() {
        return getColumns().stream().filter(c -> (c.isPrimary() == false && c.isAutoIncrement() == false)).collect(Collectors.toList());
    }

    static TableBuilder create() {
        return TuxJSQL.getSQLBuilder().createTable();
    }

    public DBResult select(WhereStatement whereStatement) {
        return select(whereStatement,getColumns());
    }

    public DBResult select(WhereStatement whereStatement, List<Column> columns) {
        return select(SelectStatement.create().where(whereStatement).setColumns(columns.stream().map(Column::getName).collect(Collectors.toList())));
    }

    public abstract DBResult select(SelectStatement statement);

    public abstract void update(WhereStatement whereStatement, List<Column> columns, Object... values);


    public <T> void update(T primaryKeyValue, Object... keyValues) {
        Map.Entry<List<Column>, List<Object>> entry = seperateThing(keyValues);
        update(primaryKeyValue, entry.getKey(), entry.getValue().toArray());
    }

    public <T> void update(T primaryKeyValue, List<Column> columnsToUpdate, Object... vales) {
        update(TuxJSQL.getSQLBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue), columnsToUpdate, vales);
    }

    public Column getPrimaryColumn() {
        return getColumns().stream().filter(Column::isPrimary).findFirst().orElse(null);
    }

    public void update(WhereStatement whereStatement, Map<Column, Object> kv) {
        List<Column> columns = new ArrayList<>();
        List<Object> items = new ArrayList<>();
        for (Map.Entry<Column, Object> e : kv.entrySet()) {
            if (e.getKey().isPrimary()) continue;
            columns.add(e.getKey());
            items.add(e.getValue());
        }
        update(whereStatement, columns, items.toArray());
    }

    public <T> void update(T primaryKeyValue, Map<Column, Object> kv) {
        update(TuxJSQL.getSQLBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue), kv);
    }

    public abstract long max(Column c);

    public abstract  long min(Column c);

    public long max(String s) {
    return max(getColumnByName(s));
    }

    public long min(String s) {
        return min(getColumnByName(s));
    }

    public abstract Table createIfNotExists();
    public abstract void delete(WhereStatement whereStatement);

    public <T> T delete(T primaryKeyValue) {
        delete(TuxJSQL.getSQLBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue));
        return primaryKeyValue;
    }

    public abstract String getName();

    public abstract void drop();

    public abstract void dropColumn(Column column);

    public abstract void addColumn(Column column);

    public abstract void modifyColumn(Column column);

    protected Logger getLogger() {
        return TuxJSQL.logger;
    }

    public <T> DBResult select(T primaryKeyValue) {
        return select(TuxJSQL.getSQLBuilder().createWhere().start(getPrimaryColumn().getName(), primaryKeyValue));
    }
}
