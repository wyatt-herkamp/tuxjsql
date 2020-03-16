package dev.tuxjsql.basic.sql;

import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.response.*;
import dev.tuxjsql.core.sql.*;
import dev.tuxjsql.core.sql.select.SelectStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BasicSQLTable implements SQLTable {
    protected TuxJSQL tuxJSQL;
    protected String name;
    protected List<SQLColumn> sqlColumns;


    public BasicSQLTable(TuxJSQL tuxJSQL, String name, List<SQLColumn> sqlColumns) {
        this.tuxJSQL = tuxJSQL;
        this.name = name;
        this.sqlColumns = sqlColumns;
        this.sqlColumns.forEach(column -> {
            ((BasicSQLColumn) column).setTable(this);
        });
    }

    /**
     * Prepare the Table on the database side :)
     */
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

    @Override
    public List<SQLColumn> getColumns() {
        return new ArrayList<>(sqlColumns);
    }

    @Override
    public SQLColumn getPrimaryColumn() {
        return sqlColumns.stream().filter(SQLColumn::primaryKey).findFirst().orElse(null);
    }

    @Override
    public DBAction<DBSelect> select(Object primaryKey) {
        return select(getPrimaryColumn(), primaryKey);
    }

    @Override
    public DBAction<DBUpdate> update(Object primaryKey, Map<?, Object> values) {
        UpdateStatement updateStatement = update().where().start(getPrimaryColumn().getName(), primaryKey).and();
        values.forEach((o, o2) -> updateStatement.value(nameOrIt(o), o2));
        return updateStatement.execute();
    }

    @Override
    public DBAction<DBInsert> insert(Map<?, Object> values) {
        InsertStatement insert = insert();
        values.forEach((o, o2) -> insert.value(nameOrIt(o), o2));
        return insert.execute();
    }

    public static String nameOrIt(Object o) {
        String name;
        if (o instanceof SQLColumn) {
            name = ((SQLColumn) o).getName();
        } else {
            name = (String) o;
        }
        return name;
    }

    @Override
    public DBAction<DBSelect> select(Object columnToLookFor, Object value) {
        return select().where().start(nameOrIt(columnToLookFor), value).and().execute();
    }

    @Override
    public DBAction<DBDelete> delete(Object primarykey) {
        return delete().where().start(getPrimaryColumn().getName(), primarykey).and().execute();
    }

}
