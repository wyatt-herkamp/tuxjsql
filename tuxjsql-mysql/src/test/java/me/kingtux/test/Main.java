package me.kingtux.test;

import me.kingtux.tuxjsql.core.Builder;
import me.kingtux.tuxjsql.core.CommonDataTypes;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TuxJSQL;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        //Configure the TUxJSQL
        Properties properties = new Properties();
        String path = System.getProperty("user.home") + "/mysql.properties";
        System.out.println(path);
        properties.load(new FileInputStream(new File(path)));
        TuxJSQL.setBuilder(TuxJSQL.Type.MYSQL);
        TuxJSQL.setConnection(properties);
        //Start to use it.
        Builder builder = TuxJSQL.getBuilder();
        Table table = builder.createTable("t1", builder.createColumn("id", CommonDataTypes.INT, true), builder.createColumn("name", CommonDataTypes.TEXT));
        table.createIfNotExists();
        table.insertAll("test");

    }


}
