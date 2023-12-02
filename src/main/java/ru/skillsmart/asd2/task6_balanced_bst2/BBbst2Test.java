package ru.skillsmart.asd2.task6_balanced_bst2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BBbst2Test {

    @Test
    public void generateTreeTest() {
        int[] arr7 = fillArrayWithValues(7);
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(arr7);
        assertEquals(4, tree.Root.NodeKey);
        assertEquals(0, tree.Root.Level);

        assertEquals(2, tree.Root.LeftChild.NodeKey);
        assertEquals(4, tree.Root.LeftChild.Parent.NodeKey);
        assertEquals(1, tree.Root.LeftChild.Level);

        assertEquals(1, tree.Root.LeftChild.LeftChild.NodeKey);
        assertEquals(2, tree.Root.LeftChild.LeftChild.Parent.NodeKey);
        assertEquals(2, tree.Root.LeftChild.LeftChild.Level);

        assertEquals(3, tree.Root.LeftChild.RightChild.NodeKey);
        assertEquals(2, tree.Root.LeftChild.RightChild.Parent.NodeKey);
        assertEquals(2, tree.Root.LeftChild.RightChild.Level);
    }

    @Test
    public void isBalancedTest() {
        int[] arr15 = fillArrayWithValues(15);
        BalancedBST balancedTree = new BalancedBST();
        balancedTree.GenerateTree(arr15);
        assertTrue(balancedTree.IsBalanced(balancedTree.Root));

        BalancedBST unbalancedTree = generateUnbalancedTree();
        assertFalse(unbalancedTree.IsBalanced(unbalancedTree.Root));
    }


    private int[] fillArrayWithValues(int arraySize) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = arraySize - i;
        }
        return array;
    }

    private BalancedBST generateUnbalancedTree() {
        BalancedBST tree = new BalancedBST();
        BSTNode rootNode = new BSTNode(10, null);
        BSTNode leftNode1 = new BSTNode(9, rootNode);
        rootNode.LeftChild = leftNode1;
        BSTNode leftNode2 = new BSTNode(8, leftNode1);
        leftNode1.LeftChild = leftNode2;
        BSTNode leftNode3 = new BSTNode(7, leftNode2);
        leftNode2.LeftChild = leftNode3;

        BSTNode rightNode1 = new BSTNode(15, rootNode);
        rootNode.RightChild = rightNode1;

        tree.Root = rootNode;
        return tree;
    }

}
