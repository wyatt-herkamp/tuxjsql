package me.kingtux.tuxjsql.core.result;

import me.kingtux.tuxjsql.core.Table;

import java.util.Iterator;
import java.util.List;

/**
 * This is a simplified version of a ResultSet so less errors in the end
 */
public class DBResult implements Iterable<DBRow> {
    private int numberOfRows, numberOfColumns;
    private Table table;
    private List<DBRow> rows;

    public DBResult(int numberOfRows, int numberOfColumns, Table table, List<DBRow> rows) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.table = table;
        this.rows = rows;
    }

    /**
     * Number of rows
     * @return the number of rows
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Number of columns
     * @return the number of columns
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * The table that it was used on
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * The Rows
     * @return the rows
     */
    public List<DBRow> getRows() {
        return rows;
    }

    @Override
    public Iterator<DBRow> iterator() {
        return rows.iterator();
    }

    public DBRow get(int i) {
        return rows.get(i);
    }

    public DBRow first() {
        if (rows.size() == 0) return null;
        return rows.get(0);
    }

}
