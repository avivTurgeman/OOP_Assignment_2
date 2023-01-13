package src.Ex2_1;

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


    /**
     * creating <I>'n'</I> text files where each file have maximum of <I>'bound'</I> lines
     * each run is different according to the <I>'seed'</I>
     * @param n int of number of files
     * @param seed int to set a frame
     * @param bound int max lines for each file
     * @return String[n] of the file names.
     */
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

    /**
     * This method returns the number of lines of all the files int the String[n] combine,
     * where <I>'n'</I> is the number of files.
     * this is a generic solution (each file at a time).
     * @param fileNames String[n] of <I>'n'</I> names of files.
     * @return int of combine lines count.
     */
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

    /**
     * this method returns the number of lines of all the files int the String[n] combine,
     * where <I>'n'</I> is the number of files.
     * This solution works with one Thread.
     * @param fileNames String[n] of <I>'n'</I> names of files.
     * @return int of combine lines count.
     */
    public static int getNumOfLinesThreads(String[] fileNames) {

        int counter = 0;

        class LineCounterThreads extends Thread {

            private int counter;
            private final String fileName;


            public LineCounterThreads(String fileName) {
                this.fileName = fileName;
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
        LineCounterThreads[] threads = new LineCounterThreads[fileNames.length];

        for (int i = 1; i <= fileNames.length; i++) {
            String fileName = "file_" + i + ".txt";
            threads[i-1] = new LineCounterThreads(fileName);
        }

        for (int i = 0; i < fileNames.length; i++){
            threads[i].start();
        }

        for(LineCounterThreads thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter += thread.counter;
        }
        return counter;
    }

    /**
     * This method returns the number of lines of all the files int the String[n] combine,
     * where <I>'n'</I> is the number of files.
     * This solution works with a ThreadPool.
     * @param fileNames String[n] of <I>'n'</I> names of files.
     * @return int of combine lines count.
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {

        int counter = 0;
        ExecutorService pool = Executors.newFixedThreadPool(fileNames.length);
        Future<Integer>[] results = new Future[fileNames.length];

        class LinesThreadPool implements Callable<Integer> {
            private int counter;
            private final String fileName;

            LinesThreadPool(String fileName){
                this.fileName = fileName;
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

        for (Future<Integer> result : results) {
            try {
                counter += result.get();
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
}
