package dev.tuxjsql.basic.response;

import dev.tuxjsql.core.response.DBSelect;
import dev.tuxjsql.core.response.DBRow;
import dev.tuxjsql.core.sql.SQLTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class BasicDBSelect implements DBSelect {
    private List<DBRow> rows;
    private boolean successful;
    private SQLTable table;

    public BasicDBSelect(List<DBRow> rows, boolean successful, SQLTable table) {
        this.rows = rows;
        this.successful = successful;
        this.table = table;
    }

    public BasicDBSelect(boolean successful, SQLTable table) {
        this.successful = successful;
        this.rows = new ArrayList<>();
        this.table = table;
    }

    public BasicDBSelect(List<DBRow> rows) {
        this.rows = rows;
    }

    @Override
    public List<DBRow> getRows() {
        return new ArrayList<>(rows);
    }


    @Override
    public DBRow get(int i) {
        return rows.get(i);
    }

    @Override
    public Optional<DBRow> first() {
        if (numberOfRows() == 0) {
            return Optional.empty();
        }
        return Optional.of(rows.get(0));
    }

    @Override
    public boolean success() {
        return successful;
    }

    @Override
    public int numberOfRows() {
        return rows.size();
    }

    @Override
    public SQLTable tableAffected() {
        return table;
    }


    @Override
    public Iterator<DBRow> iterator() {
        return rows.iterator();
    }
}
