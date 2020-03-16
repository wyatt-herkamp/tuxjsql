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

    /**
     * Complete the action
     * This is a blocking call and will block the current thread until complete
     *
     * @return The response
     * @throws InterruptedException if the thread was interrupted
     */
    public T complete() throws InterruptedException {
        Future<T> future = tuxjsql.getExecutor().submit(callable);
        try {
            return future.get();
        } catch (ExecutionException e) {
            TuxJSQL.getLogger().error(ERROR_MESSAGE, e);
        }
        return null;
    }

    /**
     * Complete the action
     * This is a blocking call and will block the current thread until complete
     *
     * @param time how much time
     * @param unit the unit of time measured
     * @return The response
     * @throws InterruptedException if the thread was interrupted
     */
    public T complete(long time, TimeUnit unit) throws TimeoutException, InterruptedException {
        Future<T> future = tuxjsql.getExecutor().submit(callable);
        try {
            return future.get(time, unit);
        } catch (ExecutionException e) {
            TuxJSQL.getLogger().error(ERROR_MESSAGE, e);
        }
        return null;
    }

    /**
     * Queues the action the action will execute and the rest of the code will go on
     */
    public void queue() {
        tuxjsql.getExecutor().submit(callable);
    }

    /**
     * Queues the action and takes in a Consumer to handle it once its done
     *
     * @param handler your handler
     */
    public void queue(Consumer<T> handler) {
        CompletableFuture.runAsync(() -> {
            try {
                handler.accept(callable.call());
            } catch (Exception e) {
                TuxJSQL.getLogger().error("Unable to get value from callable", e);
            }
        }, tuxjsql.getExecutor());

    }

    /**
     * Returns the Callble for this action.
     * <p>
     * This was added for the TuxORM.
     *
     * @return the callable for this action.
     */
    public Callable<T> getCallable() {
        return callable;
    }
}
