package me.kingtux.tuxjsql.core;

import java.util.ArrayList;
import java.util.List;

public class ColumnType {
    private DataType type;
    private List<String> rules;

    public ColumnType(DataType type) {
        this(type, new ArrayList<>());
    }

    public ColumnType(DataType type, List<String> rules) {
        this.type = type;
        this.rules = rules;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }
}
