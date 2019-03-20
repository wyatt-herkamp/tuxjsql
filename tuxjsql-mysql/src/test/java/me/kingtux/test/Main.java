package me.kingtux.test;

import me.kingtux.tuxjsql.core.*;
import me.kingtux.tuxjsql.core.statements.SelectStatement;

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
        TuxJSQL.setDatasource(properties);
        //Start to use it.
        Builder builder = TuxJSQL.getBuilder();
        Column primary = builder.createColumn().primary(true).name("id").autoIncrement(true).type(CommonDataTypes.INT).build();
        Column name = builder.createColumn().name("name").notNull(true).type(CommonDataTypes.TEXT).build();
        Column coolness = builder.createColumn().name("coolness").defaultValue("NOT COOL").type(CommonDataTypes.VARCHAR, "255").build();
        Table newTable = builder.createTable().name("CoolTable").addColumn(primary).addColumn(name).addColumn(coolness).build();
        newTable.createIfNotExists().insert("name", "Wyatt Herkamp");
        newTable.insert("name", "Clifford", "coolness", "Really Cool!");
        System.out.println(newTable.select(SelectStatement.create().addColumn("coolness")).get(0).getRowItem("coolness").getAsString());
        //Old way of Using TuxJSQL
        //Table table = builder.createTable("t1", builder.createColumn("id", CommonDataTypes.INT, true), builder.createColumn("name", CommonDataTypes.TEXT));
        //table.createIfNotExists();
        //table.insertAll("test");

    }


}
