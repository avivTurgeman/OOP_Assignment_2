import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TaskFuture<V> extends FutureTask<V> implements Comparable<TaskFuture<V>>{

    private final int priority;


    public TaskFuture(Callable<V> callable, int priority) {
        super(callable);
        this.priority = priority;
    }



    @Override
    public int compareTo(TaskFuture taskFuture) {
        return Integer.compare(this.priority, taskFuture.priority);
    }
}
