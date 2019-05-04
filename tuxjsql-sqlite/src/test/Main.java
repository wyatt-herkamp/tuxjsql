import me.kingtux.tuxjsql.core.CommonDataTypes;
import me.kingtux.tuxjsql.core.ORDERBy;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;
import me.kingtux.tuxjsql.core.statements.SelectStatement;
import me.kingtux.tuxjsql.sqlite.SQLITESelectStatement;
import me.kingtux.tuxjsql.sqlite.SQLiteQuery;

import java.util.Arrays;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("db.file", "db.sql");
        properties.setProperty("db.type", "SQLITE");
        SQLBuilder sqlbuilder = TuxJSQL.setup(properties);
        assert sqlbuilder != null;
        Table table = sqlbuilder.createTable().name("t1").
                addColumn(sqlbuilder.createColumn().type(CommonDataTypes.INT).autoIncrement(true).primary(true).name("id").build()).
                addColumn(sqlbuilder.createColumn().type(CommonDataTypes.TEXT).name("name").build()).
                addColumn(sqlbuilder.createColumn().name("moment").type(CommonDataTypes.BIGINT).build()).build().createUpdate();
        for (int i = 0; i < 4; i++) {
            table.insertAll("KingTux"+ i, System.currentTimeMillis());
        }
        table.insertAll("KingTux1", System.currentTimeMillis());

        table.select(sqlbuilder.createSelectStatement().addColumn("id").addColumn("name").where(sqlbuilder.createWhere().start("name", "KingTux1")).orderBy(ORDERBy.DESCENDING, "id").limit(1));
    }
}
