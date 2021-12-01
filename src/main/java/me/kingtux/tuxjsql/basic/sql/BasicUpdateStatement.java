package me.kingtux.tuxjsql.basic.sql;

import me.kingtux.tuxjsql.basic.sql.where.BasicWhereStatement;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.sql.SQLTable;
import me.kingtux.tuxjsql.core.sql.UpdateStatement;
import me.kingtux.tuxjsql.core.sql.where.WhereStatement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class BasicUpdateStatement implements UpdateStatement {
    protected SQLTable sqlTable;
    protected WhereStatement<UpdateStatement> whereStatement;
    protected Map<String, Object> values = new HashMap<>();
    protected TuxJSQL tuxJSQL;
    public BasicUpdateStatement(TuxJSQL tuxJSQL) {
        whereStatement = tuxJSQL.createWhere(this);
        this.tuxJSQL = tuxJSQL;
    }

    @Override
    public UpdateStatement value(String key, Object value) {
        values.put(key, value);
        return this;
    }

    @Override
    public WhereStatement<UpdateStatement> where() {
        return whereStatement;
    }
    @Override
    public UpdateStatement where(WhereStatement whereStatement) {
        this.whereStatement = whereStatement == null ? tuxJSQL.createWhere() : whereStatement;
        ((BasicWhereStatement<UpdateStatement>) this.whereStatement).setAnd(this);
        return this;
    }

    @Override
    public UpdateStatement where(Consumer<WhereStatement<UpdateStatement>> consumer) {
        consumer.accept(whereStatement);
        return this;
    }


    @Override
    public UpdateStatement setTable(SQLTable basicSQLTable) {
        this.sqlTable = basicSQLTable;
        return this;
    }
}
