package dev.tuxjsql.core.response;

import java.util.List;
import java.util.Optional;

public interface DBSelect extends Iterable<DBRow>, DBResult{


    List<DBRow> getRows();

    DBRow get(int i);

    Optional<DBRow> first();


}
