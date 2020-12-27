package me.kingtux.tuxjsql.basic.sql.where;

public class BasicWhereResponse {
    private String query;
    private Object[] values;

    protected BasicWhereResponse(String query, Object[] values) {
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
