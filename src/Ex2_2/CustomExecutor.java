package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {

    private final int[] QueuePriorityArr = new int[4];
    private static final int CorePoolSize = Runtime.getRuntime().availableProcessors() / 2;
    private static final int MaximumPoolSize = Runtime.getRuntime().availableProcessors() - 1;
    private static final long keepAliveTime = 300;

    public CustomExecutor() {
        super(
                CorePoolSize,
                MaximumPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>());
    }

    public <V> Future<V> submit(Task<V> task) throws NullPointerException{
        if(task != null){
            Adapter<V> taskAdapter = new Adapter<>(task, task.getTaskType().getPriorityValue());
            this.QueuePriorityArr[task.getTaskType().getPriorityValue()]++;
            super.execute(taskAdapter);
            return taskAdapter;
        }
        throw new NullPointerException();
    }

    public <V> Future<V> submit(Callable<V> callable, TaskType taskType) throws NullPointerException{
        if((callable != null) && (taskType != null))
            return this.submit(Task.createTask(callable, taskType));
        throw new NullPointerException();
    }

    @Override
    public <V> Future<V> submit(Callable<V> callable) throws NullPointerException{
        if(callable != null)
            return this.submit(Task.createTask(callable));
        throw new NullPointerException();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r){
        this.QueuePriorityArr[((Adapter<?>) r).getPriority()]--;
    }

    public int getCurrentMax() {

        for(int i = 1; i < QueuePriorityArr.length; i++){
            if(QueuePriorityArr[i] >0){
                return i;
            }
        }
        return -1;
    }

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