package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.Where;

public class MysqlWhere implements Where {
    private String where;

    public MysqlWhere(String where) {
        this.where = where;
    }

    @Override
    public String build() {
        return where + "=?";
    }
}
