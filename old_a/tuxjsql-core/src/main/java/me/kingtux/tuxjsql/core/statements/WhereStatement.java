package me.kingtux.tuxjsql.core.statements;

import me.kingtux.tuxjsql.core.Query;
import me.kingtux.tuxjsql.core.TuxJSQL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this represents the WHERE part of a SQL statement
 * @author KingTux
 */
public abstract class WhereStatement {
    protected List<Object> objects = new ArrayList<>();
    protected List<Object> items = new ArrayList<>();

    public static WhereStatement create() {
        return TuxJSQL.getSQLBuilder().createWhere();
    }


    public WhereStatement start(String s, Object value) {
        objects.add(value);
        items.add(new Where(s));
        return this;
    }


    public WhereStatement start(SubWhereStatement s) {
        items.add(s);
        return this;
    }


    public WhereStatement AND(String s, Object value) {
        objects.add(value);
        items.add("AND");
        items.add(new Where(s));
        return this;
    }


    public WhereStatement AND(SubWhereStatement s) {
        items.add("AND");
        items.add(s);
        return this;
    }


    public WhereStatement OR(String s, Object value) {
        objects.add(value);
        items.add("OR");

        items.add(new Where(s));
        return this;
    }


    public WhereStatement OR(SubWhereStatement s) {
        items.add("OR");

        items.add(s);
        return this;
    }


    public WhereStatement NOT(String s, Object value) {
        objects.add(value);
        items.add("NOT");

        items.add(new Where(s));
        return this;
    }


    public WhereStatement NOT(SubWhereStatement s) {
        items.add("NOT");
        items.add(s);
        return this;
    }


    public Object[] getValues() {
        return objects.toArray();
    }

    public abstract Query build();


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
}
