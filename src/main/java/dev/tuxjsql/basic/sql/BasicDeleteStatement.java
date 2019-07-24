package dev.tuxjsql.basic.sql;

import dev.tuxjsql.basic.sql.where.BasicWhereStatement;
import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.sql.DeleteStatement;
import dev.tuxjsql.core.sql.SQLTable;
import dev.tuxjsql.core.sql.UpdateStatement;
import dev.tuxjsql.core.sql.select.SelectStatement;
import dev.tuxjsql.core.sql.where.WhereStatement;

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
        this.whereStatement = whereStatement;
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
