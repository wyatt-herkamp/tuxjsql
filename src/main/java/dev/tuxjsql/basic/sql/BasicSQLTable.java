package dev.tuxjsql.basic.sql;
import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.sql.*;
import dev.tuxjsql.core.sql.select.SelectStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BasicSQLTable implements SQLTable {
    protected TuxJSQL tuxJSQL;
    protected String name;
    protected List<SQLColumn> sqlColumns;


    public BasicSQLTable(TuxJSQL tuxJSQL, String name, List<SQLColumn> sqlColumns) {
        this.tuxJSQL = tuxJSQL;
        this.name = name;
        this.sqlColumns = sqlColumns;
        this.sqlColumns.forEach(column -> {
            ((BasicSQLColumn)column).setTable(this);
        });
    }

    /**
     * Prepare the Table on the database side :)
     *
     * */
    public abstract void prepareTable();

    @Override
    public SelectStatement select() {
        return tuxJSQL.createSelectStatement().setTable(this);
    }

    @Override
    public UpdateStatement update() {
        return tuxJSQL.getBuilder().createUpdateStatement().setTable(this);
    }

    @Override
    public DeleteStatement delete() {
        return tuxJSQL.getBuilder().createDeleteStatement().setTable(this);
    }

    @Override
    public InsertStatement insert() {
        return tuxJSQL.getBuilder().createInsertStatement().setTable(this);
    }

    @Override
    public void executeStatement(String string) {
        TuxJSQL.getLogger().debug(string);
        try (Connection connection = tuxJSQL.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(string);
            }
        } catch (SQLException e) {
            TuxJSQL.getLogger().error("Unable to execute " + string, e);
        }
    }

    public TuxJSQL getTuxJSQL() {
        return tuxJSQL;
    }

    public List<SQLColumn> getSqlColumns() {
        return sqlColumns;
    }

    @Override
    public SQLColumn getColumn(String s) {
        for (SQLColumn column : sqlColumns) {
            if (column.getName().equalsIgnoreCase(s)) {
                return column;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
