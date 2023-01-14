package src.Ex2_2;

import java.util.concurrent.*;

/**
 * CustomExecutor is a class that extends ThreadPoolExecutor and adds functionality for handling tasks with different priorities.
 * It uses a PriorityBlockingQueue to store the tasks and the priority of each task is determined by its TaskType.
 * The maximum pool size of the thread pool is set to the number of available processors minus one, and the core pool size
 * is set to half the number of available processors.
 * In addition to that, this class obtains an array that holds the number of tasks of each priority.
 */
public class CustomExecutor extends ThreadPoolExecutor {
    private final int[] QueuePriorityArr = new int[4];
    private static final int CorePoolSize = Runtime.getRuntime().availableProcessors() / 2;
    private static final int MaximumPoolSize = Runtime.getRuntime().availableProcessors() - 1;
    private static final long keepAliveTime = 300;

    /**
     * Constructs a CustomExecutor with the default core pool size, maximum pool size, and keep alive time.
     * The thread pool uses a PriorityBlockingQueue to hold the tasks.
     */
    public CustomExecutor() {
        super(
                CorePoolSize,
                MaximumPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>());
    }

    /**
     * Submits a task to the thread pool.
     * @param task  the task to be executed
     * @return a Future representing the task
     * @throws NullPointerException if the task is null
     */
    public <V> Future<V> submit(src.Ex2_2.Task<V> task) throws NullPointerException{
        if(task != null){
            Adapter<V> taskAdapter = new Adapter<>(task, task.getTaskType().getPriorityValue());
            this.QueuePriorityArr[task.getTaskType().getPriorityValue()]++;
            super.execute(taskAdapter);
            return taskAdapter;
        }
        throw new NullPointerException();
    }
    /**
     * Submits a callable task to the thread pool with a specified TaskType.
     * @param callable the task to be executed
     * @param taskType the TaskType of the task
     * @return a Future representing the task
     * The Future's get method will return the result of callable upon successful completion
     * @throws NullPointerException if the callable or taskType is null
     */
    public <V> Future<V> submit(Callable<V> callable, TaskType taskType) throws NullPointerException{
        if((callable != null) && (taskType != null))
            return this.submit(Task.createTask(callable, taskType));
        throw new NullPointerException();
    }

    /**
     * Submits a callable task to the thread pool. The task will be assigned the default TaskType.
     * @param <V>callable the task to be executed
     * @return a Future representing the task
     The Future's get method will return the result of callable upon successful completion
     * @throws NullPointerException if the callable is null
     */

    @Override
    public <V> Future<V> submit(Callable<V> callable) throws NullPointerException{
        if(callable != null)
            return this.submit(Task.createTask(callable));
        throw new NullPointerException();
    }

    /**
     * This method is called before a task is executed.
     * This method decrements the count of the priority of the task in the QueuePriorityArr.
     * @param t the thread that will run task r, which is a worker thread in the thread pool.
     * @param r the task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r){
        this.QueuePriorityArr[((Adapter<?>) r).getPriority()]--;
    }
    /**
     * Returns the current maximum priority among the tasks in the queue.
     * @return the current maximum priority or -1 if the queue is empty
     */
    public int getCurrentMax() {

        for(int i = 1; i < QueuePriorityArr.length; i++){
            if(QueuePriorityArr[i] >0){
                return i;
            }
        }
        return -1;
    }
    /**
     * Initiates an orderly shutdown in which previously submitted tasks are executed,
     * but no new tasks will be accepted.
     * The method waits for the termination of all running tasks and for the tasks in the queue to be completed.
     * The method will wait for a maximum of 100 milliseconds for the termination of the tasks
     */
    public void gracefullyTerminate(){
        super.shutdown();
        try
        {
            boolean await = super.awaitTermination(100, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}