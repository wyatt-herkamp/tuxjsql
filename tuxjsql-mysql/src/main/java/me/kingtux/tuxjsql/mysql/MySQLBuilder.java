package me.kingtux.tuxjsql.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.kingtux.tuxjsql.core.builders.ColumnBuilder;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;
import me.kingtux.tuxjsql.core.builders.TableBuilder;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.core.statements.SubWhereStatement;
import me.kingtux.tuxjsql.core.statements.WhereStatement;

import java.util.Properties;
@SuppressWarnings("Duplicates")
public class MySQLBuilder implements SQLBuilder {
    private HikariDataSource dataSource;
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
        HikariConfig config = getDefaultHikariConfig();
        config.setJdbcUrl(String.format(config.getJdbcUrl(), properties.getProperty("db.host"), properties.getProperty("db.db")));
        config.setUsername(properties.getProperty("db.username"));
        config.setPassword(properties.getProperty("db.password"));
        setDataSource(config);
    }

    @Override
    public SelectStatement createSelectStatement() {
        return new MySQLSelectStatement();
    }

    @Override
    public HikariDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void setDataSource(HikariDataSource bds) {
        dataSource = bds;

    }

    @Override
    public void setDataSource(HikariConfig config) {
        setDataSource(new HikariDataSource(config));
    }

    @Override
    public HikariConfig getDefaultHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://%1$s/%2$s?useSSL=false");
        config.setMaximumPoolSize(5);
        config.setIdleTimeout(30000);
        return config;
    }

}
