package algo_project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class SortingAlgorithmsTest {
    private SortingAlgorithms sa;
    @BeforeEach
    void setUp() {
        sa = new SortingAlgorithms();
    }

    private void assertArrayEqualsSorted(int[] actual, int[] expected) {
        assertArrayEquals(expected, actual);
    }

    @Test
    void testBubbleSort_basic() {
        int[] arr = {5, 1, 4, 2, 8};
        int[] expected = {1, 2, 4, 5, 8};

        sa.bubble_sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testBubbleSort_sorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        sa.bubble_sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testCountingSort_basic() {
        int[] arr = {4, 2, 2, 8, 3};
        int[] expected = {2, 2, 3, 4, 8};

        int[] result = sa.counting_sort(arr);
        assertArrayEquals(expected, result);
    }

    @Test
    void testCountingSort_single() {
        int[] arr = {7};
        int[] expected = {7};

        int[] result = sa.counting_sort(arr);
        assertArrayEquals(expected, result);
    }

    @Test
    void testCountingSort_duplicates() {
        int[] arr = {1, 1, 1, 1};
        int[] expected = {1, 1, 1, 1};

        int[] result = sa.counting_sort(arr);
        assertArrayEquals(expected, result);
    }
    @Test
    void testQuickSort_basic() {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int[] expected = {1, 5, 7, 8, 9, 10};

        sa.quick_sort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testQuickSort_sorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        sa.quick_sort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testMergeSort_basic() {
        int[] arr = {12, 11, 13, 5, 6, 7};
        int[] expected = {5, 6, 7, 11, 12, 13};

        sa.merge_sort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testMergeSort_twoElements() {
        int[] arr = {2, 1};
        int[] expected = {1, 2};

        sa.merge_sort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testEmptyArray_allAlgorithms() {
        int[] arr = {};
        int[] expected = {};

        sa.bubble_sort(arr);
        assertArrayEquals(expected, arr);

        assertArrayEquals(expected, sa.counting_sort(arr));

        sa.quick_sort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);

        sa.merge_sort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);
    }

}
