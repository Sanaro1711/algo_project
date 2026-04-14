package algo_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class CSVgen {

    // I used this to make the 1m random CSVs
    // results are stored as "random_x" in /CSVs
    // you can run this to generate the CSVs again, or modify their generation in case something is wrong

    // [v] sorted CSV - reused
    // [v] reverse sorted CSV - reused
    // [x] unsorted CSVs
    // [v] alternating CSV - reused
    // [v] evenly-partitioned CSV - reused
    // [v] counting sort CSVs - reused

    public static void main(String args[]) throws IOException {

        // makes 10 unsorted CSVs with size 1m
        // we can restrict their input size later

        for(int x = 0; x < 10; x++) {

            // makes a new CSV file, including number in name
            File output = new File("D:\\ucd\\comp20290\\algo_project\\SortingAlgorithms\\src\\algo_project\\CSVs\\random_" + (x+1) + ".csv");
            // sets up a FileWriter using the newly made CSV
            FileWriter writer = new FileWriter(output);
            // sets up an RNG
            Random r = new Random();

            // sets the size to 1m
            int size = 1000000;
            // writes a random number into the CSV
            for(int i = 0; i < size; i++) {
                // to prevent an empty final line in the CSV
                if(i == size-1) {
                    writer.write(String.valueOf(r.nextInt()));
                } else {
                    // writes a random unbounded integer into the CSV
                    writer.write(String.valueOf(r.nextInt()) + "\n");
                }
            }
            // closes the FileWriter after it's finished
            writer.close();
        }
    }
}
