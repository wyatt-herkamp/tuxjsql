package dev.tuxjsql.core;

import dev.tuxjsql.core.connection.ConnectionSettings;
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
public interface Configuration {
    Pair<ConnectionSettings, Properties> createConnection();

    /**
     * This is just used for the Connection Pool and stuff like that.
     * @return the user properties
     */
    Properties getUserProperties();

    /**
     * this will set the User Properties also known as Connection Pool Rules.
     */
    void setUserProperties(Properties properties);
}
