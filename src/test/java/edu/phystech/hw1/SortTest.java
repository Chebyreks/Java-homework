package edu.phystech.hw1;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SortTest {

    private static int[] sort(int[] numbs) {
        int[] res = Arrays.copyOf(numbs, numbs.length);
        int temp = 0;
        for (int i = 0; i < numbs.length - 1; i++) {
            for (int a = 0; a < numbs.length - i - 1; a++) {
                if (res[a] > res[a + 1]) {
                    temp = res[a + 1];
                    res[a + 1] = res[a];
                    res[a] = temp;
                }
            }
        }
        return res;
    }

    @Test
    public void sortWorks() {
        Assertions.assertArrayEquals(new int[]{1}, sort(new int[]{1}));
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void sortReturnsNewArray() {
        int[] input = {9, 1, 3, 11, 45, 499};
        int[] copy = Arrays.copyOf(input, input.length);

        int[] sorted = sort(input);

        Assertions.assertArrayEquals(new int[]{1, 3, 9, 11, 45, 499}, sorted);
        Assertions.assertArrayEquals(copy, input);
    }
}
