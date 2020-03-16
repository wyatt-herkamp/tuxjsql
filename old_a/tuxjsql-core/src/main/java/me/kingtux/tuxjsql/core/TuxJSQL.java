package me.kingtux.tuxjsql.core;

import me.kingtux.tuxjsql.core.builders.SQLBuilder;
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
 * TuxJSQL core class.
 *
 * @author KingTux
 */
public class TuxJSQL {
    private static me.kingtux.tuxjsql.core.builders.SQLBuilder SQLBuilder;
    public  static Logger logger = LoggerFactory.getLogger(TuxJSQL.class);
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
     * Gets the static SQLBuilder
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
     * Set the static access TuxJSQL
     *
     *
     * @param SQLBuilder the SQLBuilder
     */
    public static void setSQLBuilder(SQLBuilder SQLBuilder) {
        TuxJSQL.SQLBuilder = SQLBuilder;
    }


    /**
     * Get the connection
     * <b>Warning: for this to work the SQLBuilder must be set in TuxJSQL</b>
     * @return the connection
     */
    public static Connection getConnection() {
        try {
            return getSQLBuilder().getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Creates a SQLBuilder from the properties provided
     * <a href="https://github.com/wherkamp/tuxjsql/wiki/Creating-your-first-TuxJSQL-SQLBuilder">Learn more here</a>
     *
     * @param properties Properties in the correct format
     * @return the SQLBuilder null if not found.
     */
    public static SQLBuilder setup(Properties properties) {
        Type type = Type.valueOf(properties.getProperty("db.type").toUpperCase());
        SQLBuilder builder;
        if (type == Type.OTHER) {
            try {
                builder = (me.kingtux.tuxjsql.core.builders.SQLBuilder) Class.forName(properties.getProperty("db.class")).getConstructor().newInstance();
            } catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                logger.error("Unable to find " + properties.getProperty("db.class"), e);
                return null;
            }
        } else {
            builder = type.create();
        }
        if (Boolean.parseBoolean(properties.getProperty("db.auto.connect", "true"))) {
            builder.createConnection(properties);
        }
        return builder;
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
        SQLITE("me.kingtux.tuxjsql.sqlite.SQLITEBuilder", "tuxjsql-sqlite"),
        /**
         * h2 database
         *
         */
        H2("me.kingtux.tuxjsql.h2.H2Builder", "tuxjsql-h2"),
        /**
         * Other. If this is set please set the properties value
         * db.class to your builders class.
         */
        OTHER("", "");
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

        public SQLBuilder create() {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(classPath);
            } catch (ClassNotFoundException e) {
                logger.error("Please add " + dependency + " To your maven or gradle. Use the same groupId as TuxJSQL-core");
            }

            try {
                return (SQLBuilder) clazz.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public static class Utils {

        public static List<DBRow> resultSetToResultRow(ResultSet resultSet, int numberOfColumns) {
            List<DBRow> results = new ArrayList<>();
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
            return results;
        }
    }
}
