package me.kingtux.tuxjsql.core;

import me.kingtux.tuxjsql.core.builders.SQLBuilder;
import me.kingtux.tuxjsql.core.connection.CPProvider;
import me.kingtux.tuxjsql.core.connection.ConnectionProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class will be merged into TuxJSQLBuilder but currently we are keeping them separate for testing and coding purposes
 */
public class CTuxJSQLBuilder {
    private CTuxJSQLBuilder() {

    }

    public static TuxJSQL create(Configuration properties) throws Exception {
        return create(properties, getExecutorsService(properties.getThreadPoolSize()));
    }

    private static ExecutorService getExecutorsService(int size) {
        if (size > 1) {
            return Executors.newFixedThreadPool(size);
        } else {
            return Executors.newSingleThreadExecutor();
        }
    }

    public static TuxJSQL create(Configuration properties, ClassLoader classLoader) throws Exception {
        return create(properties, getExecutorsService(properties.getThreadPoolSize()), classLoader);
    }


    public static TuxJSQL create(Configuration properties, ExecutorService service) throws Exception {
        return create(properties, service, TuxJSQL.class.getClassLoader());
    }

    public static TuxJSQL create(Configuration properties, ExecutorService service, ClassLoader classLoader) throws Exception {
        SQLBuilder builder;
        builder = (SQLBuilder) properties.getSQLBuilder().get();
        return create(properties, builder, service);
    }


    public static TuxJSQL create(Configuration properties, SQLBuilder builder, ExecutorService service) throws Exception {
        ConnectionProvider provider = CPProvider.getCP();
        builder.configureConnectionProvider(provider, properties.getUserProperties());
        if (provider.isClosed()) {
            return null;
        }
        return new TuxJSQL(provider, builder, service);
    }
}
