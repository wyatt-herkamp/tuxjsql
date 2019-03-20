package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.Query;
import me.kingtux.tuxjsql.core.statements.SubWhereStatement;
import me.kingtux.tuxjsql.core.statements.Where;

import java.util.List;
@SuppressWarnings("Duplicates")
public class SQLSubWhereStatement extends SubWhereStatement {
    @Override
    public Query build() {
        StringBuilder builder = new StringBuilder();
        for (Object object : items) {
            if (object instanceof SubWhereStatement) {
                builder.append(((SubWhereStatement) object).build());
            } else if (object instanceof Where) {
                builder.append(((Where) object).build());
            } else if (object instanceof String) {
                builder.append(object);
            }
        }

        return new Query(builder.toString(), values());
    }

}
