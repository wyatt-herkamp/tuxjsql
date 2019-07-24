package dev.tuxjsql.core.exceptions;

public class ColumnNotFoundException extends RuntimeException {
    public ColumnNotFoundException(String s) {
        super(String.format("%s Was not found", s));
    }
}
