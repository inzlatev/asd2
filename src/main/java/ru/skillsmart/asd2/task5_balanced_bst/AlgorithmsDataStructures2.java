package ru.skillsmart.asd2.task5_balanced_bst;

import java.util.Arrays;

public class AlgorithmsDataStructures2 {
    public static int[] GenerateBBSTArray(int[] a) {
        if (a.length == 0)
            return a;
        boolean isArraySizeCorrect = CheckArraySize(a.length);
        if (!isArraySizeCorrect) {
            throw new IllegalArgumentException("Incorrect input array size");
        }
        Arrays.sort(a);
        int[] resultingArray = new int[a.length];
        FillArray(a, 0, resultingArray);

        return resultingArray;
    }

    private static void FillArray(int[] currentArray, int resultingArrayIndex, int[] resultingArray) {
        int currentArrayIndex = currentArray.length / 2;
        resultingArray[resultingArrayIndex] = currentArray[currentArrayIndex];
        if (currentArrayIndex == 0)
            return;

        int[] leftArrayPart = Arrays.stream(currentArray)
                .filter(a -> a < currentArray[currentArrayIndex]).toArray();
        int[] rightArrayPart = Arrays.stream(currentArray)
                .filter(a -> a > currentArray[currentArrayIndex]).toArray();
        FillArray(leftArrayPart, resultingArrayIndex * 2 + 1, resultingArray);
        FillArray(rightArrayPart, resultingArrayIndex * 2 + 2, resultingArray);
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