package dev.tuxjsql.core;

import dev.tuxjsql.core.builders.SQLBuilder;
import dev.tuxjsql.core.connection.ConnectionSettings;
import dev.tuxjsql.core.tools.SimpleSupplier;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Properties;

/**
 * This is a Configuration.
 * The different TuxJSQL implementation will implement this Interface.
 * <p>
 * Adding different methods for setting different properties for that Connection Type.
 * <p>
 * TuxJSQL will call {@link Configuration#createConnection()} and pass that data onto the TuxJSQL Connection Provider
 */
public interface Configuration<T extends Configuration> {
    Pair<ConnectionSettings, Properties> createConnection();

    /**
     * This is just used for the Connection Pool and stuff like that.
     *
     * @return the user properties
     */
    Properties getUserProperties();

    /**
     * this will set the User Properties also known as Connection Pool Rules.
     *
     * @return this
     */
    T setUserProperties(Properties properties);

    /**
     * Loads the configuration from Properties
     *
     * @param properties the properties to load
     * @return this
     */
    T loadFromProperties(Properties properties);

    T setSQLBuilder(Class<?> sqlBuilder);

    T setSQLBuilder(String sqlBuilder);

    /**
     * This is the default. All the other setSQLBuilder will just fork into this
     *
     * @param builder a Supplier that will build the SQLBuilder
     * @return the Supplier<SQLBuilder>
     */
    T setSQLBuilder(SimpleSupplier<SQLBuilder> builder);

    /**
     * Sets the ThreadPool size. Default is 1
     *
     * @return this
     */
    T setThreadPoolSize(int size);

    SimpleSupplier<SQLBuilder> getSQLBuilder();

    int getThreadPoolSize();


}
