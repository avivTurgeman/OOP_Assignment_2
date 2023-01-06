import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

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

    public int getNumOfLinesThreadPool(String[] fileNames) {

        return 0;
    }

    public static void main(String[] args) {
        String[] fileNames = createTextFiles(5, 4, 20);

        System.out.println(getNumOfLinesThreads(fileNames));

    }
}
