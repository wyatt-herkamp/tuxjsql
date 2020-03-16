package dev.tuxjsql.basic.sql.where;

import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.sql.SQLTable;
import dev.tuxjsql.core.sql.where.SubWhereStatement;
import dev.tuxjsql.core.sql.where.WhereStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public abstract class BasicWhereStatement<T> implements WhereStatement<T> {
    private T and;
    protected List<Object> whereObjects = new ArrayList<>();
    private TuxJSQL core;
    private static final String EQUALS = "=";
    protected SQLTable table;

    public BasicWhereStatement(T and, TuxJSQL core) {
        this.and = and;
        this.core = core;
    }

    public BasicWhereStatement(TuxJSQL core) {
        this.and = null;
        this.core = core;
    }


    @Override
    public WhereStatement<T> start(String s, String comparator, Object value) {
        if (!whereObjects.isEmpty()) {
            throw new IllegalArgumentException("You cant start twice");
        }
        whereObjects.add(new BasicWhere(s, value, comparator));
        return this;
    }

    @Override
    public WhereStatement<T> start(String s, Object value) {
        return start(s, EQUALS, value);
    }

    @Override
    public WhereStatement<T> start(SubWhereStatement s) {
        if (!whereObjects.isEmpty()) {
            throw new IllegalArgumentException("You cant start twice");
        }
        whereObjects.add(s);
        return this;
    }

    @Override
    public WhereStatement<T> start(Consumer<SubWhereStatement> s) {
        if (!whereObjects.isEmpty()) {
            throw new IllegalArgumentException("You cant start twice");
        }
        SubWhereStatement whereStatement = core.createSubWhereStatement();
        s.accept(whereStatement);
        whereObjects.add(whereStatement);
        return this;
    }

    @Override
    public SubWhereStatement<WhereStatement> start() {
        if (!whereObjects.isEmpty()) {
            throw new IllegalArgumentException("You cant start twice");
        }
        SubWhereStatement<WhereStatement> subWhere = core.createSubWhereStatement(this);
        whereObjects.add(subWhere);
        return subWhere;
    }

    @Override
    public WhereStatement<T> AND(String s, String comparator, Object value) {
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(new BasicWhere(s, value, comparator));
        return this;
    }

    @Override
    public WhereStatement<T> AND(String s, Object value) {
        return AND(s, EQUALS, value);
    }

    @Override
    public WhereStatement<T> AND(SubWhereStatement s) {
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(s);
        return this;
    }

    @Override
    public WhereStatement<T> AND(Consumer<SubWhereStatement> s) {
        SubWhereStatement whereStatement = core.createSubWhereStatement();
        s.accept(whereStatement);
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(whereStatement);
        return this;
    }

    @Override
    public SubWhereStatement<WhereStatement> AND() {
        SubWhereStatement<WhereStatement> subWhere = core.createSubWhereStatement(this);
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(subWhere);
        return subWhere;
    }

    @Override
    public WhereStatement<T> OR(String s, String comparator, Object value) {

        whereObjects.add(WhereSeperator.OR);
        whereObjects.add(new BasicWhere(s, value, comparator));
        return this;
    }

    @Override
    public WhereStatement<T> OR(String s, Object value) {
        return OR(s, EQUALS, value);
    }

    @Override
    public WhereStatement<T> OR(SubWhereStatement s) {
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(s);
        return this;
    }

    @Override
    public WhereStatement<T> OR(Consumer<SubWhereStatement> s) {
        SubWhereStatement whereStatement = core.createSubWhereStatement();
        s.accept(whereStatement);
        whereObjects.add(WhereSeperator.OR);
        whereObjects.add(whereStatement);
        return this;
    }

    @Override
    public SubWhereStatement<WhereStatement> OR() {
        SubWhereStatement<WhereStatement> subWhere = core.createSubWhereStatement(this);
        whereObjects.add(WhereSeperator.OR);
        whereObjects.add(subWhere);
        return subWhere;
    }

    @Override
    public WhereStatement<T> NOT(String s, String comparator, Object value) {
        whereObjects.add(WhereSeperator.NOT);
        whereObjects.add(new BasicWhere(s, value, comparator));
        return this;
    }

    @Override
    public WhereStatement<T> NOT(String s, Object value) {
        return NOT(s, EQUALS, value);
    }

    @Override
    public WhereStatement<T> NOT(SubWhereStatement s) {
        whereObjects.add(WhereSeperator.AND);
        whereObjects.add(s);
        return this;
    }

    @Override
    public WhereStatement<T> NOT(Consumer<SubWhereStatement> s) {

        SubWhereStatement whereStatement = core.createSubWhereStatement();
        s.accept(whereStatement);
        whereObjects.add(whereStatement);
        return this;
    }

    @Override
    public SubWhereStatement<WhereStatement> NOT() {


        SubWhereStatement<WhereStatement> subWhere = core.createSubWhereStatement(this);
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

    public void setAnd(T basicSelectStatement) {
        and = basicSelectStatement;
    }
}
