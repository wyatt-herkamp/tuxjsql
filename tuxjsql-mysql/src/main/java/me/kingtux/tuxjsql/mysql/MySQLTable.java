package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.WhereStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLTable implements Table {
    private String name;
    private List<Column> columns;

    public MySQLTable(String name, List<Column> columns) {
        this.name = name;
        this.columns = columns;
    }

    @Override
    public List<Column> getColumns() {
        return columns;
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
        String query = String.format(Querie.INSERT.getQuery(), name, columnsToInsert.toString(), question.toString());
        try {
            PreparedStatement preparedStatement = TuxJSQL.getConnection().prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i, values[i]);
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ResultSet select(WhereStatement whereStatement, List<Column> columns) {

        return null;
    }

    @Override
    public void update(WhereStatement whereStatement, List<Column> columns, Object... values) {

    }

    @Override
    public void delete(WhereStatement whereStatement) {

    }
}
