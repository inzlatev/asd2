package ru.skillsmart.asd2.task5_balanced_bst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.skillsmart.asd2.task5_balanced_bst.AlgorithmsDataStructures2.GenerateBBSTArray;

public class BBstTest {

    @Test
    public void generateBBSTTest() {
        int[] array3 = {2, 1, 3};
        int[] array7 = {4, 2, 6, 1, 3, 5, 7};
        int[] array15 = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        int[] array31 = {16, 8, 24, 4, 12, 20, 28, 2, 6, 10, 14, 18, 22, 26, 30, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31};

        int[] arr = new int[0];
        assertEquals(0, GenerateBBSTArray(arr).length);

        arr = fillArrayWithValues(3);
        int[] result = GenerateBBSTArray(arr);
        for (int i = 0; i < result.length; i++) {
            assertEquals(array3[i], result[i]);
        }

        arr = fillArrayWithValues(7);
        result = GenerateBBSTArray(arr);
        for (int i = 0; i < result.length; i++) {
            assertEquals(array7[i], result[i]);
        }

        arr = fillArrayWithValues(15);
        result = GenerateBBSTArray(arr);
        for (int i = 0; i < result.length; i++) {
            assertEquals(array15[i], result[i]);
        }

        arr = fillArrayWithValues(31);
        result = GenerateBBSTArray(arr);
        for (int i = 0; i < result.length; i++) {
            assertEquals(array31[i], result[i]);
        }

        arr = fillArrayWithValues(4);
        int[] finalArr = arr;
        assertThrows(IllegalArgumentException.class, () -> GenerateBBSTArray(finalArr));
    }

    private int[] fillArrayWithValues(int arraySize) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = arraySize - i;
        }
        return array;
    }
}
