package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.ColumnType;
import me.kingtux.tuxjsql.core.TuxJSQL;

public class MySQLColumn implements Column {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isUnique() {
        return false;
    }

    @Override
    public boolean isPrimary() {
        return false;
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public ColumnType getType() {

        return null;
    }
}
