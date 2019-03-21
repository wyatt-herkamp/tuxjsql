package me.kingtux.tuxjsql.sqlite;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.Query;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.result.DBResult;
import me.kingtux.tuxjsql.core.result.DBRow;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.core.statements.WhereStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static me.kingtux.tuxjsql.core.TuxJSQL.Utils.resultSetToResultRow;

@SuppressWarnings("Duplicates")
public class SQLiteTable extends Table {
    private String name;
    private List<Column> columns;

    public SQLiteTable(String name, List<Column> columns) {
        this.name = name;
        this.columns = columns;
    }

    @Override
    public List<Column> getColumns() {
        return columns;
    }


    @Override
    public void update(WhereStatement whereStatement, List<Column> columns, Object... values) {
        StringBuilder columsToUpdate = new StringBuilder();
        for (Column column : columns) {
            if (!columsToUpdate.toString().isEmpty()) {
                columsToUpdate.append(",");
            }
            columsToUpdate.append(column.getName() + "=?");
        }
        String query = String.format(SQLiteQuery.UPDATE.getQuery(), name, columsToUpdate, whereStatement.build());
        try {
            PreparedStatement preparedStatement = TuxJSQL.getConnection().prepareStatement(query);
            int fin = 0;
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
                fin = i;
            }
            Object[] valu = whereStatement.values();
            for (int i = 0; i < valu.length; i++) {
                fin++;
                preparedStatement.setObject(fin + 1, valu[i]);
            }

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long max(Column c) {
        long i = 0;
        try (ResultSet resultSet = TuxJSQL.getConnection().createStatement().executeQuery(String.format(SQLiteQuery.MAX.getQuery(), c.getName(), name))) {
            resultSet.next();
            i = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public long min(Column c) {
        long i = 0;
        try (ResultSet resultSet = TuxJSQL.getConnection().createStatement().executeQuery(String.format(SQLiteQuery.MIN.getQuery(), c.getName(), name))) {
            resultSet.next();
            i = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public Table createIfNotExists() {
        StringBuilder builder = new StringBuilder();
        for (Column column : columns) {
            if (!builder.toString().isEmpty()) {
                builder.append(", ");
            }
            builder.append(column.build());
        }
        String query = String.format(SQLiteQuery.TABLE.getQuery(), name, builder.toString());
        getLogger().debug(query);
        try {
            TuxJSQL.getConnection().createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void insert(List<Column> columns, Object... values) {
        StringBuilder columnsToInsert = new StringBuilder();
        StringBuilder question = new StringBuilder();
        for (Column column : columns) {
            if (!columnsToInsert.toString().isEmpty()) {
                columnsToInsert.append(",");
                question.append(",");
            }
            columnsToInsert.append(column.getName());
            question.append("?");
        }
        String query = String.format(SQLiteQuery.INSERT.getQuery(), name, columnsToInsert.toString(), question.toString());
        try (PreparedStatement preparedStatement = TuxJSQL.getConnection().prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    @Override
    public DBResult select(SelectStatement statement) {
        Query sqlQuery = statement.build(this);
        ResultSet resultSet = null;
        List<DBRow> rows = null;
        try {
            getLogger().debug(sqlQuery.getQuery());
            getLogger().debug(sqlQuery.getValuesAsString());
            PreparedStatement preparedStatement = TuxJSQL.getConnection().prepareStatement(sqlQuery.getQuery());
            if (sqlQuery.getValues() != null||sqlQuery.getValues().length == 0) {
                for (int i = 0; i < sqlQuery.getValues().length; i++) {
                    preparedStatement.setObject(i + 1, sqlQuery.getValues()[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();

            rows = resultSetToResultRow(resultSet, statement.getColumns().size());
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DBResult(rows.size(), statement.getColumns().size(), this, rows);
    }



    @Override
    public void delete(WhereStatement whereStatement) {
        String query = String.format(SQLiteQuery.DELETE.getQuery(), name, whereStatement.build());
        try (PreparedStatement preparedStatement = TuxJSQL.getConnection().prepareStatement(query)) {
            Object[] values = whereStatement.values();
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            preparedStatement.execute();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void drop() {
        try {
            TuxJSQL.getConnection().createStatement().execute(String.format(SQLiteQuery.DROP_TABLE.getQuery(), getName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeSimpleStatement(String statement) {
        try {
            TuxJSQL.getConnection().createStatement().execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropColumn(Column column) {
        if (!columns.contains(column)) {
            throw new IllegalArgumentException("Column Does not exist!");
        }
        columns.remove(column);
        executeSimpleStatement(String.format(SQLiteQuery.DROP_COLUMN.getQuery(), name, column.getName()));
    }

    @Override
    public void addColumn(Column column) {
        executeSimpleStatement(String.format(SQLiteQuery.ADD_COLUMN.getQuery(), name, column.build()));
        SQLITEColumn column1 = (SQLITEColumn) column;
        column1.setTable(this);
        columns.add(column1);
    }

    @Override
    public void modifyColumn(Column column) {
        if (!columns.contains(column)) {
            throw new IllegalArgumentException("Column Does not exist!");
        }
        executeSimpleStatement(String.format(SQLiteQuery.MODIFY_COLUMN.getQuery(), name, column.build()));
        //Update the Object :)

        columns.remove(column);
        SQLITEColumn column1 = (SQLITEColumn) column;
        column1.setTable(this);
        columns.add(column1);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SQLiteTable)) return false;
        return name.equals(((SQLiteTable) obj).getName());
    }
}
