package me.kingtux.tuxjsql.mysql;

import me.kingtux.tuxjsql.core.builders.ColumnBuilder;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;
import me.kingtux.tuxjsql.core.builders.TableBuilder;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.core.statements.SubWhereStatement;
import me.kingtux.tuxjsql.core.statements.WhereStatement;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.Properties;
@SuppressWarnings("Duplicates")
public class MySQLBuilder implements SQLBuilder {
    private BasicDataSource dataSource;
    @Override
    public TableBuilder createTable() {
        return new MySQLTableBuilder(this);
    }

    @Override
    public WhereStatement createWhere() {
        return new SQLWhereStatement();
    }

    @Override
    public SubWhereStatement createSubWhere() {
        return new SQLSubWhereStatement();
    }

    @Override
    public ColumnBuilder createColumn() {
        return new MySQLColumnBuilder();
    }


    @Override
    public void createConnection(Properties properties) {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:h2://" + properties.getProperty("db.host") + "/" + properties.getProperty("db.database") + "?useSSL=false");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setPassword(properties.getProperty("db.password"));
        dataSource.setUsername(properties.getProperty("db.username"));
        dataSource.setInitialSize(Integer.parseInt(properties.getProperty("db.poolsize", "5")));

    }

    @Override
    public SelectStatement createSelectStatement() {
        return new MySQLSelectStatement();
    }

    @Override
    public BasicDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void setDataSource(BasicDataSource bds) {
        dataSource = bds;

    }

}
