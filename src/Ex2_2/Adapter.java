package src.Ex2_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * The Adapter class is an implementation of the FutureTask class and Comparable interface.
 * It is used to encapsulate a Callable and a priority value, which can be used to compare and order instances of this class.
 * @param <V> the type of the result returned by the encapsulated Callable.
 */

public class Adapter<V> extends FutureTask<V> implements Comparable<Adapter<V>> {

    private final int priority;

    /**
     * Constructs a new Adapter instance, encapsulating the provided Callable and priority value.
     * @param callable the Callable to be encapsulated by this Adapter instance
     * @param priority the priority value of this Adapter instance
     */
    public Adapter(Callable<V> callable, int priority) {
        super(callable);
        this.priority = priority;
    }

    /**
     * Compares this Adapter instance with the specified Adapter instance which is given.
     * @param taskFuture the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object's priority is less than, equal to, or greater than the specified object's priority.
     */
    @Override
    public int compareTo(Adapter taskFuture) {
        return Integer.compare(this.priority, taskFuture.priority);
    }

    /**
     * @return the priority value of this Adapter instance
     */
    public int getPriority() {
        return priority;
    }
}