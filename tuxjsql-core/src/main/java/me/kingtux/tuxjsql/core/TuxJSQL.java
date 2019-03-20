package me.kingtux.tuxjsql.core;

import com.zaxxer.hikari.HikariDataSource;
import me.kingtux.tuxjsql.core.result.ColumnItem;
import me.kingtux.tuxjsql.core.result.DBRow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    private static Builder builder;
    private static HikariDataSource ds;

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
     * Gets the SQL Builder
     *
     * @return the builder
     */
    public static Builder getBuilder() {
        if (builder == null) {
            try {
                throw new IllegalAccessException("TuxJSQL has not been configured... Please set the Builder");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
        return builder;
    }

    /**
     * Set the builder by object
     *
     * @param builder the builder
     */
    public static void setBuilder(Builder builder) {
        TuxJSQL.builder = builder;
    }

    /**
     * Set the Builder by Type
     *
     * @param type the Builder Type
     */
    public static void setBuilder(Type type) {
        setBuilder(type.classPath);
    }

    /**
     * Sets the Builder by class path
     *
     * @param clazzPath the class path to the builder
     */
    public static void setBuilder(String clazzPath) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(clazzPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (clazz == null) {
            return;
        }
        Method method;

        try {
            method = clazz.getMethod("getInstance");
        } catch (NoSuchMethodException e) {
            method = null;
        }
        if (method != null) {
            try {
                builder = (Builder) method.invoke(null);
                return;
            } catch (IllegalAccessException | InvocationTargetException ignored) {

            }
        }
        try {
            builder = (Builder) clazz.getConstructor().newInstance();
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
        TuxJSQL.ds = getBuilder().createConnection(properties);
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
        MYSQL("me.kingtux.tuxjsql.mysql.SQLBuilder"),
        /**
         * SQLITE
         */
        SQLITE("me.kingtux.tuxjsql.sqlite.SQLITEBuilder");
        private String classPath;

        Type(String classPath) {
            this.classPath = classPath;
        }

        public String getClassPath() {
            return classPath;
        }
    }

    public static class Utils {
        public static List<DBRow> resultSetToResultRow(ResultSet resultSet, int numberOfColumns) {
            List<DBRow> results = new ArrayList<>();
            try {
                int i = numberOfColumns;
                System.out.println(i);
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
            return results;
        }
    }
}
