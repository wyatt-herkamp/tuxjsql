package dev.tuxjsql.core.response;

import dev.tuxjsql.core.sql.QueryType;

import java.util.List;

public interface DBSelect extends Iterable<DBRow>{


    List<DBRow> getRows();

    DBRow get(int i);

    DBRow first();

    int numberOfRows();

    boolean successful();
}
