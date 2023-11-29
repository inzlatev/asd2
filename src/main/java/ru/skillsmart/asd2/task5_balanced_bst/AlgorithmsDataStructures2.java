package ru.skillsmart.asd2.task5_balanced_bst;

import java.util.Arrays;

public class AlgorithmsDataStructures2 {
    public static int[] GenerateBBSTArray(int[] a) {
        if (a.length == 0)
            return new int[0];
        boolean isArraySizeCorrect = CheckArraySize(a.length);
        if (!isArraySizeCorrect) {
            throw new IllegalArgumentException("Incorrect input array size");
        }
        Arrays.sort(a);
        int[] resultingArray = new int[a.length];
        FillArray(a, 0, 0, a.length - 1, resultingArray);

        return resultingArray;
    }

    private static void FillArray(int[] initialArray, int resultingArrayIndex, int start, int end, int[] resultingArray) {
        int middle = (start + end) / 2;
        resultingArray[resultingArrayIndex] = initialArray[middle];
        if (middle == start && middle == end)
            return;

        FillArray(initialArray, resultingArrayIndex * 2 + 1, start, middle - 1, resultingArray);
        FillArray(initialArray, resultingArrayIndex * 2 + 2, middle + 1, end, resultingArray);
    }

    private static boolean CheckArraySize(int arraySize) {
        int sum = 0;
        int depth = 0;
        while (sum < arraySize) {
            sum += (int) Math.pow(2, depth);
            depth++;
        }
        return sum == arraySize;
    }
}