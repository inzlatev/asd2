package ru.skillsmart.asd2.task6_balanced_bst2;

import java.util.*;

class BSTNode {
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок
    public int Level; // глубина узла

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }

    @Override
    public String toString() {
        return
                "\"NodeKey\" : " + NodeKey +
                        ", \"Level\" : " + Level +
                        ", \"LeftChild\" : {" + LeftChild + "}" +
                        ", \"RightChild\" : {" + RightChild + "}";
    }
}

class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        // создаём дерево с нуля из неотсортированного массива a
        Arrays.sort(a);
        Root = FillTreeWithNodes(null, a, 0, a.length - 1, 0);
    }

    private BSTNode FillTreeWithNodes(BSTNode parentNode, int[] a, int startIndex, int endIndex, int level) {
        if (startIndex > endIndex) {
            return null;
        }
        int midIndex = (startIndex + endIndex) / 2;
        BSTNode currentNode = new BSTNode(a[midIndex], parentNode);
        currentNode.Level = level;

        currentNode.LeftChild = FillTreeWithNodes(currentNode, a, startIndex, midIndex - 1, level + 1);
        currentNode.RightChild = FillTreeWithNodes(currentNode, a, midIndex + 1, endIndex, level + 1);
        return currentNode;
    }

    public boolean IsBalanced(BSTNode root_node) {

        if (root_node == null)
            return true;

        int leftSubtreeHeight = GetHeight(root_node.LeftChild);
        int rightSubtreeHeight = GetHeight(root_node.RightChild);

        if ((Math.abs(leftSubtreeHeight - rightSubtreeHeight) < 2) && IsBalanced(root_node.LeftChild) && IsBalanced(root_node.RightChild)) {
            return true;
        }

        return false;
        // сбалансировано ли дерево с корнем root_node
    }

    private int GetHeight(BSTNode node) {
        if (node == null)
            return 0;
        return 1 + Math.max(GetHeight(node.LeftChild), GetHeight(node.RightChild));
    }


    @Override
    public String toString() {
        return "{" + Root + "}";
    }
}
