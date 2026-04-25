package algo_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// This class contains the code for the sorting algorithms, as well as sample code to test them in the main method.
// The jRAPL commands have been commented out, to ensure compatibility with all machines.
// You can test jRAPL by uncommenting lines 23-24, 27, 59, 62-63, and 66-69.

public class SortingAlgorithms {
    public static final String COMMA_DELIMITER = ",";

    public static void main(String[] args) {
        SortingAlgorithms sa = new SortingAlgorithms();

        int[] original = {5, 2, 9, 1, 5, 6};

        // makes an object that tracks the energy usage
        //SyncEnergyMonitor m = new SyncEnergyMonitor();
        //m.activate();

        // collects a sample of the energy usage before the work is done
        //EnergyStats before = m.getSample();

        int[] arr1 = original.clone();
        sa.bubble_sort(arr1);
        System.out.print("Bubble Sort:   ");
        printArray(arr1);

        // ---------- Quick Sort ----------
        int[] arr3 = original.clone();
        sa.quick_sort(arr3, 0, arr3.length - 1);
        System.out.print("Quick Sort:    ");
        printArray(arr3);

        //test reading csv
        int[] randFiveHundred = sa.readCsv("src/Bubble Sort/Sorted with 500k inputs - Sheet1.csv");
        sa.quick_sort(randFiveHundred, 0, randFiveHundred.length - 1);
        System.out.print("Quick Sort csv:    ");
        printArray(randFiveHundred);

        // ---------- Merge Sort ----------
        int[] arr4 = original.clone();
        sa.merge_sort(arr4, 0, arr4.length - 1);
        System.out.print("Merge Sort:    ");
        printArray(arr4);

        // ---------- Counting Sort ----------
        int[] arr2 = original.clone();
        int[] sortedCounting = sa.counting_sort(arr2);
        System.out.print("Counting Sort: ");
        printArray(sortedCounting);

        // grabs a sample after the work is done
        //EnergyStats after = m.getSample();

        // calculates the difference between the two, and stops the tracker
        //EnergyDiff difference = EnergyDiff.between(before, after);
        //m.deactivate();

        // prints out the jRAPL readings
        // System.out.println(String.format(
        //  "Used %.2f J for CPU, %.2f J for DRAM, %.2f J in total over %d milliseconds",
        //  difference.getCore(), difference.getDram(), difference.getPackage(), difference.getTimeElapsed().getNano() / 1000000
        // ));

        // NOTE: BEST CASE OF BUBBLE SORT IS O(n) but report says n^2
        // ---------- Complexity Table ----------
        System.out.println("\nAlgorithm           Best Case     Worst Case");
        System.out.println("------------------------------------------------");
        System.out.println("Bubble Sort         O(n^2)          O(n^2)");
        System.out.println("Quick Sort          O(n log n)    O(n^2)");
        System.out.println("Merge Sort          O(n log n)    O(n log n)");
        System.out.println("Counting Sort       O(n+k)        O(n+k)"); // n = size, k = max value
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void bubble_sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (array[j] > array[j+1]) { // if current element is greater than next element, swap them
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    public static int[] counting_sort(int[] array) {
        int n = array.length;
        if (n == 0) {
            return new int[0];
        }
        // find max value
        int max_val = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > max_val) {
                max_val = array[i];
            }
        }

        // make cntArr and initialise all vals to 0
        int[] cntArr = new int[max_val+1];
        for (int i = 0; i < max_val+1; i++) {
            cntArr[i] = 0;
        }

        // store count of each unique elemnet
        for (int i = 0; i < n; i++) {
            cntArr[array[i]]++;
        }

        // compute prefix sum
        for (int i = 1; i < max_val+1; i++) {
            cntArr[i] = cntArr[i] + cntArr[i-1];
        }

        int[] ans = new int[n];
        for (int i = n-1; i >= 0; i--) {
            int e = array[i];
            ans[cntArr[e] - 1] = e;
            cntArr[e]--;
        }

        return ans;
    }

