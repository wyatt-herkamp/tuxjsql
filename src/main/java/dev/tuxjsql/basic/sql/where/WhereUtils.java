package dev.tuxjsql.basic.sql.where;

import dev.tuxjsql.core.sql.SQLTable;
import dev.tuxjsql.core.sql.where.SubWhereStatement;
import dev.tuxjsql.core.sql.where.Where;

import java.util.*;

public class WhereUtils {
    public static BasicWhereResponse doubleBuild(Object[] whereObjects, SQLTable table) {
        List<Object> values = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (Object object : whereObjects) {
            if (object instanceof WhereSeperator) {
                builder.append(((WhereSeperator) object).name()).append(" ");
            } else if (object instanceof Where) {
                Where where = (Where) object;
                values.add(((Where) object).getValue());
                if (table == null) {
                    builder.append("`").append(where.getKey()).append("`")
                            .append(where.getComparator())
                            .append("?")
                            .append(" ");
                } else {
                    builder.append(table.getName()).append(".").append(where.getKey())
                            .append(where.getComparator())
                            .append("?")
                            .append(" ");
                }
            } else if (object instanceof SubWhereStatement) {
                BasicSubWhereStatement subWhereStatement = (BasicSubWhereStatement) object;
                subWhereStatement.setTable(table);
                values.addAll(Arrays.asList(subWhereStatement.getValues()));
                builder.append(subWhereStatement.getQuery()).append(" ");
            }
        }
        return new BasicWhereResponse(builder.toString(), values.toArray());
    }

}
