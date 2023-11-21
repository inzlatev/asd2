package ru.skillsmart.asd2.task2_BST;

import java.io.*;
import java.util.*;


class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }

    @Override
    public String toString() {
        return "BSTNode[" +
                "NodeKey=" + NodeKey +
                ", LeftChild=" + LeftChild +
                ", RightChild=" + RightChild +
                ']';
    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node) {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        // ищем в дереве узел и сопутствующую информацию по ключу
        if (Root == null) {
            return null;
        }

        return searchResultRecursive(Root, key);
    }

    public boolean AddKeyValue(int key, T val) {
        // добавляем ключ-значение в дерево
        if (Root == null) {
            Root = new BSTNode<>(key, val, null);
            return true;
        }
        BSTFind<T> searchResult = searchResultRecursive(Root, key);
        if (searchResult.NodeHasKey)
            return false;  // если ключ уже есть

        BSTNode<T> newNode = new BSTNode<>(key, val, searchResult.Node);

        if (searchResult.ToLeft) {
            searchResult.Node.LeftChild = newNode;
            return true;
        }
        searchResult.Node.RightChild = newNode;
        return true;
    }

    private BSTFind<T> searchResultRecursive(BSTNode<T> currentNode, int key) {
        if (key == currentNode.NodeKey) {
            BSTFind<T> result = new BSTFind<>();
            result.Node = currentNode;
            result.NodeHasKey = true;
            return result;
        }
        if (key < currentNode.NodeKey && currentNode.LeftChild == null) {
            BSTFind<T> result = new BSTFind<>();
            result.Node = currentNode;
            result.ToLeft = true;
            return result;
        }
        if (key > currentNode.NodeKey && currentNode.RightChild == null) {
            BSTFind<T> result = new BSTFind<>();
            result.Node = currentNode;
            result.ToLeft = false;
            return result;
        }
        if (key < currentNode.NodeKey) {
            return searchResultRecursive(currentNode.LeftChild, key);
        }
        return searchResultRecursive(currentNode.RightChild, key);
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        // ищем максимальный/минимальный ключ в поддереве
        if (FromNode == null) {
            throw new IllegalArgumentException("Wrong value for initial node");
        }
        if ((FindMax && FromNode.RightChild == null) || (!FindMax && FromNode.LeftChild == null)) {
            return FromNode;
        }
        if (FindMax) {
            return FinMinMax(FromNode.RightChild, FindMax);
        }
        return FinMinMax(FromNode.LeftChild, FindMax);
    }

    public boolean DeleteNodeByKey(int key) {
        // удаляем узел по ключу
        BSTFind<T> searchResult = FindNodeByKey(key);
        if (!searchResult.NodeHasKey)
            return false; // если узел не найден

        BSTNode<T> initialNode = searchResult.Node;
        BSTNode<T> replacementNode = findReplacementNode(initialNode);

        //the node for replacement doesn't have any nodes to the right:
        if (replacementNode == initialNode && initialNode.equals(Root)) {
            throw new IllegalArgumentException("Can't handle the case when deleting root node with no right child");
        }
        if (replacementNode == initialNode && replacementNode.LeftChild != null) {

            replacementNode.Parent.RightChild = replacementNode.Parent.RightChild == replacementNode ?
                    replacementNode.LeftChild : replacementNode.Parent.RightChild;

            replacementNode.Parent.LeftChild = replacementNode.Parent.LeftChild == replacementNode ?
                    replacementNode.LeftChild : replacementNode.Parent.LeftChild;

            replacementNode.LeftChild.Parent = replacementNode.Parent;
            CleanUpNode(replacementNode);

            return true;
        } else if (replacementNode == initialNode) {
            replacementNode.Parent.RightChild = null;
            replacementNode.Parent = null;
            return true;
        }

        if (replacementNode.RightChild != null) {
            replacementNode.Parent.LeftChild = replacementNode.RightChild;
            replacementNode.RightChild.Parent = replacementNode.Parent;
        }

        replacementNode.Parent = initialNode.Parent;
        replacementNode.LeftChild = initialNode.LeftChild;
        if (initialNode.LeftChild != null) {
            initialNode.LeftChild.Parent = replacementNode;
        }
        if (initialNode.RightChild != null && initialNode.RightChild != replacementNode) {
            initialNode.RightChild.Parent = replacementNode;
        }

        if (initialNode.RightChild != replacementNode) {
            replacementNode.RightChild = initialNode.RightChild;
        }

        if (initialNode == Root) {
            Root = replacementNode;
        } else if (initialNode.Parent.LeftChild == initialNode) {
            initialNode.Parent.LeftChild = replacementNode;
        }

        CleanUpNode(initialNode);

        return true;
    }

    private BSTNode<T> findReplacementNode(BSTNode<T> initialNode) {
        if (initialNode.RightChild == null)
            return initialNode;

        BSTNode<T> tempNode = initialNode.RightChild;
        while (tempNode.LeftChild != null) {
            tempNode = tempNode.LeftChild;
        }
        return tempNode;
    }

    private void CleanUpNode(BSTNode<T> node) {
        node.LeftChild = null;
        node.RightChild = null;
        node.Parent = null;
    }


    public int Count() {
        return getCountRecursive(Root); // количество узлов в дереве
    }

    private int getCountRecursive(BSTNode<T> currentNode) {

        if (currentNode == null) {
            return 0;
        }

        int leftCount = getCountRecursive(currentNode.LeftChild);
        int rightCount = getCountRecursive(currentNode.RightChild);

        return leftCount + rightCount + 1;

    }

    @Override
    public String toString() {
        return "BST{" +
                "Root=" + Root +
                '}';
    }
}