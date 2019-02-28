package me.kingtux.tuxjsql.core;

import java.util.Arrays;
import java.util.List;

/**
 * The Column Builder
 */
public interface ColumnBuilder {
    /**
     * The NOT NULL Status
     * @param notNull is it not null
     * @return is it not null
     */
    ColumnBuilder notNull(boolean notNull);

    default ColumnBuilder notNull() {
        return notNull(true);
    }

    ColumnBuilder name(String s);

    ColumnBuilder autoIncrement(boolean autoIncrement);

    ColumnBuilder primary(boolean primary);

    ColumnBuilder unique(boolean unique);

    ColumnBuilder defaultValue(Object value);

    ColumnBuilder type(ColumnType value);

    default ColumnBuilder type(DataType value, List<String> rules) {
        return type(new ColumnType(value, rules));
    }

    default ColumnBuilder type(DataType value, String... rules) {
        return type(new ColumnType(value, Arrays.asList(rules)));
    }

    default ColumnBuilder type(DataType value) {
        return type(new ColumnType(value));
    }


    Column build();

}
