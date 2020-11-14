package dev.tuxjsql.core.tools;

@FunctionalInterface
public interface SimpleSupplier<T> {
    T get() throws Exception;
}
