package me.kingtux.tuxjsql.sqlite;

import me.kingtux.tuxjsql.core.CommonDataTypes;
import me.kingtux.tuxjsql.core.DataType;

import java.util.Arrays;

public enum SQLITEDataTypes implements DataType {

    INTEGER(CommonDataTypes.INT, CommonDataTypes.BIGINT,CommonDataTypes.TINYINT, CommonDataTypes.TINYINT),
    TEXT(CommonDataTypes.TEXT, CommonDataTypes.VARCHAR),
    REAL(CommonDataTypes.DOUBLE);
    private CommonDataTypes[] cdt;

    SQLITEDataTypes(CommonDataTypes... types) {
        cdt = types;
    }

    static SQLITEDataTypes getType(CommonDataTypes type) {
        for (SQLITEDataTypes sqliteDataTypes : values()) {
            if (Arrays.asList(sqliteDataTypes.cdt).contains(type)) {
                return sqliteDataTypes;
            }
        }
        return null;
    }

    @Override
    public String type() {
        return name();
    }
}
