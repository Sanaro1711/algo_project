package algo_project;

public class SortingAlgorithms {

    public static void main(String[] args) {
        SortingAlgorithms sa = new SortingAlgorithms();

        int[] original = {5, 2, 9, 1, 5, 6};

        int[] arr1 = original.clone();
        sa.bubble_sort(arr1);
        System.out.print("Bubble Sort:   ");
        printArray(arr1);

        // ---------- Quick Sort ----------
        int[] arr3 = original.clone();
        sa.quick_sort(arr3, 0, arr3.length - 1);
        System.out.print("Quick Sort:    ");
        printArray(arr3);

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

        // NOTE: BEST CASE OF BUBBLE SORT IS O(n) but report says n^2
        // ---------- Complexity Table ----------
        System.out.println("\nAlgorithm           Best Case     Worst Case");
        System.out.println("------------------------------------------------");
        System.out.println("Bubble Sort         O(n)          O(n^2)");
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

    public void bubble_sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-2; j++) {
                if (array[j] > array[j+1]) { // if current element is greater than next element, swap them
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    public int[] counting_sort(int[] array) {
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

    public void quick_sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        else {
            int index = partition(arr, low, high);
            quick_sort(arr, low, index-1);
            quick_sort(arr, index+1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {
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

    public void merge_sort(int[] arr, int l, int r) {
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

    public void merge(int[] arr, int l, int mid, int r) {

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

}
