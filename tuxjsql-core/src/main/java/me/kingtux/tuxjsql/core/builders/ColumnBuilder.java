package me.kingtux.tuxjsql.core.builders;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.ColumnType;
import me.kingtux.tuxjsql.core.DataType;
import me.kingtux.tuxjsql.core.TuxJSQL;

import java.util.Arrays;
import java.util.List;

/**
 * The Column SQLBuilder
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

    static ColumnBuilder create() {
        return TuxJSQL.getSQLBuilder().createColumn();
    }
}
