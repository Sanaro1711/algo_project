package algo_project;

import jRAPL.SyncEnergyMonitor;
import jRAPL.EnergyStats;
import jRAPL.EnergyDiff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

// this class is responsible for generating the data
// it produces the required number of CSV files for each algorithm,
// containing the input size & energy used as x & y coordinates
// this is to simplify graphing for later, where each CSV file can be used to plot an individual line

public class DataGenerator {

    // IMPORTANT: to get this code running, CHANGE THE FILE PATHS in lines 36 and 37
    // these are absolute file paths, and they will vary for each machine

    // WARNING: there is a lot of recycled code ahead

    public static void main() throws IOException {

        // makes the energy tracker object
        SyncEnergyMonitor monitor = new SyncEnergyMonitor();
        monitor.activate();

        // |
        // v

        // stores the path for the input & output CSVs
        // CHANGE THESE TO GET THE CODE RUNNING!!!
        String inputPath = "D:/ucd/comp20290/algo_project/SortingAlgorithms/src/algo_project/CSVs";
        String outputPath = "D:/ucd/comp20290/algo_project/SortingAlgorithms/src/algo_project/Output";

        // ^
        // |

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
            File output = new File(outputPath + "/BubbleSort/Reverse/" + i + ".csv");
            FileWriter writer = new FileWriter(output);

            // cycles through the sizes
            // writes the input size & energy consumed as coordinates into the CSV
            for(int j = 0; j < 8; j++) {
                // makes a copy of the desired size
                int[] array = Arrays.copyOf(reverseSorted, initSize);
                EnergyStats before = monitor.getSample();
                SortingAlgorithms.bubble_sort(array);
                EnergyStats after = monitor.getSample();

                // gets the difference between the two samples
                EnergyDiff diff = EnergyDiff.between(before, after);

                // gets the CPU usage
                double energy = diff.getCore();
                // stores the size & CPU usage as
                writer.write(initSize + "," + energy + "\n");

                // increases the size
                if(j < 3) {initSize += 25000;}
                else {initSize += 100000;}
            }
        }

        // sorted file

        int[] sorted = readCSV(inputPath + "/sorted.csv", 500000);

        for(int i = 0; i < 400; i++) {
            // 25k, 50k, 75k, 100k, 200k, 300k, 400k, 500k

            // assigns the initial size
            int initSize = 25000;

            // sets up the file & output for the iteration
            File output = new File(outputPath + "/BubbleSort/Sorted/" + i + ".csv");
            FileWriter writer = new FileWriter(output);

            // cycles through the sizes
            // writes the input size & energy consumed as coordinates into the CSV
            for(int j = 0; j < 8; j++) {
                // makes a copy of the desired size
                int[] array = Arrays.copyOf(sorted, initSize);
                EnergyStats before = monitor.getSample();
                SortingAlgorithms.bubble_sort(array);
                EnergyStats after = monitor.getSample();

                // gets the difference between the two samples
                EnergyDiff diff = EnergyDiff.between(before, after);

                // gets the CPU usage
                double energy = diff.getCore();
                // stores the size & CPU usage as a CSV entry
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
                File output = new File(outputPath + "/BubbleSort/Random/" + (x+1) + "/" + i + ".csv");
                FileWriter writer = new FileWriter(output);

                for(int j = 0; j < 8; j++) {
                    // makes a copy of the desired size
                    int[] array1 = Arrays.copyOf(array, initSize);
                    EnergyStats before = monitor.getSample();
                    SortingAlgorithms.bubble_sort(array1);
                    EnergyStats after = monitor.getSample();

                    EnergyDiff diff = EnergyDiff.between(before, after);
                    double energy = diff.getCore();
                    // stores the size & CPU usage as a CSV entry
                    writer.write(initSize + "," + energy + "\n");

                    // increases the size
                    if(j < 3) {initSize += 25000;}
                    else {initSize += 100000;}
                }
            }
        }





        // MERGE SORT
        // Alternating elements, 1x30
        // Sorted, 1x30
        // Randomly sorted, 10x3

        // Alternating elements

        int[] alternating =  readCSV(inputPath + "/alternating.csv", 1000000);

