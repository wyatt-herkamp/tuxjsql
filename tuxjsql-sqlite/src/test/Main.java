import me.kingtux.tuxjsql.core.*;
import me.kingtux.tuxjsql.core.statements.SelectStatement;


import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        TuxJSQL.setBuilder(TuxJSQL.Type.SQLITE);
        Properties properties = new Properties();
        properties.setProperty("file", "db.sql");
        TuxJSQL.setDatasource(properties);
        Builder builder = TuxJSQL.getBuilder();
        Table table = TuxJSQL.getBuilder().createTable().name("t1").
                addColumn(builder.createColumn().type(CommonDataTypes.INT).autoIncrement(true).primary(true).name("id").build()).
                addColumn(builder.createColumn().type(CommonDataTypes.TEXT).name("name").build()).build().createIfNotExists();
        table.insertAll("Test");
        System.out.println(table.select(SelectStatement.create().addColumn("id")).first().getRowItem("id").getAsInt());
    }
}
