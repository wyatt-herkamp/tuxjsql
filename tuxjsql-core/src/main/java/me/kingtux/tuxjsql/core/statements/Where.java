package me.kingtux.tuxjsql.core.statements;

public class Where {
    private String where;

    public Where(String where) {
        this.where = where;
    }

    public String build() {
        return where + "=?";
    }
}