        for(int i = 0; i < 30; i++) {

            int initSize = 25000;
            File output = new File(outputPath + "/MergeSort/Alternating/" + i + ".csv");
            FileWriter writer = new FileWriter(output);

            // cycles thru the sizes, up to 1m
            for(int j = 0; j < 13; j++) {
                // 25k, 50k, 75k|, 100k, 200k, 300k, 400k, 500k|, 600k, 700k, 800k, 900k, 1m

                // makes a copy of the desired size
                int[] array1 = Arrays.copyOf(alternating, initSize);
                EnergyStats before = monitor.getSample();
                SortingAlgorithms.merge_sort(array1, 0, array1.length);
                EnergyStats after = monitor.getSample();

                EnergyDiff diff = EnergyDiff.between(before, after);
                double energy = diff.getCore();
                // stores the size & CPU usage as a CSV entry
                writer.write(initSize + "," + energy + "\n");

                // increases the size
                if(j < 3) {initSize += 25000;}
                else {initSize += 100000;}
            }

        }

        // Sorted
        int[] sortedArray =  readCSV(inputPath + "/sorted.csv", 1000000);
        for(int i = 0; i < 30; i++) {

            int initSize = 25000;
            File output = new File(outputPath + "/MergeSort/Sorted/" + i + ".csv");
            FileWriter writer = new FileWriter(output);

            // cycles thru the sizes
            for(int j = 0; j < 13; j++) {

                // makes a copy of the desired size
                int[] array1 = Arrays.copyOf(sortedArray, initSize);
                EnergyStats before = monitor.getSample();
                SortingAlgorithms.merge_sort(array1, 0, array1.length);
                EnergyStats after = monitor.getSample();

                EnergyDiff diff = EnergyDiff.between(before, after);
                double energy = diff.getCore();
                // stores the size & CPU usage as a CSV entry
                writer.write(initSize + "," + energy + "\n");

                if(j < 3) {initSize += 25000;}
                else {initSize += 100000;}
            }

        }

        // Randomly sorted
        for(int x = 0; x < 10; x++) {
            int[] random = readCSV(inputPath + "/random_" + (x+1) + ".csv", 1000000);
            for(int i = 0; i < 40; i++) {
                int initSize = 25000;
                File output = new File(outputPath + "/MergeSort/Random/" + (x+1) + "/" + i + ".csv");
                FileWriter writer = new FileWriter(output);

                // cycles thru the sizes
                for(int j = 0; j < 13; j++) {

                    // makes a copy of the desired size
                    int[] array1 = Arrays.copyOf(random, initSize);
                    EnergyStats before = monitor.getSample();
                    SortingAlgorithms.merge_sort(array1, 0, array1.length);
                    EnergyStats after = monitor.getSample();

                    EnergyDiff diff = EnergyDiff.between(before, after);
                    double energy = diff.getCore();
                    // stores the size & CPU usage as a CSV entry
                    writer.write(initSize + "," + energy + "\n");

                    if(j < 3) {initSize += 25000;}
                    else {initSize += 100000;}
                }
            }
        }






        // QUICK SORT
        // Reverse-sorted, 1x30
        // Evenly partitioned, 1x30
        // Randomly sorted, 10x3

        // Reverse-sorted

        int[] reversed = readCSV(inputPath + "/sorted_reverse.csv", 1000000);

        for(int i = 0; i < 30; i++) {
            int  initSize = 25000;
            File output = new File(outputPath + "/QuickSort/Reverse/" + i + ".csv");
            FileWriter writer = new FileWriter(output);

            // cycles thru the sizes, only up to 200k
            for(int j = 0; j < 5; j++) {
                // 25k, 50k, 75k, 100k, 200k

                // makes a copy of the desired size
                int[] array1 = Arrays.copyOf(reversed, initSize);
                EnergyStats before = monitor.getSample();
                SortingAlgorithms.quick_sort(array1, 0, array1.length);
                EnergyStats after = monitor.getSample();

                EnergyDiff diff = EnergyDiff.between(before, after);
                double energy = diff.getCore();
                // stores the size & CPU usage as a CSV entry
                writer.write(initSize + "," + energy + "\n");

                if(j < 3) {initSize += 25000;}
                else {initSize += 100000;}
            }
        }

