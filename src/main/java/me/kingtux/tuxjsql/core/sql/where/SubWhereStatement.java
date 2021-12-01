package me.kingtux.tuxjsql.core.sql.where;

import java.util.function.Consumer;

public interface SubWhereStatement<T> {
    SubWhereStatement<T> start(String s,String comparator, Object value);

    SubWhereStatement<T> start(String s, Object value);

    SubWhereStatement<T> start(SubWhereStatement s);

    SubWhereStatement<T> start(Consumer<SubWhereStatement> s);

    SubWhereStatement<SubWhereStatement> start();

    SubWhereStatement<T> AND(String s, String comparator,Object value);

    SubWhereStatement<T> AND(String s, Object value);

    SubWhereStatement<T> AND(SubWhereStatement s);

    SubWhereStatement<T> AND(Consumer<SubWhereStatement> s);

    SubWhereStatement<SubWhereStatement> AND();

    SubWhereStatement<T> OR(String s, String comparator,Object value);

    SubWhereStatement<T> OR(String s, Object value);

    SubWhereStatement<T> OR(SubWhereStatement s);

    SubWhereStatement<T> OR(Consumer<SubWhereStatement> s);

    SubWhereStatement<SubWhereStatement> OR();

    SubWhereStatement<T> NOT(String s, String comparator,Object value);

    SubWhereStatement<T> NOT(String s, Object value);

    SubWhereStatement<T> NOT(SubWhereStatement s);

    SubWhereStatement<T> NOT(Consumer<SubWhereStatement> s);

    SubWhereStatement<SubWhereStatement> NOT();


    T and();

    String getQuery();

    Object[] getValues();
}
