package me.kingtux.test;

import me.kingtux.tuxjsql.core.Builder;
import me.kingtux.tuxjsql.core.CommonDataTypes;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TuxJSQL;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        //Configure the TUxJSQL
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("sql.properties")));
        TuxJSQL.setBuilder(TuxJSQL.Type.SQL);
        TuxJSQL.setConnection(properties);
        //Start to use it.
        Builder builder = TuxJSQL.getBuilder();
        Table table = builder.createTable("t1", builder.createColumn("id", CommonDataTypes.INT, true), builder.createColumn("name", CommonDataTypes.TEXT));
        table.createIfNotExists();
        table.insert("name", "Value" + new Random(10).nextInt());
        table.delete(builder.createWhere().start("id", 1));
    }


}
