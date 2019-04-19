import me.kingtux.tuxjsql.core.*;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;
import me.kingtux.tuxjsql.core.statements.SelectStatement;


import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        TuxJSQL.setBuilder(TuxJSQL.Type.SQLITE);
        Properties properties = new Properties();
        properties.setProperty("db.file", "db.sql");
        SQLBuilder sqlbuilder = TuxJSQL.getSQLBuilder();
        sqlbuilder.createConnection(properties);
        System.out.println(TuxJSQL.getConnection().getClass().getSimpleName());
        Table table = TuxJSQL.getSQLBuilder().createTable().name("t1").
                addColumn(sqlbuilder.createColumn().type(CommonDataTypes.INT).autoIncrement(true).primary(true).name("id").build()).
                addColumn(sqlbuilder.createColumn().type(CommonDataTypes.TEXT).name("name").build()).build().createIfNotExists();
            new Thread(() -> {
                table.insertAll("Test");
                System.out.println(table.select(SelectStatement.create().addColumn("id")).first().getRowItem("id").getAsInt());
            }).start();



    }
}
