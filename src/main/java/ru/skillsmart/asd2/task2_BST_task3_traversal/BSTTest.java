package ru.skillsmart.asd2.task2_BST_task3_traversal;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {

    @Test
    public void addKeyValueTest() {

        BST<Integer> bst = new BST<>(null);
        assertNull(bst.Root);

        assertTrue(bst.AddKeyValue(25, 1));
        assertEquals(25, bst.Root.NodeKey);
        assertNull(bst.Root.Parent);

        assertTrue(bst.AddKeyValue(5, 1));
        assertEquals(5, bst.Root.LeftChild.NodeKey);

        assertTrue(bst.AddKeyValue(3, 1));
        assertEquals(3, bst.Root.LeftChild.LeftChild.NodeKey);

        assertTrue(bst.AddKeyValue(4, 1));
        assertEquals(4, bst.Root.LeftChild.LeftChild.RightChild.NodeKey);

        assertTrue(bst.AddKeyValue(10, 1));
        assertEquals(10, bst.Root.LeftChild.RightChild.NodeKey);

        assertTrue(bst.AddKeyValue(35, 1));
        assertEquals(35, bst.Root.RightChild.NodeKey);

        assertTrue(bst.AddKeyValue(30, 1));
        assertEquals(30, bst.Root.RightChild.LeftChild.NodeKey);

        assertTrue(bst.AddKeyValue(32, 1));
        assertEquals(32, bst.Root.RightChild.LeftChild.RightChild.NodeKey);

        assertFalse(bst.AddKeyValue(25, 1));
    }

    @Test
    public void findNodeByKeyTest3() {
        BSTNode<Integer> root = new BSTNode<Integer>(8, 0, null);
        BST<Integer> tree = new BST<Integer>(root);
        tree.AddKeyValue(4, 4);
        tree.AddKeyValue(12, 12);
        tree.AddKeyValue(2, 2);
        tree.AddKeyValue(6, 6);
        tree.AddKeyValue(10, 10);
        tree.AddKeyValue(14, 14);
        tree.AddKeyValue(1, 1);
        tree.AddKeyValue(3, 3);
        tree.AddKeyValue(5, 5);
        tree.AddKeyValue(7, 7);
        tree.AddKeyValue(9, 9);
        tree.AddKeyValue(11, 11);
        tree.AddKeyValue(13, 13);
        tree.AddKeyValue(15, 15);
        assertEquals(15, tree.Count());
        tree.DeleteNodeByKey(1);
        assertEquals(14, tree.Count());
        BSTFind<Integer> f = tree.FindNodeByKey(1);
        assertFalse(f.NodeHasKey);
        tree.AddKeyValue(1, 1);
        assertEquals(15, tree.Count());
        tree.DeleteNodeByKey(3);
        assertEquals(14, tree.Count());
        f = tree.FindNodeByKey(3);
        assertFalse(f.NodeHasKey);
    }

    @Test
    public void findNodeByKeyTest2() {
        BST<Integer> bst = new BST<>(null);
        assertNull(bst.FindNodeByKey(0));

        int node1key = 8;
        bst.AddKeyValue(node1key, 1);
        assertEquals(node1key, bst.FindNodeByKey(node1key).Node.NodeKey);

        int node2key = 4;
        bst.AddKeyValue(node2key, 1);
        assertEquals(node2key, bst.FindNodeByKey(node2key).Node.NodeKey);

        int node3key = 2;
        bst.AddKeyValue(node3key, 1);
        assertEquals(node3key, bst.FindNodeByKey(node3key).Node.NodeKey);

        int node4key = 1;
        bst.AddKeyValue(node4key, 1);
        assertEquals(node4key, bst.FindNodeByKey(node4key).Node.NodeKey);

        int node5key = 3;
        bst.AddKeyValue(node5key, 1);
        assertEquals(node5key, bst.FindNodeByKey(node5key).Node.NodeKey);

        int node6key = 6;
        bst.AddKeyValue(node6key, 1);
        assertEquals(node6key, bst.FindNodeByKey(node6key).Node.NodeKey);

        int node7key = 5;
        bst.AddKeyValue(node7key, 1);
        assertEquals(node7key, bst.FindNodeByKey(node7key).Node.NodeKey);

        int node8key = 7;
        bst.AddKeyValue(node8key, 1);
        assertEquals(node8key, bst.FindNodeByKey(node8key).Node.NodeKey);

        int node9key = 12;
        bst.AddKeyValue(node9key, 1);
        assertEquals(node9key, bst.FindNodeByKey(node9key).Node.NodeKey);

        int node10key = 10;
        bst.AddKeyValue(node10key, 1);
        assertEquals(node10key, bst.FindNodeByKey(node10key).Node.NodeKey);

        int node11key = 9;
        bst.AddKeyValue(node11key, 1);
        assertEquals(node11key, bst.FindNodeByKey(node11key).Node.NodeKey);

        int node12key = 11;
        bst.AddKeyValue(node12key, 1);
        assertEquals(node12key, bst.FindNodeByKey(node12key).Node.NodeKey);

        int node13key = 14;
        bst.AddKeyValue(node13key, 1);
        assertEquals(node13key, bst.FindNodeByKey(node13key).Node.NodeKey);

        int node14key = 13;
        bst.AddKeyValue(node14key, 1);
        assertEquals(node14key, bst.FindNodeByKey(node14key).Node.NodeKey);

        int node15key = 15;
        bst.AddKeyValue(node15key, 1);
        assertEquals(node15key, bst.FindNodeByKey(node15key).Node.NodeKey);

        assertEquals(node1key, bst.FindNodeByKey(node1key).Node.NodeKey);
        assertEquals(node2key, bst.FindNodeByKey(node2key).Node.NodeKey);
        assertEquals(node3key, bst.FindNodeByKey(node3key).Node.NodeKey);
        assertEquals(node4key, bst.FindNodeByKey(node4key).Node.NodeKey);
        assertEquals(node5key, bst.FindNodeByKey(node5key).Node.NodeKey);
        assertEquals(node6key, bst.FindNodeByKey(node6key).Node.NodeKey);
        assertEquals(node7key, bst.FindNodeByKey(node7key).Node.NodeKey);
        assertEquals(node8key, bst.FindNodeByKey(node8key).Node.NodeKey);
        assertEquals(node9key, bst.FindNodeByKey(node9key).Node.NodeKey);
        assertEquals(node10key, bst.FindNodeByKey(node10key).Node.NodeKey);
        assertEquals(node11key, bst.FindNodeByKey(node11key).Node.NodeKey);
        assertEquals(node12key, bst.FindNodeByKey(node12key).Node.NodeKey);
        assertEquals(node13key, bst.FindNodeByKey(node13key).Node.NodeKey);
        assertEquals(node14key, bst.FindNodeByKey(node14key).Node.NodeKey);
        assertEquals(node15key, bst.FindNodeByKey(node15key).Node.NodeKey);
    }

    @Test
    public void findNodeByKeyTest() {

        BST<Integer> bst = new BST<>(null);
        assertNull(bst.FindNodeByKey(0));

        int node1key = 25;
        bst.AddKeyValue(node1key, 1);

        assertEquals(node1key, bst.FindNodeByKey(node1key).Node.NodeKey);
        assertTrue(bst.FindNodeByKey(node1key).NodeHasKey);
        assertEquals(bst.Root, bst.FindNodeByKey(node1key).Node);
        assertNull(bst.FindNodeByKey(node1key).Node.LeftChild);
        assertNull(bst.FindNodeByKey(node1key).Node.RightChild);
        assertNull(bst.FindNodeByKey(node1key).Node.Parent);

        int node2key = 5;
        bst.AddKeyValue(node2key, 1);
        assertEquals(node2key, bst.FindNodeByKey(node2key).Node.NodeKey);
        assertEquals(node1key, bst.FindNodeByKey(node2key).Node.Parent.NodeKey);
        assertEquals(node2key, bst.FindNodeByKey(node1key).Node.LeftChild.NodeKey);
        assertNull(bst.FindNodeByKey(node2key).Node.LeftChild);
        assertNull(bst.FindNodeByKey(node2key).Node.RightChild);
        assertTrue(bst.FindNodeByKey(node2key).NodeHasKey);

        int node3key = 3;
        bst.AddKeyValue(node3key, 1);
        assertEquals(node3key, bst.FindNodeByKey(node3key).Node.NodeKey);
        assertEquals(node2key, bst.FindNodeByKey(node3key).Node.Parent.NodeKey);
        assertEquals(node3key, bst.FindNodeByKey(node2key).Node.LeftChild.NodeKey);
        assertNull(bst.FindNodeByKey(node3key).Node.LeftChild);
        assertNull(bst.FindNodeByKey(node3key).Node.RightChild);
        assertTrue(bst.FindNodeByKey(node3key).NodeHasKey);

        int node4key = 4;
        bst.AddKeyValue(node4key, 1);
        assertEquals(node4key, bst.FindNodeByKey(node4key).Node.NodeKey);
        assertEquals(node3key, bst.FindNodeByKey(node4key).Node.Parent.NodeKey);
        assertEquals(node4key, bst.FindNodeByKey(node3key).Node.RightChild.NodeKey);
        assertNull(bst.FindNodeByKey(node4key).Node.LeftChild);
        assertNull(bst.FindNodeByKey(node4key).Node.RightChild);
        assertTrue(bst.FindNodeByKey(node4key).NodeHasKey);

        int node5key = 10;
        bst.AddKeyValue(node5key, 1);
        assertEquals(node5key, bst.FindNodeByKey(node5key).Node.NodeKey);
        assertEquals(node2key, bst.FindNodeByKey(node5key).Node.Parent.NodeKey);
        assertEquals(node5key, bst.FindNodeByKey(node2key).Node.RightChild.NodeKey);
        assertNull(bst.FindNodeByKey(node5key).Node.LeftChild);
        assertNull(bst.FindNodeByKey(node5key).Node.RightChild);
        assertTrue(bst.FindNodeByKey(node5key).NodeHasKey);

        int node6key = 35;
        bst.AddKeyValue(node6key, 1);
        assertEquals(node6key, bst.FindNodeByKey(node6key).Node.NodeKey);
        assertEquals(node1key, bst.FindNodeByKey(node6key).Node.Parent.NodeKey);
        assertEquals(node6key, bst.FindNodeByKey(node1key).Node.RightChild.NodeKey);
        assertNull(bst.FindNodeByKey(node6key).Node.LeftChild);
        assertNull(bst.FindNodeByKey(node6key).Node.RightChild);
        assertTrue(bst.FindNodeByKey(node6key).NodeHasKey);

        int node7key = 30;
        bst.AddKeyValue(node7key, 1);
        assertEquals(node7key, bst.FindNodeByKey(node7key).Node.NodeKey);
        assertEquals(node6key, bst.FindNodeByKey(node7key).Node.Parent.NodeKey);
        assertEquals(node7key, bst.FindNodeByKey(node6key).Node.LeftChild.NodeKey);
        assertNull(bst.FindNodeByKey(node7key).Node.LeftChild);
        assertNull(bst.FindNodeByKey(node7key).Node.RightChild);
        assertTrue(bst.FindNodeByKey(node7key).NodeHasKey);

        int node8key = 32;
        bst.AddKeyValue(node8key, 1);
        assertEquals(node8key, bst.FindNodeByKey(node8key).Node.NodeKey);
        assertEquals(node7key, bst.FindNodeByKey(node8key).Node.Parent.NodeKey);
        assertEquals(node8key, bst.FindNodeByKey(node7key).Node.RightChild.NodeKey);
        assertNull(bst.FindNodeByKey(node8key).Node.LeftChild);
        assertNull(bst.FindNodeByKey(node8key).Node.RightChild);
        assertTrue(bst.FindNodeByKey(node8key).NodeHasKey);


        BSTFind<Integer> searchResult = bst.FindNodeByKey(12);
        assertFalse(searchResult.NodeHasKey);
        assertEquals(node5key, searchResult.Node.NodeKey);
        assertFalse(searchResult.ToLeft);

        searchResult = bst.FindNodeByKey(9);
        assertFalse(searchResult.NodeHasKey);
        assertEquals(node5key, searchResult.Node.NodeKey);
        assertTrue(searchResult.ToLeft);

        System.out.println(bst);
        System.out.println(bst.WideAllNodes());
        System.out.println(bst.DeepAllNodes(1));


    }

    @Test
    public void findMinMaxTest() {
        BSTNode<Integer> bstNodeRoot = new BSTNode<>(25, 1, null);
        BST<Integer> bst = new BST<>(bstNodeRoot);
        bst.AddKeyValue(5, 1);
        bst.AddKeyValue(3, 1);
        bst.AddKeyValue(4, 1);
        bst.AddKeyValue(10, 1);
        bst.AddKeyValue(35, 1);
        bst.AddKeyValue(30, 1);
        bst.AddKeyValue(32, 1);

        assertEquals(3, bst.FinMinMax(bstNodeRoot, false).NodeKey);
        assertEquals(35, bst.FinMinMax(bstNodeRoot, true).NodeKey);

        BSTFind<Integer> searchResult = bst.FindNodeByKey(5);
        assertEquals(3, bst.FinMinMax(searchResult.Node, false).NodeKey);
        assertEquals(10, bst.FinMinMax(searchResult.Node, true).NodeKey);

        searchResult = bst.FindNodeByKey(3);
        assertEquals(3, bst.FinMinMax(searchResult.Node, false).NodeKey);
        assertEquals(4, bst.FinMinMax(searchResult.Node, true).NodeKey);
    }

    @Test
    public void deleteNodeByKeyTest() {
        BSTFind<Integer> searchResult;

        BSTNode<Integer> bstNodeRoot = new BSTNode<>(25, 1, null);
        BST<Integer> bst = new BST<>(bstNodeRoot);
        bst.AddKeyValue(5, 1);
        bst.AddKeyValue(3, 1);
        bst.AddKeyValue(4, 1);
        bst.AddKeyValue(10, 1);
        bst.AddKeyValue(35, 1);
        bst.AddKeyValue(30, 1);
        bst.AddKeyValue(32, 1);
        assertEquals(8, bst.Count());

        assertFalse(bst.DeleteNodeByKey(66));
        assertEquals(8, bst.Count());

        assertTrue(bst.DeleteNodeByKey(25));
        assertEquals(7, bst.Count());
        searchResult = bst.FindNodeByKey(25);
        assertFalse(searchResult.NodeHasKey);
        assertEquals(30, bst.Root.NodeKey);
        searchResult = bst.FindNodeByKey(5);
        assertEquals(searchResult.Node.NodeKey, bst.Root.LeftChild.NodeKey);
        assertEquals(30, searchResult.Node.Parent.NodeKey);
        assertEquals(35, bst.Root.RightChild.NodeKey);
        searchResult = bst.FindNodeByKey(35);
        assertEquals(32, searchResult.Node.LeftChild.NodeKey);

        assertTrue(bst.DeleteNodeByKey(5));
        assertEquals(6, bst.Count());
        searchResult = bst.FindNodeByKey(5);
        assertFalse(searchResult.NodeHasKey);
        assertEquals(10, bst.Root.LeftChild.NodeKey);
        assertEquals(3, bst.Root.LeftChild.LeftChild.NodeKey);

        assertTrue(bst.DeleteNodeByKey(35));
        assertEquals(5, bst.Count());
        searchResult = bst.FindNodeByKey(35);
        assertFalse(searchResult.NodeHasKey);
        assertEquals(32, bst.Root.RightChild.NodeKey);
        assertEquals(30, bst.Root.RightChild.Parent.NodeKey);

        assertTrue(bst.DeleteNodeByKey(32));
        assertEquals(4, bst.Count());
        searchResult = bst.FindNodeByKey(32);
        assertFalse(searchResult.NodeHasKey);
        assertNull(bst.Root.RightChild);

        assertTrue(bst.DeleteNodeByKey(10));
        assertEquals(3, bst.Count());
        assertEquals(3, bst.Root.LeftChild.NodeKey);
        assertEquals(30, bst.Root.LeftChild.Parent.NodeKey);

        assertTrue(bst.DeleteNodeByKey(30));
        assertEquals(2, bst.Count());

        System.out.println(bst);
        assertTrue(bst.DeleteNodeByKey(3));
        assertEquals(1, bst.Count());
        assertEquals(4, bst.Root.NodeKey);

        assertTrue(bst.DeleteNodeByKey(4));
        assertEquals(0, bst.Count());
        assertNull(bst.Root);
        assertFalse(bst.DeleteNodeByKey(1));
    }

    @Test
    public void countTest() {
        BSTNode<Integer> root = new BSTNode<Integer>(8, 0, null);
        BST<Integer> tree = new BST<Integer>(root);
        assertEquals(1, tree.Count());
        tree.AddKeyValue(4, 4);
        tree.AddKeyValue(12, 12);
        tree.AddKeyValue(2, 2);
        tree.AddKeyValue(6, 6);
        tree.AddKeyValue(10, 10);
        tree.AddKeyValue(14, 14);
        tree.AddKeyValue(1, 1);
        tree.AddKeyValue(3, 3);
        tree.AddKeyValue(5, 5);
        tree.AddKeyValue(7, 7);
        tree.AddKeyValue(9, 9);
        tree.AddKeyValue(11, 11);
        tree.AddKeyValue(13, 13);
        tree.AddKeyValue(15, 15);
        assertEquals(15, tree.Count());
        System.out.println(tree.DeepAllNodes(0));
        System.out.println(tree.DeepAllNodes(1));
        System.out.println(tree.DeepAllNodes(2));

        tree.DeleteNodeByKey(1);
        assertEquals(14, tree.Count());
        System.out.println(tree.Count());
        BSTFind<Integer> searchResult = tree.FindNodeByKey(2);
        assertNull(searchResult.Node.LeftChild);

        tree.DeleteNodeByKey(7);
        assertEquals(13, tree.Count());
        System.out.println(tree.Count());
        searchResult = tree.FindNodeByKey(6);
        assertNull(searchResult.Node.RightChild);

        tree.DeleteNodeByKey(8);
        assertEquals(12, tree.Count());
        assertNull(tree.Root.Parent);
        searchResult = tree.FindNodeByKey(9);
        assertSame(tree.Root, searchResult.Node);
        assertEquals(4, searchResult.Node.LeftChild.NodeKey);
        assertEquals(12, searchResult.Node.RightChild.NodeKey);
        searchResult = tree.FindNodeByKey(10);
        assertNull(searchResult.Node.LeftChild);

        tree.DeleteNodeByKey(12);
        assertEquals(11, tree.Count());
        searchResult = tree.FindNodeByKey(13);
        assertEquals(tree.Root.RightChild, searchResult.Node);
        assertEquals(tree.Root, searchResult.Node.Parent);
        assertEquals(14, searchResult.Node.RightChild.NodeKey);
        assertEquals(13, searchResult.Node.RightChild.Parent.NodeKey);
        assertEquals(10, searchResult.Node.LeftChild.NodeKey);
        assertEquals(13, searchResult.Node.LeftChild.Parent.NodeKey);
        assertNull(searchResult.Node.RightChild.LeftChild);

        tree.DeleteNodeByKey(13);
        assertEquals(10, tree.Count());
        assertEquals(14, tree.Root.RightChild.NodeKey);
        assertEquals(tree.Root, tree.Root.RightChild.Parent);
        assertEquals(10, tree.Root.RightChild.LeftChild.NodeKey);
        assertEquals(14, tree.Root.RightChild.LeftChild.Parent.NodeKey);
        assertEquals(15, tree.Root.RightChild.RightChild.NodeKey);
        assertEquals(14, tree.Root.RightChild.RightChild.Parent.NodeKey);

        tree.DeleteNodeByKey(10);
        assertEquals(9, tree.Count());
        assertEquals(11, tree.Root.RightChild.LeftChild.NodeKey);
        assertEquals(14, tree.Root.RightChild.LeftChild.Parent.NodeKey);

        tree.DeleteNodeByKey(14);
        assertEquals(8, tree.Count());
        assertEquals(15, tree.Root.RightChild.NodeKey);
        assertEquals(11, tree.Root.RightChild.LeftChild.NodeKey);

        tree.DeleteNodeByKey(9);
        assertEquals(7, tree.Count());
        assertEquals(11, tree.Root.NodeKey);
        assertEquals(15, tree.Root.RightChild.NodeKey);
        assertEquals(11, tree.Root.RightChild.Parent.NodeKey);

        tree.DeleteNodeByKey(15);
        assertEquals(6, tree.Count());
        assertNull(tree.Root.RightChild);

        tree.DeleteNodeByKey(11);
        assertEquals(5, tree.Count());
        assertNull(tree.Root.Parent);
        assertEquals(4, tree.Root.NodeKey);
        assertEquals(6, tree.Root.RightChild.NodeKey);

        tree.DeleteNodeByKey(2);
        assertEquals(4, tree.Count());
        assertEquals(3, tree.Root.LeftChild.NodeKey);
        assertEquals(4, tree.Root.LeftChild.Parent.NodeKey);

        tree.DeleteNodeByKey(6);
        tree.DeleteNodeByKey(4);

        tree.DeleteNodeByKey(5);
        tree.DeleteNodeByKey(3);
        assertFalse(tree.DeleteNodeByKey(24));
    }

    @Test
    public void traversalTestFullTree() {
        BSTNode<Integer> root = new BSTNode<Integer>(8, 0, null);
        BST<Integer> tree = new BST<Integer>(root);
        tree.AddKeyValue(4, 4);
        tree.AddKeyValue(12, 12);
        tree.AddKeyValue(2, 2);
        tree.AddKeyValue(6, 6);
        tree.AddKeyValue(10, 10);
        tree.AddKeyValue(14, 14);
        tree.AddKeyValue(1, 1);
        tree.AddKeyValue(3, 3);
        tree.AddKeyValue(5, 5);
        tree.AddKeyValue(7, 7);
        tree.AddKeyValue(9, 9);
        tree.AddKeyValue(11, 11);
        tree.AddKeyValue(13, 13);
        tree.AddKeyValue(15, 15);

        ArrayList<Integer> inOrderExpectedResult = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        ArrayList<Integer> postOrderExpectedResult = new ArrayList<>(List.of(1, 3, 2, 5, 7, 6, 4, 9, 11, 10, 13, 15, 14, 12, 8));
        ArrayList<Integer> preOrderExpectedResult = new ArrayList<>(List.of(8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15));
        ArrayList<Integer> wideAllNodesExpectedResult = new ArrayList<>(List.of(8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15));

        ArrayList<BSTNode> deepAllNodesInOrderList = tree.DeepAllNodes(0);
        for (int i = 0; i < deepAllNodesInOrderList.size(); i++) {
            assertEquals(inOrderExpectedResult.get(i), deepAllNodesInOrderList.get(i).NodeKey);
        }

        ArrayList<BSTNode> deepAllNodesPostOrderList = tree.DeepAllNodes(1);
        for (int i = 0; i < deepAllNodesPostOrderList.size(); i++) {
            assertEquals(postOrderExpectedResult.get(i), deepAllNodesPostOrderList.get(i).NodeKey);
        }

        ArrayList<BSTNode> deepAllNodesPreOrderList = tree.DeepAllNodes(2);
        for (int i = 0; i < deepAllNodesPreOrderList.size(); i++) {
            assertEquals(preOrderExpectedResult.get(i), deepAllNodesPreOrderList.get(i).NodeKey);
        }

        ArrayList<BSTNode> wideAllNodesList = tree.WideAllNodes();
        for (int i = 0; i < wideAllNodesList.size(); i++) {
            assertEquals(wideAllNodesExpectedResult.get(i), wideAllNodesList.get(i).NodeKey);
        }
    }

    @Test
    public void traversalTestRandomBinaryTree() {
        BSTNode<Integer> bstNodeRoot = new BSTNode<>(25, 1, null);
        BST<Integer> bst = new BST<>(bstNodeRoot);
        bst.AddKeyValue(5, 1);
        bst.AddKeyValue(3, 1);
        bst.AddKeyValue(4, 1);
        bst.AddKeyValue(10, 1);
        bst.AddKeyValue(35, 1);
        bst.AddKeyValue(30, 1);
        bst.AddKeyValue(32, 1);

        ArrayList<Integer> inOrderExpectedResult = new ArrayList<>(List.of(3, 4, 5, 10, 25, 30, 32, 35));
        ArrayList<Integer> postOrderExpectedResult = new ArrayList<>(List.of(4, 3, 10, 5, 32, 30, 35, 25));
        ArrayList<Integer> preOrderExpectedResult = new ArrayList<>(List.of(25, 5, 3, 4, 10, 35, 30, 32));
        ArrayList<Integer> wideAllNodesExpectedResult = new ArrayList<>(List.of(25, 5, 35, 3, 10, 30, 4, 32));


        ArrayList<BSTNode> deepAllNodesInOrderList = bst.DeepAllNodes(0);
        for (int i = 0; i < deepAllNodesInOrderList.size(); i++) {
            assertEquals(inOrderExpectedResult.get(i), deepAllNodesInOrderList.get(i).NodeKey);
        }

        ArrayList<BSTNode> deepAllNodesPostOrderList = bst.DeepAllNodes(1);
        for (int i = 0; i < deepAllNodesPostOrderList.size(); i++) {
            assertEquals(postOrderExpectedResult.get(i), deepAllNodesPostOrderList.get(i).NodeKey);
        }

        ArrayList<BSTNode> deepAllNodesPreOrderList = bst.DeepAllNodes(2);
        for (int i = 0; i < deepAllNodesPreOrderList.size(); i++) {
            assertEquals(preOrderExpectedResult.get(i), deepAllNodesPreOrderList.get(i).NodeKey);
        }

        ArrayList<BSTNode> wideAllNodesList = bst.WideAllNodes();
        for (int i = 0; i < wideAllNodesList.size(); i++) {
            assertEquals(wideAllNodesExpectedResult.get(i), wideAllNodesList.get(i).NodeKey);
        }
    }

}
