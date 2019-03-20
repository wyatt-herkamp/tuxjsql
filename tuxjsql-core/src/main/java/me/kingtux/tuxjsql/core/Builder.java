package me.kingtux.tuxjsql.core;

import com.zaxxer.hikari.HikariDataSource;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.core.statements.SubWhereStatement;
import me.kingtux.tuxjsql.core.statements.WhereStatement;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * This is the central builder. This is used to build everything.
 */
public interface Builder {
    /**
     * Build a table with name and columns
     *
     * @param name    The Table Name
     * @param columns Columns you want
     * @return the Table
     */
    default Table createTable(String name, Column... columns) {
        return createTable(name, Arrays.asList(columns));
    }

    /**
     * Build a table with name and columns
     *
     * @param name    The Table Name
     * @param columns Columns you want
     * @return the Table
     */
    default Table createTable(String name, List<Column> columns) {
        TableBuilder tb = createTable();
        tb.name(name);
        columns.forEach(tb::addColumn);
        return tb.build();
    }

    /**
     * Creates a Table Builder
     *
     * @return a table builder
     */
    TableBuilder createTable();
    /**
     * Creates a WhereStatement
     *
     * @return the wherestatement
     * @see WhereStatement
     */
    WhereStatement createWhere();

    /**
     * The SubWhere
     *
     * @return the subwhere statement
     * @see SubWhereStatement
     */
    SubWhereStatement createSubWhere();

    /**
     * Builds a simple column
     *
     * @param name the column name
     * @param type the column type
     * @return column
     * @see Column
     */
    default Column createColumn(String name, DataType type) {
        return createColumn(name, type, false);
    }

    /**
     * Builds a Column
     *
     * @param name    name
     * @param type    type
     * @param primary is it a primary key
     * @return the created column
     * @see Column
     */
    default Column createColumn(String name, DataType type, boolean primary) {
        return createColumn(name, type, primary, false);
    }

    /**
     * Builds a column
     *
     * @param name     the name
     * @param type     type
     * @param primary  is it primary key
     * @param nullable is it nullable
     * @return the built column
     * @see Column
     */
    default Column createColumn(String name, DataType type, boolean primary, boolean nullable) {
        return createColumn(name, type, primary, nullable, false);
    }

    /**
     * Builds a column
     *
     * @param name     name
     * @param type     type
     * @param primary  is it primary
     * @param nullable is it nullable
     * @param unique   is it unique
     * @return the built column
     * @see Column
     */
    default Column createColumn(String name, DataType type, boolean primary, boolean nullable, boolean unique) {
        return createColumn(name, type, primary, nullable, unique, primary);
    }

    /**
     * All other create column methods call this
     *
     * @param name          name
     * @param type          tpye
     * @param primary       primary
     * @param nullable      nullable
     * @param unique        unque
     * @param autoIncrement is it an AUTO INCREMENT VALUE
     * @return the column
     * @see Column
     */
    default Column createColumn(String name, DataType type, boolean primary, boolean nullable, boolean unique, boolean autoIncrement) {
        return createColumn(name, new ColumnType(type), primary, nullable, unique, autoIncrement);
    }

    /**
     * @param name         name
     * @param type         type
     * @param primary      primary
     * @param nullable     nullable
     * @param unique       unique
     * @param autoIncrment autoinc
     * @return the Colulm
     */
    default Column createColumn(String name, ColumnType type, boolean primary, boolean nullable, boolean unique, boolean autoIncrment) {
        return createColumn().name(name).type(type).primary(primary).notNull(nullable).unique(unique).autoIncrement(autoIncrment).build();
    }

    /**
     * Its highly recommended you use this method for anything more than Name and Type.
     *
     * @return The ColumnBuilder
     */
    ColumnBuilder createColumn();


    /**
     * Creates a Connection from properties
     *
     * @param properties the properties
     * @return the Connection
     */
    HikariDataSource createConnection(Properties properties);

    SelectStatement createSelectStatement();
}
