package dev.tuxjsql.core;

import dev.tuxjsql.core.builders.SQLBuilder;
import dev.tuxjsql.core.tools.SimpleSupplier;

public class SQLBuilderSupplier implements SimpleSupplier<SQLBuilder> {
    private final Class<?> clazz;

    public SQLBuilderSupplier(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public SQLBuilder get() throws Exception {
        return (SQLBuilder) clazz.getConstructor().newInstance();
    }
}
