import me.kingtux.tuxjsql.core.CommonDataTypes;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("db.file", "db.sql");
        properties.setProperty("db.type", "SQLITE");
        SQLBuilder sqlbuilder = TuxJSQL.setup(properties);


        Table table = TuxJSQL.getSQLBuilder().createTable().name("t1").
                addColumn(sqlbuilder.createColumn().type(CommonDataTypes.INT).autoIncrement(true).primary(true).name("id").build()).
                addColumn(sqlbuilder.createColumn().type(CommonDataTypes.TEXT).name("name").build()).build().createUpdate();
    }
}
