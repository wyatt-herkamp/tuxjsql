package me.kingtux.tuxjsql.sqlite;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;
import me.kingtux.tuxjsql.core.builders.TableBuilder;
import me.kingtux.tuxjsql.core.builders.ColumnBuilder;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.core.statements.SubWhereStatement;
import me.kingtux.tuxjsql.core.statements.WhereStatement;

import java.io.File;
import java.util.Properties;

@SuppressWarnings("Duplicates")
public class SQLITEBuilder implements SQLBuilder {
    @Override
    public TableBuilder createTable() {
        return new SQLITETableBuilder();
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
    public HikariDataSource createConnection(Properties properties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + new File(properties.getProperty("db.file")).getAbsolutePath());

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(20);
        config.setIdleTimeout(30000);
        return new HikariDataSource(config);
    }

    @Override
    public SelectStatement createSelectStatement() {
        return new SQLITESelectStatement();
    }


}