        // Evenly partitioned
        int[] evenlyPartitioned = readCSV(inputPath + "/even_partitioned.csv", 1000000);

        for(int i = 0; i < 30; i++) {
            int  initSize = 25000;
            File output = new File(outputPath + "/QuickSort/EvenPartition/" + i + ".csv");
            FileWriter writer = new FileWriter(output);

            // cycles thru the sizes
            for(int j = 0; j < 13; j++) {
                // makes a copy of the desired size
                int[] array1 = Arrays.copyOf(evenlyPartitioned, initSize);
                EnergyStats before = monitor.getSample();
                SortingAlgorithms.quick_sort(array1, 0, array1.length);
                EnergyStats after = monitor.getSample();

                EnergyDiff diff = EnergyDiff.between(before, after);
                double energy = diff.getCore();
                // stores the size & CPU usage as a CSV entry
                writer.write(initSize + "," + energy + "\n");

                if(j < 3) {initSize += 25000;}
                else {initSize += 100000;}
            }
        }

        // Randomly sorted
        for(int x = 0; x < 10; x++) {

            int[] random =  readCSV(inputPath + "/random_" + (x+1) + ".csv", 1000000);

            for(int i = 0; i < 3; i++) {
                int  initSize = 25000;
                File output = new File(outputPath + "/QuickSort/Random/" + (x+1) + "/" + i + ".csv");
                FileWriter writer = new FileWriter(output);

                // cycles thru the sizes
                for(int j = 0; j < 13; j++) {
                    // makes a copy of the desired size
                    int[] array1 = Arrays.copyOf(random, initSize);
                    EnergyStats before = monitor.getSample();
                    SortingAlgorithms.quick_sort(array1, 0, array1.length);
                    EnergyStats after = monitor.getSample();

                    EnergyDiff diff = EnergyDiff.between(before, after);
                    double energy = diff.getCore();
                    // stores the size & CPU usage as a CSV entry
                    writer.write(initSize + "," + energy + "\n");

                    if(j < 3) {initSize += 25000;}
                    else {initSize += 100000;}
                }
            }

        }




        // COUNTING SORT
        // Randomly sorted, big k - 1x30
        // Randomly sorted, small k - 1x30

        // Big K
        int[] bigK = readCSV(inputPath + "/random_big_k.csv", 1000000);
        for(int i = 0; i < 30; i++) {
            int  initSize = 25000;
            File output = new File(outputPath + "/CountingSort/Random_BigK/" + i + ".csv");
            FileWriter writer = new FileWriter(output);

            // cycles thru the sizes
            for(int j = 0; j < 13; j++) {
                // makes a copy of the desired size
                int[] array1 = Arrays.copyOf(bigK, initSize);
                EnergyStats before = monitor.getSample();
                int[] countingArray = SortingAlgorithms.counting_sort(array1);
                EnergyStats after = monitor.getSample();

                EnergyDiff diff = EnergyDiff.between(before, after);
                double energy = diff.getCore();
                // stores the size & CPU usage as a CSV entry
                writer.write(initSize + "," + energy + "\n");

                if(j < 3) {initSize += 25000;}
                else {initSize += 100000;}
            }
        }

        // Small K
        int[] smallK =  readCSV(inputPath + "/random_small_k.csv", 1000000);
        for(int i = 0; i < 30; i++) {
            int  initSize = 25000;
            File output = new File(outputPath + "/CountingSort/Random_SmallK/" + i + ".csv");
            FileWriter writer = new FileWriter(output);

            // cycles thru the sizes
            for(int j = 0; j < 13; j++) {
                // makes a copy of the desired size
                int[] array1 = Arrays.copyOf(smallK, initSize);
                EnergyStats before = monitor.getSample();
                int[] countingArray = SortingAlgorithms.counting_sort(array1);
                EnergyStats after = monitor.getSample();

                EnergyDiff diff = EnergyDiff.between(before, after);
                double energy = diff.getCore();
                // stores the size & CPU usage as a CSV entry
                writer.write(initSize + "," + energy + "\n");

                if(j < 3) {initSize += 25000;}
                else {initSize += 100000;}
            }
        }



    }


    // a method for reading CSV files into an array
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
