package me.kingtux.tuxjsql.sqlite;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TableBuilder;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("Duplicates")
public class SQLITETableBuilder implements TableBuilder {
    private String name;
    private List<Column> columns= new ArrayList<>();


    @Override
    public TableBuilder name(String s) {
        name = s;
        return this;
    }

    @Override
    public TableBuilder addColumn(Column column) {
        columns.add(column);
        return this;
    }

    public Table build() {
        return new SQLiteTable(name, columns);
    }
}
