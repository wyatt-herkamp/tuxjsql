package dev.tuxjsql.core.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A connection provider provides an JDBC connections, returns a connection (If in a pool), and closessd it when needed
 */
public interface ConnectionProvider {
    /**
     * Returns a connection out of the pool
     *
     * @return the connection
     */
    Connection getConnection();

    /**
     * Closes the Connection Pool or the connection
     */
    void close();

    /**
     * Returns a connection to the pool
     *
     * @param connection the connection to return
     */
    void returnConnection(Connection connection);

    /**
     * Is there an active connection
     *
     * @return the status
     */
    boolean isConnected();

    /**
     * The name of your connection provider
     *
     * @return the name
     */
    String name();

    /**
     * Setup the ConnectionProvider
     *
     * @param settings     the settings from the implementation
     * @param userSettings the user settings(Pool size, username, password, and ....)
     */
    void setup(ConnectionSettings settings, Properties userSettings) throws SQLException;

    boolean isClosed();

}
