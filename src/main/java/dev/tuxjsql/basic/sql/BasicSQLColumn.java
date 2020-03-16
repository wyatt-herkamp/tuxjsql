package dev.tuxjsql.basic.sql;

import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.sql.SQLColumn;
import dev.tuxjsql.core.sql.SQLDataType;
import dev.tuxjsql.core.sql.SQLTable;

import java.util.List;

public abstract class BasicSQLColumn implements SQLColumn {
    protected String name;
    protected Object defaultValue;
    private List<String> dataTypeRules;
    private boolean notNull, unique, autoIncrement, primaryKey;
    private SQLColumn foreignKey;
    private SQLTable table;
    private SQLDataType type;
private TuxJSQL tuxJSQL;
    public BasicSQLColumn() {

    }

    public BasicSQLColumn(String name, Object defaultValue, List<String> dataTypeRules, boolean notNull, boolean unique, boolean autoIncrement, boolean primaryKey, SQLColumn foreignKey, SQLTable table, SQLDataType type, TuxJSQL tuxJSQL) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.dataTypeRules = dataTypeRules;
        this.notNull = notNull;
        this.unique = unique;
        this.autoIncrement = autoIncrement;
        this.primaryKey = primaryKey;
        this.foreignKey = foreignKey;
        this.table = table;
        this.type = type;
        this.tuxJSQL = tuxJSQL;
    }

    protected String buildDataType() {
        if(type instanceof BasicDataTypes) type = tuxJSQL.getBuilder().convertDataType((BasicDataTypes) type);
        StringBuilder builder = new StringBuilder(type.key());
        if (!dataTypeRules().isEmpty()) {
            builder.append("(");
            int i = 0;
            for (String s : dataTypeRules) {
                if (i != 0) builder.append(", ");
                builder.append(s);
                i++;
            }
            builder.append(")");
        }
        return builder.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object defaultValue() {
        return defaultValue;
    }

    @Override
    public boolean unique() {
        return unique;
    }

    @Override
    public boolean notNull() {
        return notNull;
    }

    @Override
    public boolean autoIncrement() {
        return autoIncrement;
    }

    @Override
    public boolean primaryKey() {
        return primaryKey;
    }

    @Override
    public boolean isForeignKey() {
        return foreignKey != null;
    }

    @Override
    public SQLColumn foreignKey() {
        return foreignKey;
    }

    @Override
    public SQLTable getTable() {
        return table;
    }

    @Override
    public SQLDataType getDataType() {
        return type;
    }

    @Override
    public List<String> dataTypeRules() {
        return dataTypeRules;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setDataTypeRules(List<String> dataTypeRules) {
        this.dataTypeRules = dataTypeRules;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setTuxJSQL(TuxJSQL tuxJSQL) {
        this.tuxJSQL = tuxJSQL;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setForeignKey(SQLColumn foreignKey) {
        this.foreignKey = foreignKey;
    }

    public void setTable(SQLTable table) {
        this.table = table;
    }

    public void setType(SQLDataType type) {
        this.type = type;
    }
}
