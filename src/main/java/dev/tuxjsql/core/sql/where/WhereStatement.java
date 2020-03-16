package dev.tuxjsql.core.sql.where;

import java.util.function.Consumer;

/**
 * This represents the WHERE part of a sql query.
 *
 *
 * @param <T> This is optional. This is the type for the and() method
 */
public interface WhereStatement<T> {

    WhereStatement<T> start(String s, String comparator, Object value);
    WhereStatement<T> start(String s,Object value);

    WhereStatement<T> start(SubWhereStatement s);

    WhereStatement<T> start(Consumer<SubWhereStatement> s);

    SubWhereStatement<WhereStatement> start();

   WhereStatement<T> AND(String s, String comparator, Object value);
   WhereStatement<T> AND(String s, Object value);

   WhereStatement<T> AND(SubWhereStatement s);

   WhereStatement<T> AND(Consumer<SubWhereStatement> s);

    SubWhereStatement<WhereStatement> AND();

   WhereStatement<T> OR(String s, String comparator, Object value);
   WhereStatement<T> OR(String s,  Object value);

   WhereStatement<T> OR(SubWhereStatement s);

   WhereStatement<T> OR(Consumer<SubWhereStatement> s);

    SubWhereStatement<WhereStatement> OR();

   WhereStatement<T> NOT(String s, String comparator, Object value);
   WhereStatement<T> NOT(String s, Object value);

   WhereStatement<T> NOT(SubWhereStatement s);

   WhereStatement<T> NOT(Consumer<SubWhereStatement> s);

    SubWhereStatement<WhereStatement> NOT();

    /**
     * This does a return to the previus object.
     * @return previos object
     */
    T and();


    String getQuery();

    Object[] getValues();
}
