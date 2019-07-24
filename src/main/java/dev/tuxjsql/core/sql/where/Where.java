package dev.tuxjsql.core.sql.where;

public interface Where {
    String getKey();

    String getComparator();

    Object getValue();
}
