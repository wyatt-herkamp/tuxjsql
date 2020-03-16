package dev.tuxjsql.basic.sql.select;

import dev.tuxjsql.core.sql.SQLColumn;
import dev.tuxjsql.core.sql.select.JoinStatement;
import dev.tuxjsql.core.sql.select.JoinType;
import dev.tuxjsql.core.sql.select.SelectStatement;

public class BasicJoinStatement implements JoinStatement {
    protected SelectStatement selectStatement;
    protected JoinType joinType = null;
    protected String tableOneColumn = null;
    protected SQLColumn tableTwo;

    public BasicJoinStatement(SelectStatement selectStatement) {
        this.selectStatement = selectStatement;
    }

    @Override

    public JoinStatement on(String tableOneColumn, SQLColumn sqlColumn) {
        this.tableOneColumn = tableOneColumn;
        tableTwo = sqlColumn;
        return this;
    }


    @Override
    public SelectStatement build() {
        return selectStatement;
    }

    @Override
    public JoinStatement joinType(JoinType type) {
        joinType = type;
        return this;
    }

    public SelectStatement getSelectStatement() {
        return selectStatement;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public String getTableOneColumn() {
        return tableOneColumn;
    }

    public SQLColumn getTableTwo() {
        return tableTwo;
    }
}
