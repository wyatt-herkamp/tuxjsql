package me.kingtux.tuxjsql.mysql;

public enum Querie {
    INSERT("INSERT INTO %1$s (%2$s) VALUES (%3$s);");


    private String query;

    Querie(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
