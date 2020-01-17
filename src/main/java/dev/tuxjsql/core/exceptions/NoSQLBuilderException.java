package dev.tuxjsql.core.exceptions;

public class NoSQLBuilderException extends Exception {
    public NoSQLBuilderException(String s, ClassNotFoundException e) {
        super(s, e);
    }
}
