package me.kingtux.tuxjsql.sqlite;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.builders.TableBuilder;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("Duplicates")
public class SQLITETableBuilder implements TableBuilder {
    private String name;
    private List<Column> columns= new ArrayList<>();
private SQLITEBuilder builder;

    public SQLITETableBuilder(SQLITEBuilder builder) {
        this.builder = builder;
    }

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
        return new SQLiteTable(name, columns, builder);
    }
}
