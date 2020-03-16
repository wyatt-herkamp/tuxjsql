package dev.tuxjsql.basic.sql;

import dev.tuxjsql.basic.utils.BasicUtils;
import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.sql.InsertStatement;
import dev.tuxjsql.core.sql.SQLTable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class BasicInsertStatement implements InsertStatement {
    protected Map<String, Object> values = new HashMap<>();
    protected SQLTable table;
    protected TuxJSQL tuxJSQL;

    public BasicInsertStatement(TuxJSQL tuxJSQL) {
        this.tuxJSQL = tuxJSQL;
    }

    @Override
    public InsertStatement values(Map<String, Object> map) {
        map.forEach(this::value);
        return this;
    }


    @Override
    public InsertStatement value(String string, Object o) {
        if (o instanceof Enum) {
            values.put(string, BasicUtils.enumToString((Enum) o));

        } else if (o instanceof UUID) {
            values.put(string, o.toString());
        } else {
            values.put(string, o);
        }
        return this;
    }

    @Override
    public InsertStatement setTable(SQLTable basicSQLTable) {
        table = basicSQLTable;
        return this;
    }
}
