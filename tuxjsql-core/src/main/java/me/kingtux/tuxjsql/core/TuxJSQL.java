package me.kingtux.tuxjsql.core;

import com.zaxxer.hikari.HikariDataSource;
import me.kingtux.tuxjsql.core.result.ColumnItem;
import me.kingtux.tuxjsql.core.result.DBRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * I know I know static is bad. But FUCK YOU
 */
public class TuxJSQL {
    private static SQLBuilder SQLBuilder;
    private static HikariDataSource ds;
    protected static Logger logger = LoggerFactory.getLogger(TuxJSQL.class);
    private static List<Table> savedTables = new ArrayList<>();

    private TuxJSQL() {
    }

    /**
     * Save the table to grab later!
     *
     * @param table the table
     */
    public static void saveTable(Table table) {
        savedTables.add(table);
    }

    /**
     * Get a saved table by name
     *
     * @param name the name
     * @return the saved table!
     */
    public static Table getTableByName(String name) {
        for (Table table : savedTables) {
            if(table.getName().equalsIgnoreCase(name)){
                return table;
            }
        }
        return null;
    }

    /**
     * Gets the SQL SQLBuilder
     *
     * @return the SQLBuilder
     */
    public static SQLBuilder getSQLBuilder() {
        if (SQLBuilder == null) {
            try {
                throw new IllegalAccessException("TuxJSQL has not been configured... Please set the SQLBuilder");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
        return SQLBuilder;
    }

    /**
     * Set the SQLBuilder by object
     *
     * @param SQLBuilder the SQLBuilder
     */
    public static void setSQLBuilder(SQLBuilder SQLBuilder) {
        TuxJSQL.SQLBuilder = SQLBuilder;
    }

    /**
     * Set the SQLBuilder by Type
     *
     * @param type the SQLBuilder Type
     */
    public static void setBuilder(Type type) {
        try {
            setBuilder(type.classPath);
        } catch (ClassNotFoundException e) {
            logger.error("Please add " + type.dependency + " To your maven or gradle. Use the same groupId as TuxJSQL-core");
        }
    }

    /**
     * Sets the SQLBuilder by class path
     *
     * @param clazzPath the class path to the SQLBuilder
     */
    public static void setBuilder(String clazzPath) throws ClassNotFoundException {
        Class<?> clazz = null;
        clazz = Class.forName(clazzPath);
        if (clazz == null) {
            return;
        }
        try {
            SQLBuilder = (SQLBuilder) clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the connection
     *
     * @return the connection
     */
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sets a Connection with a properties file.
     * Values: https://github.com/wherkamp/tuxjsql/wiki/Creating-a-Connection-with-Properties
     * @param properties the properties
     */
    public static void setDatasource(Properties properties) {
        TuxJSQL.ds = getSQLBuilder().createConnection(properties);
    }

    public static void setDatasource(HikariDataSource datasource) {
        TuxJSQL.ds = datasource;
    }
    /**
     * The Database Type
     */
    public enum Type {
        /**
         * MYSQL
         */
        MYSQL("me.kingtux.tuxjsql.mysql.MySQLBuilder", "tuxjsql-mysql"),
        /**
         * SQLITE
         */
        SQLITE("me.kingtux.tuxjsql.sqlite.SQLITEBuilder", "tuxjsql-sqlite");
        private String classPath, dependency;
        Type(String classPath) {
            this.classPath = classPath;
        }

        Type(String classPath, String dependency) {
            this.classPath = classPath;
            this.dependency = dependency;
        }

        public String getClassPath() {
            return classPath;
        }
    }

    public static class Utils {
        public static List<DBRow> resultSetToResultRow(ResultSet resultSet, int numberOfColumns) {
            List<DBRow> results = new ArrayList<>();
            System.out.println("Building");
            try {
                int i = numberOfColumns;
                logger.debug("Number of rows! " + i);
                while (resultSet.next()) {
                    List<ColumnItem> items = new ArrayList<>();
                    for (int j = 1; j <= i; j++) {
                        items.add(new ColumnItem(resultSet.getObject(j), resultSet.getMetaData().getColumnName(j)));
                    }
                    results.add(new DBRow(items));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Done Building");
            return results;
        }
    }
}
