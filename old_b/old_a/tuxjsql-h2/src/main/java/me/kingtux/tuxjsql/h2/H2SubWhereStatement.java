package me.kingtux.tuxjsql.h2;

import me.kingtux.tuxjsql.core.Query;
import me.kingtux.tuxjsql.core.statements.SubWhereStatement;
import me.kingtux.tuxjsql.core.statements.Where;

@SuppressWarnings("Duplicates")
public class H2SubWhereStatement extends SubWhereStatement {
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