    public static void quick_sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        else {
            int index = partition(arr, low, high);
            quick_sort(arr, low, index-1);
            quick_sort(arr, index+1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                // swap i and j
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap pivot with arr[i+1]
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void merge_sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        else {
            int mid = l + (r - l)/2;

            // sorting both halves
            merge_sort(arr, l, mid);
            merge_sort(arr, mid+1, r);

            // merge the sorted halves
            merge(arr, l, mid, r);
        }
    }

    public static void merge(int[] arr, int l, int mid, int r) {

        int n1 = mid-l+1; // mid element is included in left array
        int n2 = r-mid;
        int[] left = new int[n1];
        int[] right = new int[n2];

        // copy respective elements into left and right
        for (int i = 0; i < n1; i++) {
            left[i] = arr[l+i];
        }

        for (int i = 0; i < n2; i++) {
            right[i] = arr[mid+i+1];
        }

        // merge left and right arrays
        int i, j, k;
        i = j = 0;
        k = l;

        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // if any elements left over in either array, append them on
        while (i < n1) {
            arr[k++] = left[i++];
        }

        while (j < n2) {
            arr[k++] = right[j++];
        }

        // now both merged arrays are insdie of arr
    }

    //for the csv file
    public int[] readCsv(String csv){
        List<Integer> column = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(csv))){
            String row;
            while ((row = br.readLine()) != null){
                String[] values = row.split(COMMA_DELIMITER);
                if (values.length > 0){
                    column.add(Integer.parseInt(values[0]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] result = new int[column.size()];
        for (int i = 0; i < column.size(); i++){
            result[i] = column.get(i);
        }
        return result;
    }

    // functions used to get time usage

    public long timeBubbleSort(String csvPath) {
        int[] arr = readCsv(csvPath);
        long start = System.nanoTime();
        bubble_sort(arr);
        long end = System.nanoTime();
        return end - start;
    }

    public long timeCountingSort(String csvPath) {
        int[] arr = readCsv(csvPath);
        long start = System.nanoTime();
        counting_sort(arr);
        long end = System.nanoTime();
        return end - start;
    }

    public long timeQuickSort(String csvPath) {
        int[] arr = readCsv(csvPath);
        long start = System.nanoTime();
        quick_sort(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        return end - start;
    }

    public long timeMergeSort(String csvPath) {
        int[] arr = readCsv(csvPath);
        long start = System.nanoTime();
        merge_sort(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        return end - start;
    }
    public double timeBubbleSortSlice(int[] fullArray, int size) {
        int[] arr = Arrays.copyOfRange(fullArray, 0, size);
        long start = System.nanoTime();
        bubble_sort(arr);
        return (System.nanoTime() - start) / 1_000_000.0;
    }

    public double timeMergeSortSlice(int[] fullArray, int size) {
        int[] arr = Arrays.copyOfRange(fullArray, 0, size);
        long start = System.nanoTime();
        merge_sort(arr, 0, arr.length - 1);
        return (System.nanoTime() - start) / 1_000_000.0;
    }

    public double timeQuickSortSlice(int[] fullArray, int size) {
        int[] arr = Arrays.copyOfRange(fullArray, 0, size);
        long start = System.nanoTime();
        quick_sort(arr, 0, arr.length - 1);
        return (System.nanoTime() - start) / 1_000_000.0;
    }

    public double timeCountingSortSlice(int[] fullArray, int size) {
        int[] arr = Arrays.copyOfRange(fullArray, 0, size);
        int max = Arrays.stream(arr).max().getAsInt();
        if (max > 100_000_000) {
            System.out.println("  Counting Sort skipped (max value " + max + " too large)");
            return -1.0;
        }
        long start = System.nanoTime();
        counting_sort(arr);
        return (System.nanoTime() - start) / 1_000_000.0;
    }
}
