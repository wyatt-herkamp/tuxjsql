package me.kingtux.tuxjsql.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("All")
public interface SubWhereStatement {

    SubWhereStatement start(String s, Object value);

    SubWhereStatement start(SubWhereStatement s);

    SubWhereStatement AND(String s, Object value);

    SubWhereStatement AND(SubWhereStatement s);

    SubWhereStatement OR(String s, Object value);

    SubWhereStatement OR(SubWhereStatement s);

    SubWhereStatement NOT(String s, Object value);

    SubWhereStatement NOT(SubWhereStatement s);
    Object[] getValues();



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

    String build();
}
