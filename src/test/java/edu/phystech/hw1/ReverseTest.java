package edu.phystech.hw1;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ReverseTest {

    private static int[] reverse(int[] nums) {
        int[] res = new int[nums.length];
        int ind = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = nums[ind];
            ind++;
        }
        return res;
    }


    @Test
    public void reverseWorks() {
        Assertions.assertArrayEquals(new int[]{1}, reverse(new int[]{1}));
        Assertions.assertArrayEquals(new int[]{5, 4, 3, 2, 1}, reverse(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void reverseReturnsNewArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] copy = Arrays.copyOf(input, input.length);

        int[] reversed = reverse(input);

        Assertions.assertArrayEquals(new int[]{5, 4, 3, 2, 1}, reversed);
        Assertions.assertArrayEquals(copy, input);
    }
}
