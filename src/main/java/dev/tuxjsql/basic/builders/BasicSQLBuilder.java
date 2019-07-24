package dev.tuxjsql.basic.builders;

import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.builders.SQLBuilder;
import dev.tuxjsql.core.sql.SQLAction;

public abstract class BasicSQLBuilder implements SQLBuilder {
    protected TuxJSQL tuxJSQL;

    public TuxJSQL getTuxJSQL() {
        return tuxJSQL;
    }

    @Override
    public void setTuxJSQL(TuxJSQL tuxJSQL) {
        this.tuxJSQL = tuxJSQL;
    }

    @Override
    public boolean supportsAction(SQLAction actions) {
        for (SQLAction action : supportedActions()) {
            if (action == actions) {
                return true;
            }
        }
        return false;
    }
}
