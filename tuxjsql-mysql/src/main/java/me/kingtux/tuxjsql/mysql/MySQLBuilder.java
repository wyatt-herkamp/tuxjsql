package me.kingtux.tuxjsql.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.kingtux.tuxjsql.core.ColumnBuilder;
import me.kingtux.tuxjsql.core.TableBuilder;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.core.statements.SubWhereStatement;
import me.kingtux.tuxjsql.core.statements.WhereStatement;

import java.util.Properties;
@SuppressWarnings("Duplicates")
public class MySQLBuilder implements me.kingtux.tuxjsql.core.SQLBuilder {

    @Override
    public TableBuilder createTable() {
        return new MySQLTableBuilder();
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
    public HikariDataSource createConnection(Properties properties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + properties.getProperty("db.host") + "/" + properties.getProperty("db.database")+"?useSSL=false");
        config.setUsername(properties.getProperty("db.username"));
        config.setPassword(properties.getProperty("db.password"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(20);
        config.setIdleTimeout(30000);
        return new HikariDataSource(config);
    }

    @Override
    public SelectStatement createSelectStatement() {
        return new MySQLSelectStatement();
    }

}
