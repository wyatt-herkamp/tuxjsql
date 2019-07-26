package dev.tuxjsql.basic.utils;

import dev.tuxjsql.basic.response.BasicDBColumnItem;
import dev.tuxjsql.basic.response.BasicDBRow;
import dev.tuxjsql.basic.response.BasicDBSelect;
import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.response.DBColumnItem;
import dev.tuxjsql.core.response.DBRow;
import dev.tuxjsql.core.response.DBSelect;
import org.apache.commons.lang3.Validate;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicUtils {
    /**
     * This method converts a ResultSet to a DBSelect response
     *
     * @param set The resultset you want to convert
     * @return your List of rows
     */
    public static List<DBRow>  resultSetToDBSelect(ResultSet set) {
        Validate.notNull(set, "ResultSet cant be null.");
        try {
            Validate.isTrue(!set.isClosed(), "ResultSet must be open");
        } catch (SQLException e) {
            TuxJSQL.getLogger().error("Unable to get close status", e);
            return null;
        }
        List<DBRow> rows = new ArrayList<>();
        try {
            ResultSetMetaData metaData = set.getMetaData();
            while (set.next()) {
                int i = metaData.getColumnCount();
                List<DBColumnItem> items = new ArrayList<>();
                for (int j = 1; j <= i; j++) {

                    items.add(new BasicDBColumnItem(set.getObject(j), String.format("%s.%s", metaData.getTableName(j), metaData.getColumnName(j))));
                }
                rows.add(new BasicDBRow(items));

            }
        } catch (SQLException e) {
            TuxJSQL.getLogger().error("Unable to read ResultSet", e);
            return null;
        }
        try {
            set.close();
        } catch (SQLException e) {
            TuxJSQL.getLogger().error("Unable to close ResultSet. Probably Pim's fault. ", e);
            return null;
        }

        return rows;
    }

    public static <T> T getAsEnum(String string) {
        String[] split = string.split("#");
        if (split.length == 0) return null;
        Class<T> clazz;
        try {
            clazz = (Class<T>) Class.forName(split[0]);
        } catch (ClassNotFoundException e) {
            TuxJSQL.getLogger().error("Unable to locate class for enum", e);
            return null;
        }

        return (T) Enum.valueOf((Class<? extends Enum>) clazz, split[1]);
    }

    public static String enumToString(Enum o) {
        return o.getClass().getCanonicalName() + "#" + o.name();
    }
}
