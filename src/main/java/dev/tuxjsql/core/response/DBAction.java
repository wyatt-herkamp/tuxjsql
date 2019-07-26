package dev.tuxjsql.core.response;

import dev.tuxjsql.core.TuxJSQL;

import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * This is what is returned whenever you want to do something on the database.
 * The code does not execute until you run a method inside this class.
 * This idea was taken from the JDA Action.
 */
public class DBAction<T> {
    private static final String ERROR_MESSAGE = "Unable to get value from Future";
    private Callable<T> callable;
    private TuxJSQL tuxjsql;

    /**
     * This is the main constructor.
     *
     * @param callable the callable the action to be completed.
     * @param tuxjsql  The TuxJSQL so we can access the Executor service.
     */
    public DBAction(Callable<T> callable, TuxJSQL tuxjsql) {
        this.callable = callable;
        this.tuxjsql = tuxjsql;
    }

    public T complete() {
        Future<T> future = tuxjsql.getExecutor().submit(callable);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            TuxJSQL.getLogger().error(ERROR_MESSAGE, e);
        }
        return null;
    }

    public T complete(long time, TimeUnit unit) throws TimeoutException {
        Future<T> future = tuxjsql.getExecutor().submit(callable);
        try {
            return future.get(time, unit);
        } catch (InterruptedException | ExecutionException e) {
            TuxJSQL.getLogger().error(ERROR_MESSAGE, e);
        }
        return null;

    }

    public void queue() {
        tuxjsql.getExecutor().submit(callable);
    }



    public void queue(Consumer<T> handler) {
        CompletableFuture.runAsync(() -> {
            try {
                handler.accept(callable.call());
            } catch (Exception e) {
                TuxJSQL.getLogger().error("Unable to get value from callable", e);
            }
        }, tuxjsql.getExecutor());

    }


}
