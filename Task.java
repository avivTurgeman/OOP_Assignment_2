import java.util.concurrent.Callable;

public class Task<V> implements Callable<V>,Comparable<Task<V>>{

    private final TaskType taskType;
    private final Callable<V> callable;
    private final int priority;

    private Task(Callable<V> callable, TaskType taskType) {
        this.taskType = taskType;
        this.callable = callable;
        this.priority = taskType.getPriorityValue();
    }

    public static <V> Task<V> createTask(Callable<V> callable, TaskType taskType) {

        return new Task<>(callable, taskType);
    }

    public static <V> Task<V> createTask(Callable<V> callable){
        return new Task<>(callable, TaskType.OTHER);
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    @Override
    public int compareTo(Task task) {
        return Integer.compare(this.priority, task.priority);
    }

    @Override
    public V call() throws Exception {
        return this.callable.call();
    }

}