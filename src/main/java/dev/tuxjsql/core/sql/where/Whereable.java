package dev.tuxjsql.core.sql.where;


import java.util.function.Consumer;

public interface Whereable<T> {
    WhereStatement<T> where();

    T where(WhereStatement whereStatement);

    T where(Consumer<WhereStatement<T>> whereStatement);
}
