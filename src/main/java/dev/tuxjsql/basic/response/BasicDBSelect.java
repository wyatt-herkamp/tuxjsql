package dev.tuxjsql.basic.response;

import dev.tuxjsql.core.response.DBSelect;
import dev.tuxjsql.core.response.DBRow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BasicDBSelect implements DBSelect {
    private List<DBRow> rows;
    private boolean successful;

    public BasicDBSelect(List<DBRow> rows, boolean successful) {
        this.rows = rows;
        this.successful = successful;
    }

    public BasicDBSelect(boolean successful) {
        this.successful = successful;
        this.rows = new ArrayList<>();
    }

    public BasicDBSelect(List<DBRow> rows) {
        this.rows = rows;
    }

    @Override
    public List<DBRow> getRows() {
        return rows;
    }



    @Override
    public DBRow get(int i) {
        return rows.get(i);
    }

    @Override
    public DBRow first() {
        return get(0);
    }

    @Override
    public int numberOfRows() {
        return rows.size();
    }

    @Override
    public boolean successful() {
        return successful;
    }

    @Override
    public Iterator<DBRow> iterator() {
        return rows.iterator();
    }
}
