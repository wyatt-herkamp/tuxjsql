package dev.tuxjsql.basic.builders;

import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.builders.SQLBuilder;

public abstract class BasicSQLBuilder implements SQLBuilder {
    protected TuxJSQL tuxJSQL;

    public TuxJSQL getTuxJSQL() {
        return tuxJSQL;
    }

    @Override
    public void setTuxJSQL(TuxJSQL tuxJSQL) {
        this.tuxJSQL = tuxJSQL;
    }


}
