package me.kingtux.tuxjsql.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this represents the WHERE part of a SQL statement
 * @author KingTux
 */
public interface WhereStatement {

    WhereStatement start(String s, Object value);

    WhereStatement start(SubWhereStatement s);

    WhereStatement AND(String s, Object value);

    WhereStatement AND(SubWhereStatement s);

    WhereStatement OR(String s, Object value);

    WhereStatement OR(SubWhereStatement s);

    WhereStatement NOT(String s, Object value);

    WhereStatement NOT(SubWhereStatement s);

    Object[] getValues();

    String build();

    default Object[] values() {
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
