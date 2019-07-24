package dev.tuxjsql.core;

import dev.tuxjsql.core.builders.SQLBuilder;
import dev.tuxjsql.core.connection.CPProvider;
import dev.tuxjsql.core.connection.ConnectionProvider;
import dev.tuxjsql.core.exceptions.NoSQLBuilderException;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the starting point for your TuxJSQL jour
 */
public class TuxJSQLBuilder {

    /**
     * Get a SQLBuilder by the class
     *
     * @param clazz the class path
     * @return the SQLBuilder
     */
    public static SQLBuilder getBuildByClazz(String clazz) {
        Class<?> cla;
        try {
            cla = Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            throw new NoSQLBuilderException("Unable to find SQLBuilder for " + clazz);
        }
        return getBuildByClazz(cla);
    }

    /**
     * Builds a new SQLBuilder using the reflectasm
     *
     * @param clazz the clazz object
     * @return the SQLBuilder
     */
    public static SQLBuilder getBuildByClazz(Class<?> clazz) {

        try {
            return (SQLBuilder) clazz.getConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            TuxJSQL.getLogger().error("Unable to create instance of " + clazz.getCanonicalName(), e);
        }
        return null;
    }

    /**
     * Create a TuxJSQL via a properties
     * Follow this guide: Coming Soon
     *
     * @param properties the java properties object with rules
     * @return the TuxJSQL object
     */
    public static TuxJSQL create(Properties properties) {
        if (properties.containsKey("executors.count")) {
            return create(properties, Executors.newFixedThreadPool(Integer.parseInt(properties.getProperty("executors.count"))));
        } else {
            return create(properties, Executors.newSingleThreadExecutor());
        }
    }

    public static TuxJSQL create(Properties properties, ExecutorService service) {
        SQLBuilder builder;
        if (properties.containsKey("db.type")) {
            builder = getBuildByClazz(properties.getProperty("db.type"));
        } else {
            throw new IllegalArgumentException("Must provide a DB type");
        }
        return create(properties, builder, service);
    }


    public static TuxJSQL create(Properties properties, SQLBuilder builder, ExecutorService service) {
        ConnectionProvider provider = CPProvider.getCP();
        builder.configureConnectionProvider(provider, properties);

        return new TuxJSQL(provider, builder, service);
    }
}