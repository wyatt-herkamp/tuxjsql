package me.kingtux.test;

import me.kingtux.tuxjsql.core.*;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;
import me.kingtux.tuxjsql.core.statements.SelectStatement;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {

        //Configure the TUxJSQL
        Properties properties = new Properties();
        properties.setProperty("db.type", "H2");
        properties.setProperty("db.file", "gay.h2");
        TuxJSQL.setup(properties);
        //Start to use it.
        SQLBuilder SQLBuilder = TuxJSQL.getSQLBuilder();
        Column primary = SQLBuilder.createColumn().primary(true).name("id").autoIncrement(true).type(CommonDataTypes.INT).build();
        Column name = SQLBuilder.createColumn().name("name").notNull(true).type(CommonDataTypes.TEXT).build();
        Column coolness = SQLBuilder.createColumn().name("coolness").defaultValue("NOT COOL").type(CommonDataTypes.VARCHAR, "255").build();
        Table newTable = SQLBuilder.createTable().name("CoolTable").addColumn(primary).addColumn(name).addColumn(coolness).build();
        newTable.createIfNotExists().insert("name", "Wyatt Herkamp");
        newTable.insert("name", "Clifford", "coolness", "Really Cool!");
        System.out.println(newTable.select(SelectStatement.create().addColumn("coolness")).get(0).getRowItem("coolness").getAsString());
        newTable.drop();
        //Old way of Using TuxJSQL
        //Table table = MySQLBuilder.createTable("t1", MySQLBuilder.createColumn("id", CommonDataTypes.INT, true), MySQLBuilder.createColumn("name", CommonDataTypes.TEXT));
        //table.createIfNotExists();
        //table.insertAll("test");

    }


}
