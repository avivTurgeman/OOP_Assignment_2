import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1 {
    public static String[] createTextFiles(int n, int seed, int bound) {

        String[] fileNames = new String[n];
        Random rand = new Random(seed);

        for (int i = 1; i <= n; i++) {

            String fileName = "file_" + i + ".txt";
            fileNames[i - 1] = fileName;

            File file = new File(fileName);
            if (!file.exists()) {
                int lines = rand.nextInt(bound);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    for (int j = 0; j < lines; j++) {
                        bw.write("Hello World!");
                        if (j != lines - 1) {
                            bw.newLine();
                        }
                    }
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("File already exists.");
            }

        }
        return fileNames;
    }

    public static int getNumOfLines(String[] fileNames) {

        int counter = 0;

        for (int i = 1; i <= fileNames.length; i++) {

            String fileName = "file_" + i + ".txt";

            try {

                FileReader file = new FileReader(fileName);

                BufferedReader br = new BufferedReader(file);

                while ((br.readLine()) != null) {
                    counter++;
                }

                br.close();

            } catch (FileNotFoundException ex) {
                System.out.println("Unable to open file '" + fileName + "'");
            } catch (IOException ex) {
                System.out.println("Error reading file '" + fileName + "'");
            }
        }

        return counter;
    }

    public static int getNumOfLinesThreads(String[] fileNames) {

        int counter = 0;

        class LineCounterThreads extends Thread {

            private int counter;
            private String fileName;

            public LineCounterThreads(String fileName) {
                this.fileName = fileName;
                counter = 0;
            }

            public LineCounterThreads(){
                fileName = "";
                counter = 0;
            }

            public void run() {
                try {

                    FileReader file = new FileReader(fileName);

                    BufferedReader br = new BufferedReader(file);

                    while ((br.readLine()) != null) {
                        counter++;
                    }

                    br.close();

                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + fileName + "'");
                } catch (IOException ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                }
            }
        }
        ;

        for (int i = 1; i <= fileNames.length; i++) {
            String fileName = "file_" + i + ".txt";
            LineCounterThreads lct = new LineCounterThreads(fileName);
            lct.start();

            try {
                lct.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            counter += lct.counter;
        }


        return counter;
    }

    public static int getNumOfLinesThreadPool(String[] fileNames) {

        int counter = 0;
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Future<Integer> results[] = new Future[fileNames.length];

        class LinesThreadPool implements Callable<Integer> {
            private int counter;
            private final String fileName;

            LinesThreadPool(String fileName){
                this.fileName = fileName;
                counter = 0;
            }

            LinesThreadPool(){
                fileName = "";
                counter = 0;
            }

            @Override
            public Integer call() throws Exception {
                try {

                    FileReader file = new FileReader(fileName);

                    BufferedReader br = new BufferedReader(file);

                    while ((br.readLine()) != null) {
                        counter++;
                    }

                    br.close();

                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + fileName + "'");
                } catch (IOException ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                }

                return counter;
            }
        };

        for(int i = 1; i <= fileNames.length; i++){
            String fileName = "file_" + i + ".txt";
            results[i - 1] = pool.submit(new LinesThreadPool(fileName));
        }

        for(int i = 0; i < results.length; i++){
            try {
                counter += results[i].get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();


        return counter;
    }

    public static void deleteTextFiles(int n){

        ExecutorService pool = Executors.newFixedThreadPool(3);

        class LinesThreadPool implements Callable<String> {
            private final String fileName;

            LinesThreadPool(String fileName){
                this.fileName = fileName;
            }

            LinesThreadPool(){
                fileName = "";
            }

            @Override
            public String call() throws Exception {

                // Create a File object for the file you want to delete
                File file = new File(fileName);

                // Check if the file exists before attempting to delete it
                if (file.exists()) {
                    // Delete the file
                    file.delete();
                } else {
                    System.out.println("File not found");
                }

                return fileName;
            }
        };

        for(int i = 1; i <= n; i++){
            String fileName = "file_" + i + ".txt";
            pool.submit(new LinesThreadPool(fileName));
        }

        pool.shutdown();
    }

    public static void main(String[] args) {
        long start, end;

        start = System.currentTimeMillis();
        String[] fileNames = createTextFiles(200, 4, 100);
        end = System.currentTimeMillis();
        System.out.println("time to create: " + (end-start) + "ms");

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        deleteTextFiles(100);

        start = System.currentTimeMillis();
        getNumOfLines(fileNames);
        end = System.currentTimeMillis();
        System.out.println("No Threads time: " + (end-start) + "ms");

        start = System.currentTimeMillis();
        getNumOfLinesThreads(fileNames);
        end = System.currentTimeMillis();
        System.out.println("1 Threads time: " + (end-start) + "ms");

        start = System.currentTimeMillis();
        getNumOfLinesThreadPool(fileNames);
        end = System.currentTimeMillis();
        System.out.println("ThreadPool time: " + (end-start) + "ms");

    }
}
