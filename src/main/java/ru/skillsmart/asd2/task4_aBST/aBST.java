package ru.skillsmart.asd2.task4_aBST;

import java.util.*;

class aBST
{
    public Integer Tree []; // массив ключей

    public aBST(int depth)
    {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = calculateTreeSize(depth);

        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++)
            Tree[i] = null;
    }

    private int calculateTreeSize(int depth) {
        int sum = 0;
        while (depth >= 0) {
            sum = (int) (sum + Math.pow(2, depth));
            depth--;
        }
        return sum;
    }

    public Integer FindKeyIndex(int key)
    {
        // ищем в массиве индекс ключа
        return GetIndex(0, key);
    }

    private Integer GetIndex(Integer currentNodeIndex, int searchKey) {
        if (currentNodeIndex >= Tree.length) {
            return null; // не найден
        }
        if (Tree[currentNodeIndex] == null) {
            return -currentNodeIndex;
        }
        if (Tree[currentNodeIndex] == searchKey) {
            return currentNodeIndex;
        }
        if (Tree[currentNodeIndex] < searchKey) {
            return GetIndex(2*currentNodeIndex + 2, searchKey);
        }
        return GetIndex(2*currentNodeIndex +1, searchKey);
    }

    public int AddKey(int key)
    {
        // добавляем ключ в массив
        Integer nodeInputIndex = FindKeyIndex(key);
        if (nodeInputIndex == null) {
            return -1; //не удалось добавить ключ
        }
        if (nodeInputIndex < 0) {
            Tree[-nodeInputIndex] = key;
            return -nodeInputIndex;
        }
        if (nodeInputIndex > 0) {
            return nodeInputIndex;
        }
        if (Tree[0] == null){
            Tree[0] = key;
        }

        return nodeInputIndex;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }
}