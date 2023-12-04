package ru.skillsmart.asd2.task7_heap;

import java.util.*;

class Heap {
    public int[] HeapArray; // хранит неотрицательные числа-ключи

    public Heap() {
        HeapArray = null;
    }

    public void MakeHeap(int[] a, int depth) {
        // создаём массив кучи HeapArray из заданного
        // размер массива выбираем на основе глубины depth
        int heapSize = (int) (Math.pow(2, depth + 1) - 1);
        HeapArray = new int[heapSize];
        Arrays.fill(HeapArray, -1);
        boolean addResult;
        for (int i : a) {
            addResult = Add(i);
            if (!addResult)
                break;
        }
    }

    public int GetMax() {
        // вернуть значение корня и перестроить кучу
        if (HeapArray == null)
            return -1;
        int lastAllocatedIndex =
                getFirstEmptyIndex() == -1 ? HeapArray.length - 1 : getFirstEmptyIndex() - 1;
        if (lastAllocatedIndex < 0)
            return -1; // если куча пуста

        int result = HeapArray[0];
        HeapArray[0] = HeapArray[lastAllocatedIndex];
        HeapArray[lastAllocatedIndex] = -1;

        int nodeIndex = 0;
        while (true) {
            int leftChildIndex = 2 * nodeIndex + 1;
            int rightChildIndex = 2 * nodeIndex + 2;
            if (rightChildIndex > HeapArray.length - 1)
                break;
            int indexOfMaxChildValue =
                    HeapArray[leftChildIndex] > HeapArray[rightChildIndex] ? leftChildIndex : rightChildIndex;
            if (HeapArray[indexOfMaxChildValue] <= HeapArray[nodeIndex])
                break;

            int temp = HeapArray[indexOfMaxChildValue];
            HeapArray[indexOfMaxChildValue] = HeapArray[nodeIndex];
            HeapArray[nodeIndex] = temp;
            nodeIndex = indexOfMaxChildValue;
        }
        return result;
    }

    public boolean Add(int key) {
        // добавляем новый элемент key в кучу и перестраиваем её
        if (HeapArray == null)
            return false;
        int initialIndex = getFirstEmptyIndex();
        if (initialIndex == -1)
            return false;
        HeapArray[initialIndex] = key;
        allocateNewEntry(key, initialIndex);
        return true;
    }

    private void allocateNewEntry(int key, int index) {
        int parentIndex = (index - 1) / 2;
        while (HeapArray[parentIndex] < key) {
            HeapArray[index] = HeapArray[parentIndex];
            HeapArray[parentIndex] = key;
            if (parentIndex == index)
                break;
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private int getFirstEmptyIndex() {
        for (int i = 0; i < HeapArray.length; i++) {
            if (HeapArray[i] == -1)
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Heap{" +
                "HeapArray=" + Arrays.toString(HeapArray) +
                '}';
    }
}
