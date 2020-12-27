package me.kingtux.tuxjsql.basic.builders;

import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;

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
