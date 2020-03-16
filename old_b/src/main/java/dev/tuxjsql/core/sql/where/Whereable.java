package dev.tuxjsql.core.sql.where;


import java.util.function.Consumer;

/**
 * If this statement has the ability to use WHERE it will implement this
 *
 * @param <T> the and() type
 */
public interface Whereable<T> {
    /**
     * Where that returns an object
     *
     * @return the whereObject
     */
    WhereStatement<T> where();

    /**
     * This takes in your where clause
     * @param whereStatement the whereclause
     * @return your parentObject
     */
    T where(WhereStatement whereStatement);

    /**
     * this takes a consumer to add things to your where
     * @param whereStatement the consumer
     * @return your parent object
     */
    T where(Consumer<WhereStatement<T>> whereStatement);
}
