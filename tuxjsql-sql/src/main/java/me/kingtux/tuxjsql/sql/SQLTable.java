package me.kingtux.tuxjsql.sql;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.WhereStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLTable implements Table {
    private String name;
    private List<Column> columns;

    public SQLTable(String name, List<Column> columns) {
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
        String query = String.format(SQLQuery.UPDATE.getQuery(), name, columsToUpdate, whereStatement.build());
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
    public void createIfNotExists() {
        StringBuilder builder = new StringBuilder();
        for (Column column : columns) {
            if (!builder.toString().isEmpty()) {
                builder.append(", ");
            }
            builder.append(column.build());
        }
        String query = String.format(SQLQuery.TABLE.getQuery(), name, builder.toString());
        try {
            TuxJSQL.getConnection().createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        String query = String.format(SQLQuery.INSERT.getQuery(), name, columnsToInsert.toString(), question.toString());
        System.out.println(query);
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
    public ResultSet select(WhereStatement whereStatement, List<Column> columns) {
        ResultSet resultSet = null;
        StringBuilder columnsToSelect = new StringBuilder();
        for (Column column : columns) {
            if (!columnsToSelect.toString().isEmpty()) {
                columnsToSelect.append(",");
            }
            columnsToSelect.append(column.getName());
        }
        String query = String.format(SQLQuery.SELECT.getQuery(), columnsToSelect.toString(), name);
        if (whereStatement != null) {
            query += " " + String.format(SQLQuery.WHERE.getQuery(), whereStatement.build());
        }
        System.out.println(query);
        try {
            PreparedStatement preparedStatement = TuxJSQL.getConnection().prepareStatement(query);
            if (whereStatement != null) {
                Object[] values = whereStatement.values();
                for (int i = 0; i < values.length; i++) {
                    preparedStatement.setObject(i + 1, values[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public void delete(WhereStatement whereStatement) {
        String query = String.format(SQLQuery.DELETE.getQuery(), name, whereStatement.build());
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

}
