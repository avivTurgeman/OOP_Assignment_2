package src.Ex2_1;

public class main {
    public static void main(String[] args) {
        long start, end;
        int filesAmount = 500;
        int seed = 4;
        int bound = 100;
        int noThreads, oneThread, ThreadPool;


        String[] fileNames = Ex2_1.createTextFiles(filesAmount, seed, bound);
        System.out.println(filesAmount + " files created");

        start = System.currentTimeMillis();
        noThreads = Ex2_1.getNumOfLines(fileNames);
        end = System.currentTimeMillis();
        System.out.println("No Threads time: " + (end-start) + "ms");

        start = System.currentTimeMillis();
        oneThread = Ex2_1.getNumOfLinesThreads(fileNames);
        end = System.currentTimeMillis();
        System.out.println("1 Thread per file time: " + (end-start) + "ms");

        start = System.currentTimeMillis();
        ThreadPool = Ex2_1.getNumOfLinesThreadPool(fileNames);
        end = System.currentTimeMillis();
        System.out.println("ThreadPool time: " + (end-start) + "ms");

        System.out.println("\nNo Threads Lines amount: " + noThreads + "\n1 Thread Lines amount:   " + oneThread + "\nThreadPool Lines amount: " + ThreadPool);
    }
}
