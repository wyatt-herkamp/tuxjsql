package dev.tuxjsql.core.response;

import java.util.List;
import java.util.Optional;

public interface DBRow {
    /**
     * Will be removed before release
     * @param name name of column
     * @return the column
     */
    @Deprecated
    DBColumnItem getRow(String name);

    Optional<DBColumnItem> getColumn(String name);

    Optional<DBColumnItem> getColumn(int i);

    List<DBColumnItem> getColumns();

    boolean doesColumnExist(String name);

    int numberOfColumns();
}
