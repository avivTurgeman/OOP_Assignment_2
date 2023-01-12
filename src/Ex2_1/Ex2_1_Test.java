package src.Ex2_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ex2_1_Test {

    private static String[] names;

    @Test
    void main() {
        int noThreads, oneThread, ThreadPool;
        int filesAmount = 100;
        int seed = 4;
        int bound = 100;

        String[] fileNames = Ex2_1.createTextFiles(filesAmount, seed, bound);

        noThreads = Ex2_1.getNumOfLines(fileNames);
        oneThread = Ex2_1.getNumOfLinesThreads(fileNames);
        ThreadPool = Ex2_1.getNumOfLinesThreadPool(fileNames);

        assertAll(
                ()-> assertEquals(noThreads,oneThread),
                () -> assertEquals(oneThread, ThreadPool)
        );

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Ex2_1.deleteTextFiles(filesAmount);
                
    }
}