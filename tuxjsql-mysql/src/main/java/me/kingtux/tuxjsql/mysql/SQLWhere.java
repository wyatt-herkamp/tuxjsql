package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.Where;

public class SQLWhere implements Where {
    private String where;

    public SQLWhere(String where) {
        this.where = where;
    }

    @Override
    public String build() {
        return where + "=?";
    }
}
