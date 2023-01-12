package Ex2_2;

import java.util.concurrent.Callable;

/**
 * The Task class represents a task that can be executed by the program. It is implemented as a Callable object and
 * can be used in the Executor framework or any other framework that uses Callable objects. The Task class also
 * implements the Comparable interface, which allows tasks to be compared and sorted by their priority.
 * @param <V> The type of object returned by the task's call() method.
 */
public class Task<V> implements Callable<V>,Comparable<Task<V>>{

    /**
     * The task's type, represented by an enumeration.
     */
    private final TaskType taskType;
    /**
     * The callable object that the task wraps.
     */
    private final Callable<V> callable;
    /**
     * The task's priority, determined by its task type.
     */
    private final int priority;

    /**
     * Creates a new task with the given callable object and task type.
     * @param callable The callable object that the task wraps.
     * @param taskType The task's type.
     */
    private Task(Callable<V> callable, TaskType taskType) {
        this.taskType = taskType;
        this.callable = callable;
        this.priority = taskType.getPriorityValue();
    }

    /**
     * Creates a new task with the given callable object and task type.
     * @param callable The callable object that the task wraps.
     * @param taskType The task's type.
     * @param <V> The type of object returned by the task's call() method.
     * @return A new task with the given callable object and task type.
     */
    public static <V> Task<V> createTask(Callable<V> callable, TaskType taskType) {

        return new Task<>(callable, taskType);
    }

    /**
     * Creates a new task with the given callable object and default task type.
     * @param callable The callable object that the task wraps.
     * @param <V> The type of object returned by the task's call() method.
     * @return A new task with the given callable object and default task type.
     */
    public static <V> Task<V> createTask(Callable<V> callable){
        return new Task<>(callable, TaskType.OTHER);
    }

    /**
     * @return The task's type.
     */
    public TaskType getTaskType() {
        return this.taskType;
    }

    /**
     * Compares this task to another task based on their priority.
     * @param task The task to compare to.
     * @return A negative integer, zero, or a positive integer as this task's priority is less than, equal to, or greater than the specified task's priority.
     */
    @Override
    public int compareTo(Task task) {
        return Integer.compare(this.priority, task.priority);
    }

    /**
     * Executes the task's callable object and returns the result.
     * @return The result of the callable's execution.
     * @throws Exception any exception that may be thrown by the callable's execution.
     */
    @Override
    public V call() throws Exception {
        return this.callable.call();
    }
}