package me.kingtux.tuxjsql.sqlite;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;
import me.kingtux.tuxjsql.core.builders.TableBuilder;
import me.kingtux.tuxjsql.core.builders.ColumnBuilder;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.core.statements.SubWhereStatement;
import me.kingtux.tuxjsql.core.statements.WhereStatement;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.File;
import java.util.Properties;

@SuppressWarnings("Duplicates")
public class SQLITEBuilder implements SQLBuilder {
    private HikariDataSource dataSource;
    @Override
    public TableBuilder createTable() {
        return new SQLITETableBuilder(this);
    }

    @Override
    public WhereStatement createWhere() {
        return new SQLiteWhereStatement();
    }

    @Override
    public SubWhereStatement createSubWhere() {
        return new SQLITESubWhere();
    }

    @Override
    public ColumnBuilder createColumn() {
        return new SQLiteColumnBuilder();
    }


    @Override
    public SelectStatement createSelectStatement() {
        return new SQLITESelectStatement();
    }

   //"jdbc:sqlite:" + new File(properties.getProperty("db.file")).getAbsolutePath()


    @Override
    public void createConnection(Properties properties) {
        HikariConfig config = getDefaultHikariConfig();
        config.setJdbcUrl(String.format(config.getJdbcUrl(), properties.getProperty("db.file")));

        setDataSource(config);
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
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:%1$s");
        config.setMaximumPoolSize(5);
        config.setIdleTimeout(30000);
        return config;
    }


}
