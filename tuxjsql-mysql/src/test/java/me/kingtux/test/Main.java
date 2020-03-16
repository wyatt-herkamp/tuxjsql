package me.kingtux.test;

import me.kingtux.tuxjsql.core.Column;
import me.kingtux.tuxjsql.core.CommonDataTypes;
import me.kingtux.tuxjsql.core.Table;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;
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

        //Start to use it.
        SQLBuilder ssqlBuilder = TuxJSQL.setup(properties);
        Column primary = ssqlBuilder.createColumn().primary(true).name("id").autoIncrement(true).type(CommonDataTypes.INT).build();
        Column name = ssqlBuilder.createColumn().name("name").notNull(true).type(CommonDataTypes.TEXT).build();
        Column coolness = ssqlBuilder.createColumn().name("coolness").defaultValue("NOT COOL").type(CommonDataTypes.VARCHAR, "255").build();
        Table newTable = ssqlBuilder.createTable().name("CoolTable").addColumn(primary).addColumn(name).addColumn(coolness).build();
        newTable.createIfNotExists().insert("name", "Wyatt Herkamp");
        newTable.insert("name", "Clifford", "coolness", "Really Cool!");
        System.out.println(newTable.select(ssqlBuilder.createSelectStatement().addColumn("coolness")).get(0).getRowItem("coolness").getAsString());
        newTable.drop();
        //Old way of Using TuxJSQL
        //Table table = MySQLBuilder.createTable("t1", MySQLBuilder.createColumn("id", CommonDataTypes.INT, true), MySQLBuilder.createColumn("name", CommonDataTypes.TEXT));
        //table.createIfNotExists();
        //table.insertAll("test");

    }


}
