package ru.skillsmart.asd2.task7_heap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HeapTest {

    @Test
    public void makeHeapTest() {
        Heap heap = new Heap();
        int[] inputArray = fillArrayWithValues(15);
        heap.MakeHeap(inputArray, 3);
        assertEquals(15, Arrays.stream(heap.HeapArray).max().getAsInt());
        assertFalse(heap.Add(25));
    }

    @Test
    public void getMaxTest() {
        Heap heap = new Heap();
        assertEquals(-1, heap.GetMax());

        int[] inputArray = fillArrayWithValues(15);
        heap.MakeHeap(inputArray, 3);
        for (int i = 0; i < 16; i++) {
            assertEquals(Arrays.stream(heap.HeapArray).max().getAsInt(), heap.GetMax());
        }
    }

    private int[] fillArrayWithValues(int arraySize) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = i + 1;
        }
        return array;
    }

}
