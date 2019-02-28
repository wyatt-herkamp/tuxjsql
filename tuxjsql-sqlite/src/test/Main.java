import me.kingtux.tuxjsql.core.Builder;
import me.kingtux.tuxjsql.core.CommonDataTypes;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TuxJSQL;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        TuxJSQL.setBuilder(TuxJSQL.Type.SQLITE);
        Properties properties = new Properties();
        properties.setProperty("db.file", "db.sql");
        TuxJSQL.setConnection(properties);
        Builder builder = TuxJSQL.getBuilder();
        Table table = TuxJSQL.getBuilder().createTable().name("t1").
                addColumn(builder.createColumn().type(CommonDataTypes.INT).autoIncrement(true).primary(true).name("id").build()).
                addColumn(builder.createColumn().type(CommonDataTypes.TEXT).name("name").build()).build().createIfNotExists();
        table.insertAll("Test");
    }
}
