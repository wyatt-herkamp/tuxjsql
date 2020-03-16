package dev.tuxjsql.basic.sql.select;

import dev.tuxjsql.basic.sql.where.BasicWhereStatement;
import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.sql.SQLColumn;
import dev.tuxjsql.core.sql.SQLTable;
import dev.tuxjsql.core.sql.select.JoinStatement;
import dev.tuxjsql.core.sql.select.SelectStatement;
import dev.tuxjsql.core.sql.where.WhereStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class BasicSelectStatement implements SelectStatement {

    protected JoinStatement join;
    protected List<SQLColumn> columns = new ArrayList<>();
    protected SQLTable table;
    protected int limit = 0;
    protected TuxJSQL tuxJSQL;
    protected WhereStatement<SelectStatement> whereStatement;

    @Override
    public SelectStatement where(WhereStatement whereStatement) {
        this.whereStatement = whereStatement == null ? tuxJSQL.createWhere() : whereStatement;
        ((BasicWhereStatement<SelectStatement>) this.whereStatement).setAnd(this);
        return this;
    }

    public BasicSelectStatement(TuxJSQL tuxJSQL) {
        this.tuxJSQL = tuxJSQL;
        whereStatement = tuxJSQL.createWhere(this);
        join = tuxJSQL.createJoinStatement(this);
    }

    @Override
    public WhereStatement<SelectStatement> where() {
        return whereStatement;
    }

    @Override
    public SelectStatement where(Consumer<WhereStatement<SelectStatement>> consumer) {
        consumer.accept(whereStatement);
        return this;
    }

    @Override
    public SelectStatement limit(int i) {
        limit = i;
        return this;
    }

    @Override
    public SelectStatement column(String s) {
        columns.add(table.getColumn(s));
        return this;
    }

    @Override
    public SelectStatement column(String... s) {
        Stream.of(s).forEach(this::column);
        return this;
    }

    @Override
    public SelectStatement column(SQLColumn s) {
        columns.add(s);
        return this;
    }

    @Override
    public JoinStatement join() {
        return join;
    }

    @Override
    public SelectStatement join(Consumer<JoinStatement> consumer) {
        consumer.accept(join);
        return this;
    }


    @Override
    public SelectStatement setTable(SQLTable basicSQLTable) {
        table = basicSQLTable;
        return this;
    }
}
