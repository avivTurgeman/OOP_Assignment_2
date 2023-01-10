import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {

    private int CurrentMaxPriority;
    private static final int CorePoolSize = Runtime.getRuntime().availableProcessors() / 2;
    private static final int MaximumPoolSize = Runtime.getRuntime().availableProcessors() - 1;
    private static final long keepAliveTime = 300L;



    public CustomExecutor() {
        super(
                CorePoolSize,
                MaximumPoolSize,
                keepAliveTime,
                 TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>());
    }

    public static <V> Future<V> submit(Task<V> task){


        super.execute();
    }

    public <V> Future<V> submit(Callable<V> callable, TaskType taskType){
        if((callable != null) && (taskType != null))
            return new submit(Task.createTask(callable, taskType));
        throw new NullPointerException();
    }

    @Override
    public <V> Future<V> submit(Callable<V> callable){
        if(callable != null)
            return new submit(Task.createTask(callable));
        throw new NullPointerException();
    }



    public int getCurrentMax() {
        return CurrentMaxPriority;
    }

    public void gracefullyTerminate() {
        super.shutdown();
    }
}
