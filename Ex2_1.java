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

            String fileName = "file_" + (10 + i) + ".txt";
            fileNames[i - 1] = fileName;

            File file = new File(fileName);
            if (!file.exists()) {
                int lines = rand.nextInt(bound);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    for (int j = 0; j < lines; j++) {
                        bw.write("Hello World");
                        bw.newLine();
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

            String fileName = "file_" + (10 + i) + ".txt";

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

    public int getNumOfLinesThreads(String[] fileNames) {

        return 0;
    }

    public int getNumOfLinesThreadPool(String[] fileNames) {

        return 0;
    }

    public static void main(String[] args) {
        String[] fileNames = createTextFiles(10, 4, 100);

        System.out.println(getNumOfLines(fileNames));
    }
}
