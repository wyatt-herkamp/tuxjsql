package me.kingtux.tuxjsql.core;

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

    WhereStatement NOT(SubWhereStatement s, Object value);


}
