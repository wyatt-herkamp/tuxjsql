package me.kingtux.tuxjsql.core.response;

import java.util.List;
import java.util.Optional;

public interface DBRow {


    Optional<DBColumnItem> getColumn(String name);

    Optional<DBColumnItem> getColumn(int i);

    List<DBColumnItem> getColumns();

    boolean doesColumnExist(String name);

    int numberOfColumns();
}
