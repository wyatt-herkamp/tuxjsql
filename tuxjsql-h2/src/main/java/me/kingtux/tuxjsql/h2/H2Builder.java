package me.kingtux.tuxjsql.h2;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.builders.ColumnBuilder;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;
import me.kingtux.tuxjsql.core.builders.TableBuilder;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.core.statements.SubWhereStatement;
import me.kingtux.tuxjsql.core.statements.WhereStatement;

import java.util.Properties;

@SuppressWarnings("Duplicates")
public class H2Builder implements SQLBuilder {
    private HikariDataSource dataSource;

    @Override
    public TableBuilder createTable() {
        return new H2TableBuilder(this);
    }

    @Override
    public WhereStatement createWhere() {
        return new H2WhereStatement();
    }

    @Override
    public SubWhereStatement createSubWhere() {
        return new H2SubWhereStatement();
    }

    @Override
    public ColumnBuilder createColumn() {
        return new H2ColumnBuilder();
    }


    @Override
    public SelectStatement createSelectStatement() {
        return new H2SelectStatement();
    }

    @Override
    public HikariDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void setDataSource(HikariDataSource bds) {
        if(dataSource!=null && !dataSource.isClosed()){
            dataSource.close();
        }
        this.dataSource = bds;
    }

    @Override
    public void setDataSource(HikariConfig config) {
        setDataSource(new HikariDataSource(config));
    }

    @Override
    public TuxJSQL.Type getType() {
        return TuxJSQL.Type.H2;
    }

    @Override
    public HikariConfig getDefaultHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.h2.Driver");
        config.setJdbcUrl("jdbc:h2:%1$s");
        config.setMaximumPoolSize(5);
        config.setIdleTimeout(30000);
        config.setLeakDetectionThreshold(30000);
        return config;
    }

    @Override
    public void createConnection(HikariConfig config, Properties properties) {
        config.setJdbcUrl(String.format(config.getJdbcUrl(), properties.getProperty("db.file")));

        setDataSource(config);
    }

}
