package me.kingtux.tuxjsql.sqlite;

import me.kingtux.tuxjsql.core.Where;
@SuppressWarnings("Duplicates")
public class SQLITEWhere implements Where {
    private String where;

     SQLITEWhere(String where) {
        this.where = where;
    }

    @Override
    public String build() {
        return where + "=?";
    }
}
