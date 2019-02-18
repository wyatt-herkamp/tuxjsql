package me.kingtux.tuxjsql.mysql;

public enum Querie {
    INSERT("INSERT INTO %1$s (%2$s) VALUES (%3$s);"),
    SELECT("SELECT %1$s FROM %2$s"),
    WHERE("WHERE %1$s"),
    DELETE("DELETE FROM %1$s WHERE %2$s"),
    UPDATE("UPDATE %1$s SET %2$s WHERE %3$s");


    private String query;

    Querie(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
