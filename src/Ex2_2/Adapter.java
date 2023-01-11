package Ex2_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Adapter<V> extends FutureTask<V> implements Comparable<Adapter<V>>{

    private final int priority;


    public Adapter(Callable<V> callable, int priority) {
        super(callable);
        this.priority = priority;
    }

    @Override
    public int compareTo(Adapter taskFuture) {
        return Integer.compare(this.priority, taskFuture.priority);
    }

    public int getPriority() {
        return priority;
    }
}