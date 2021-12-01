package me.kingtux.tuxjsql.basic.sql;

import me.kingtux.tuxjsql.basic.sql.where.BasicWhereStatement;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.sql.DeleteStatement;
import me.kingtux.tuxjsql.core.sql.SQLTable;
import me.kingtux.tuxjsql.core.sql.where.WhereStatement;

import java.util.function.Consumer;

public abstract class BasicDeleteStatement implements DeleteStatement {
    protected SQLTable table;
    protected WhereStatement<DeleteStatement> whereStatement;
    protected TuxJSQL tuxJSQL;

    public BasicDeleteStatement(TuxJSQL tuxJSQL) {
        this.tuxJSQL = tuxJSQL;
        whereStatement = tuxJSQL.createWhere(this);
    }

    @Override
    public WhereStatement<DeleteStatement> where() {
        return whereStatement;
    }

    @Override
    public DeleteStatement where(WhereStatement whereStatement) {
        this.whereStatement = whereStatement == null ? tuxJSQL.createWhere() : whereStatement;
        ((BasicWhereStatement<DeleteStatement>) this.whereStatement).setAnd(this);
        return this;
    }

    @Override
    public DeleteStatement where(Consumer<WhereStatement<DeleteStatement>> whereStatement) {
        whereStatement.accept(this.whereStatement);

        return this;
    }

    @Override
    public DeleteStatement setTable(SQLTable basicSQLTable) {
        this.table = basicSQLTable;
        return this;
    }
}
