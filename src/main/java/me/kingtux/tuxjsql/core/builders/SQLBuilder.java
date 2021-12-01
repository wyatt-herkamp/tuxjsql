package me.kingtux.tuxjsql.core.builders;

import me.kingtux.tuxjsql.basic.sql.BasicDataTypes;
import me.kingtux.tuxjsql.core.Configuration;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.connection.ConnectionProvider;
import me.kingtux.tuxjsql.core.sql.DeleteStatement;
import me.kingtux.tuxjsql.core.sql.InsertStatement;
import me.kingtux.tuxjsql.core.sql.SQLDataType;
import me.kingtux.tuxjsql.core.sql.UpdateStatement;
import me.kingtux.tuxjsql.core.sql.select.JoinStatement;
import me.kingtux.tuxjsql.core.sql.select.SelectStatement;
import me.kingtux.tuxjsql.core.sql.where.SubWhereStatement;
import me.kingtux.tuxjsql.core.sql.where.WhereStatement;

import java.util.Properties;

/**
 * This is the base for all dialect specific crap.
 */
public interface SQLBuilder {

    /**
     * Build yourself a Column
     *
     * @param andType the return on ColumnBuilder#and
     * @param <T>     The Type for ColumnBuilder#and
     * @return the column Builder
     */
    <T> ColumnBuilder<T> createColumn(T andType);

    /**
     * Create a column
     *
     * @return the new column Builder
     */
    ColumnBuilder createColumn();

    /**
     * Create yourself a table
     *
     * @return the new Table builder
     */
    TableBuilder createTable();

    /**
     * Creates a basic where statement
     *
     * @return the new basic where statement
     */
    WhereStatement createWhere();

    /**
     * creates a wherestatement
     *
     * @param andValue the value for WhereStatement#and
     * @param <T>      the and type
     * @return the new and typed Wherestatment
     */
    <T> WhereStatement<T> createWhere(T andValue);

    /**
     * Creates a basic subwhere statement
     *
     * @return the sub where statement
     */
    SubWhereStatement createSubWhereStatement();

    /**
     * creates a subwhere
     *
     * @param andValue the value for SubWhereStatement#and
     * @param <T>      the and type
     * @return the new and typed subwhere
     */
    <T> SubWhereStatement<T> createSubWhereStatement(T andValue);

    /**
     * Create a select statement
     *
     * @return the select statement
     */
    SelectStatement createSelectStatement();

    /**
     * Create a join statement
     *
     * @param basicSelectStatement the SelectStatement to use on build
     * @return the joinstatement
     */
    JoinStatement createJoinStatement(SelectStatement basicSelectStatement);

    /**
     * Creates and update statement
     *
     * @return the new update statement
     */
    UpdateStatement createUpdateStatement();

    /**
     * The delete statement
     *
     * @return the new delete statement
     */
    DeleteStatement createDeleteStatement();

    /**
     * The new insert statemnet
     *
     * @return the insert statement
     */
    InsertStatement createInsertStatement();

    /**
     * The name of the implementation
     *
     * @return name
     */
    String name();

    /**
     * The jbdc class path for this Driver
     *
     * @return the driver classpath
     */
    String jdbcClass();


    /**
     * This will convert a public DataType to its local dialect version.
     *
     * @param dataType the BasicDataType to convert
     * @return the correct SQLDataType
     */
    SQLDataType convertDataType(BasicDataTypes dataType);


    /**
     * Configures the ConnectionProvider
     *
     * @param provider       the found Connection provider
     * @param userProperties the users properties
     * @throws Exception unable to create connection
     */
    void configureConnectionProvider(ConnectionProvider provider, Properties userProperties) throws Exception;

    void configureConnectionProvider(ConnectionProvider provider, Configuration configuration) throws Exception;

    /**
     * Set the TuxJSQL
     *
     * @param tuxJSQL the tuxjsql instance
     */
    void setTuxJSQL(TuxJSQL tuxJSQL);
}
