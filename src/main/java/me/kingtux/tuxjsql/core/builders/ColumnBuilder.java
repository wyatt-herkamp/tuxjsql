package me.kingtux.tuxjsql.core.builders;

import me.kingtux.tuxjsql.core.sql.SQLColumn;
import me.kingtux.tuxjsql.core.sql.SQLDataType;

/**
 * This is a layout for the ColumnBuilder.
 *
 * @param <T> the and() type
 */
public interface ColumnBuilder<T> {

    ColumnBuilder<T> setDataType(SQLDataType type);

    ColumnBuilder<T> addDataTypeRule(String s);

    ColumnBuilder<T> primaryKey();

    ColumnBuilder<T> defaultValue(Object defaultValue);

    ColumnBuilder<T> notNull();
    ColumnBuilder<T> unique();

    ColumnBuilder<T> autoIncrement();

    ColumnBuilder<T> name(String name);


    /**
     * If you want this column to reference another column
     *
     * @param otherColumn the other column
     * @return The ColumnBuilder
     */
    ColumnBuilder<T> foreignColumn(SQLColumn otherColumn);

    SQLColumn build();

    T and();
}
