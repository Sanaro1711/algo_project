package algo_project;

import jRAPL.SyncEnergyMonitor;
import jRAPL.EnergyStats;
import jRAPL.EnergyDiff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DataGenerator {
    // this class is responsible for generating the data
    // it produces 400 CSV files for each algorithm, containing the input size & energy used as x & y coordinates
    // this is to simplify graphing for later




    public static void main() throws IOException {

        // makes the energy tracker object
        SyncEnergyMonitor monitor = new SyncEnergyMonitor();
        monitor.activate();

        // stores the path for the input & output CSVs
        // TODO: CHANGE THESE TO GET THE CODE RUNNING!!!
        String inputPath = "D:/ucd/comp20290/algo_project/SortingAlgorithms/src/algo_project/CSVs";
        String outputPath = "D:/ucd/comp20290/algo_project/SortingAlgorithms/src/algo_project/Output";

        // BUBBLE SORT
        // reverse-sorted, 1x400
        // sorted, 1x400
        // random case, 10x40

        // opens the CSV file
        int[] reverseSorted = readCSV(inputPath + "/sorted_reverse.csv", 500000);

        for(int i = 0; i < 400; i++) {
            // 25k, 50k, 75k, 100k, 200k, 300k, 400k, 500k

            // assigns the initial size
            int initSize = 25000;

            // sets up the file & output for the iteration
            File output = new File(outputPath + "/BubbleSort/Random/" + i + ".csv");
            FileWriter writer = new FileWriter(output);

            // cycles through the sizes
            // writes the input size & energy consumed as coordinates into the CSV
            for(int j = 0; j < 8; j++) {
                // makes a copy of the desired size
                int[] array = Arrays.copyOf(reverseSorted, initSize);
                EnergyStats before = monitor.getSample();
                SampleCode.bubble_sort(array);
                EnergyStats after = monitor.getSample();

                // gets the CPU usage
                double energy = after.getCore();
                // stores the size & CPU usage as
                writer.write(initSize + "," + energy + "\n");

                // increases the size
                if(j < 3) {initSize += 25000;}
                else {initSize += 100000;}
            }
        }

        // sorted file

        int[] sorted = readCSV(inputPath + "sorted.csv", 500000);

        for(int i = 0; i < 400; i++) {
            // 25k, 50k, 75k, 100k, 200k, 300k, 400k, 500k

            // assigns the initial size
            int initSize = 25000;

            // sets up the file & output for the iteration
            File output = new File(outputPath + "BubbleSort/Sorted/" + i + ".csv");
            FileWriter writer = new FileWriter(output);

            // cycles through the sizes
            // writes the input size & energy consumed as coordinates into the CSV
            for(int j = 0; j < 8; j++) {
                // makes a copy of the desired size
                int[] array = Arrays.copyOf(sorted, initSize);
                EnergyStats before = monitor.getSample();
                SampleCode.bubble_sort(array);
                EnergyStats after = monitor.getSample();

                // gets the CPU usage
                double energy = after.getCore();
                // stores the size & CPU usage as
                writer.write(initSize + "," + energy + "\n");

                // increases the size
                if(j < 3) {initSize += 25000;}
                else {initSize += 100000;}
            }
        }

        // randomly sorted files
        // uses 10 CSVs
        for(int x = 0; x < 10; x++) {

            int[] array = readCSV(inputPath + "/random_" + (x+1) + ".csv", 500000);

            for(int i = 0; i < 40; i++) {
                int initSize = 25000;
                File output = new File(outputPath + "BubbleSort/Random/" + x + "/" + i + ".csv");
                FileWriter writer = new FileWriter(output);

                for(int j = 0; j < 8; j++) {
                    // makes a copy of the desired size
                    int[] array1 = Arrays.copyOf(sorted, initSize);
                    EnergyStats before = monitor.getSample();
                    SampleCode.bubble_sort(array1);
                    EnergyStats after = monitor.getSample();

                    // gets the CPU usage
                    double energy = after.getCore();
                    // stores the size & CPU usage as
                    writer.write(initSize + "," + energy + "\n");

                    // increases the size
                    if(j < 3) {initSize += 25000;}
                    else {initSize += 100000;}
                }
            }
        }


        // MERGE SORT

        for(int i = 0; i < 400; i++) {

        }


        // QUICK SORT
        for(int i = 0; i < 400; i++) {

        }

    }


    public static int[] readCSV(String path, int size) throws IOException {
        int[] output = new int[size];
        File file = new File(path);
        Scanner sc = new Scanner(file);

        for(int i = 0; i < size; i++) {
            int readNumber = sc.nextInt();
            output[i] = readNumber;
        }

        return output;

    }

}
