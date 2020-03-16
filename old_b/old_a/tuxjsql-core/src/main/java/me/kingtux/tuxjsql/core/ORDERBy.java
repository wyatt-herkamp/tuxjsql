package me.kingtux.tuxjsql.core;

public enum ORDERBy {
    ASCENDING("ASC"),
    DESCENDING("DESC");


    private String key;

    ORDERBy(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
