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

import java.io.File;
import java.util.Properties;

@SuppressWarnings("Duplicates")
public class H2Builder implements SQLBuilder {

    @Override
    public TableBuilder createTable() {
        return new H2TableBuilder();
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
    public HikariDataSource createConnection(Properties properties) {
        HikariConfig config = new HikariConfig();
        File file = new File(properties.getProperty("db.file"));
        TuxJSQL.logger.debug("H2 File: "+ file.getAbsolutePath());
        config.setJdbcUrl("jdbc:h2://" + file.getAbsolutePath());
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
        return new H2SelectStatement();
    }

}
