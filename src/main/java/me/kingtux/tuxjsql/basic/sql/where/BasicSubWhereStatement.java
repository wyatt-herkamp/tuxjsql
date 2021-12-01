package me.kingtux.tuxjsql.basic.sql.where;

import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.sql.SQLTable;
import me.kingtux.tuxjsql.core.sql.where.SubWhereStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class BasicSubWhereStatement<T> implements SubWhereStatement<T> {
    protected T and;
    protected List<Object> whereObjects = new ArrayList<>();
    protected TuxJSQL core;
    private static final String EQUALS = "=";
    protected SQLTable table;

    public BasicSubWhereStatement(T and, TuxJSQL core) {
        this.and = and;
        this.core = core;
    }

    public BasicSubWhereStatement(TuxJSQL core) {
        this.and = null;
        this.core = core;
    }


    @Override
    public SubWhereStatement<T> start(String s, String comparator, Object value) {
        if (!whereObjects.isEmpty()) {
            throw new IllegalArgumentException("You cant start twice");
        }
        whereObjects.add(new BasicWhere(s, value, comparator));
        return this;
    }

    @Override
    public SubWhereStatement<T> start(String s, Object value) {
        return start(s, EQUALS, value);
    }

    @Override
    public SubWhereStatement<T> start(SubWhereStatement s) {
        if (!whereObjects.isEmpty()) {
            throw new IllegalArgumentException("You cant start twice");
        }
        whereObjects.add(s);
        return this;
    }

    @Override
    public SubWhereStatement<T> start(Consumer<SubWhereStatement> s) {
        if (!whereObjects.isEmpty()) {
            throw new IllegalArgumentException("You cant start twice");
        }
        SubWhereStatement SubWhereStatement = core.createSubWhereStatement();
        s.accept(SubWhereStatement);
        whereObjects.add(SubWhereStatement);
        return this;
    }

    @Override
    public SubWhereStatement<SubWhereStatement> start() {
        if (!whereObjects.isEmpty()) {
            throw new IllegalArgumentException("You cant start twice");
        }
        SubWhereStatement<SubWhereStatement> subWhere = core.createSubWhereStatement(this);
        whereObjects.add(subWhere);
        return subWhere;
    }

    @Override
    public SubWhereStatement<T> AND(String s, String comparator, Object value) {
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(new BasicWhere(s, value, comparator));
        return this;
    }

    @Override
    public SubWhereStatement<T> AND(String s, Object value) {
        return AND(s, EQUALS, value);
    }

    @Override
    public SubWhereStatement<T> AND(SubWhereStatement s) {
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(s);
        return this;
    }

    @Override
    public SubWhereStatement<T> AND(Consumer<SubWhereStatement> s) {
        SubWhereStatement SubWhereStatement = core.createSubWhereStatement();
        s.accept(SubWhereStatement);
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(SubWhereStatement);
        return this;
    }

    @Override
    public SubWhereStatement<SubWhereStatement> AND() {
        SubWhereStatement<SubWhereStatement> subWhere = core.createSubWhereStatement(this);
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(subWhere);
        return subWhere;
    }

    @Override
    public SubWhereStatement<T> OR(String s, String comparator, Object value) {

        whereObjects.add(WhereSeperator.OR);
        whereObjects.add(new BasicWhere(s, value, comparator));
        return this;
    }

    @Override
    public SubWhereStatement<T> OR(String s, Object value) {
        return OR(s, EQUALS, value);
    }

    @Override
    public SubWhereStatement<T> OR(SubWhereStatement s) {
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(s);
        return this;
    }

    @Override
    public SubWhereStatement<T> OR(Consumer<SubWhereStatement> s) {
        SubWhereStatement SubWhereStatement = core.createSubWhereStatement();
        s.accept(SubWhereStatement);
        whereObjects.add(WhereSeperator.OR);
        whereObjects.add(SubWhereStatement);
        return this;
    }

    @Override
    public SubWhereStatement<SubWhereStatement> OR() {
        SubWhereStatement<SubWhereStatement> subWhere = core.createSubWhereStatement(this);
        whereObjects.add(WhereSeperator.OR);
        whereObjects.add(subWhere);
        return subWhere;
    }

    @Override
    public SubWhereStatement<T> NOT(String s, String comparator, Object value) {
        whereObjects.add(WhereSeperator.NOT);
        whereObjects.add(new BasicWhere(s, value, comparator));
        return this;
    }

    @Override
    public SubWhereStatement<T> NOT(String s, Object value) {
        return NOT(s, EQUALS, value);
    }

    @Override
    public SubWhereStatement<T> NOT(SubWhereStatement s) {


        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(s);
        return this;
    }

    @Override
    public SubWhereStatement<T> NOT(Consumer<SubWhereStatement> s) {

        SubWhereStatement subWhereStatement = core.createSubWhereStatement();
        s.accept(subWhereStatement);
        whereObjects.add(subWhereStatement);
        return this;
    }

    @Override
    public SubWhereStatement<SubWhereStatement> NOT() {


        SubWhereStatement<SubWhereStatement> subWhere = core.createSubWhereStatement(this);
        whereObjects.add(WhereSeperator.NOT);
        whereObjects.add(subWhere);
        return subWhere;
    }

    @Override
    public T and() {
        return and;
    }

    public void setTable(SQLTable table) {
        this.table = table;
    }
}
