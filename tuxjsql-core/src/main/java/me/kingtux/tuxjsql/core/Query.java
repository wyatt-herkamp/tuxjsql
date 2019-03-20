package me.kingtux.tuxjsql.core;

public class Query {
    private String query;
    private Object[] values;

    public Query(String query, Object[] values) {
        this.query = query;
        this.values = values;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
