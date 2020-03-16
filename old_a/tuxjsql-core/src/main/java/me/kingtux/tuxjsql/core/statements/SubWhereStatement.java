package me.kingtux.tuxjsql.core.statements;

import me.kingtux.tuxjsql.core.Query;
import me.kingtux.tuxjsql.core.TuxJSQL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("All")
public abstract class SubWhereStatement {
    protected List<Object> objects = new ArrayList<>();
    protected List<Object> items = new ArrayList<>();

    static SubWhereStatement create() {
        return TuxJSQL.getSQLBuilder().createSubWhere();
    }

    public SubWhereStatement start(String s, Object value) {
        objects.add(value);
        items.add(new Where(s));
        return this;
    }

    public SubWhereStatement start(SubWhereStatement s) {
        items.add(s);
        return this;
    }

    public SubWhereStatement AND(String s, Object value) {
        objects.add(value);
        items.add("AND");
        items.add(new Where(s));
        return this;
    }

    public SubWhereStatement AND(SubWhereStatement s) {
        items.add("AND");
        items.add(s);
        return this;
    }

    public SubWhereStatement OR(String s, Object value) {
        objects.add(value);
        items.add("OR");

        items.add(new Where(s));
        return this;
    }

    public SubWhereStatement OR(SubWhereStatement s) {
        items.add("OR");

        items.add(s);
        return this;
    }

    public SubWhereStatement NOT(String s, Object value) {
        objects.add(value);
        items.add("NOT");

        items.add(new Where(s));
        return this;
    }

    public SubWhereStatement NOT(SubWhereStatement s) {
        items.add("NOT");
        items.add(s);
        return this;
    }

    public Object[] getValues() {
        return objects.toArray();
    }

    public Object[] values() {
        List<Object> values = new ArrayList<>();
        for (Object object : getValues()) {
            if (object instanceof SubWhereStatement) {
                values.addAll(Arrays.asList(((SubWhereStatement) object).values()));
            } else {
                values.add(object);
            }
        }
        return values.toArray();
    }

    public abstract Query build();
}
