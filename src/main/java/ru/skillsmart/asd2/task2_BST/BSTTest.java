package ru.skillsmart.asd2.task2_BST;

import org.junit.jupiter.api.Test;

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

        assertTrue(bst.AddKeyValue(4,1));
        assertEquals(4, bst.Root.LeftChild.LeftChild.RightChild.NodeKey);

        assertTrue(bst.AddKeyValue(10,1));
        assertEquals(10, bst.Root.LeftChild.RightChild.NodeKey);

        assertTrue(bst.AddKeyValue(35,1));
        assertEquals(35, bst.Root.RightChild.NodeKey);

        assertTrue(bst.AddKeyValue(30,1));
        assertEquals(30, bst.Root.RightChild.LeftChild.NodeKey);

        assertTrue(bst.AddKeyValue(32,1));
        assertEquals(32, bst.Root.RightChild.LeftChild.RightChild.NodeKey);

        assertFalse(bst.AddKeyValue(25,1));
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

        assertFalse(bst.DeleteNodeByKey(66));

        assertTrue(bst.DeleteNodeByKey(25));
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
        searchResult = bst.FindNodeByKey(5);
        assertFalse(searchResult.NodeHasKey);
        assertEquals(10, bst.Root.LeftChild.NodeKey);
        assertEquals(3, bst.Root.LeftChild.LeftChild.NodeKey);

        assertTrue(bst.DeleteNodeByKey(35));
        searchResult = bst.FindNodeByKey(35);
        assertFalse(searchResult.NodeHasKey);
        assertEquals(32, bst.Root.RightChild.NodeKey);
        assertEquals(30, bst.Root.RightChild.Parent.NodeKey);

        assertTrue(bst.DeleteNodeByKey(32));
        searchResult = bst.FindNodeByKey(32);
        assertFalse(searchResult.NodeHasKey);
        assertNull(bst.Root.RightChild);

        assertTrue(bst.DeleteNodeByKey(10));
        assertEquals(3, bst.Root.LeftChild.NodeKey);
        assertEquals(30, bst.Root.LeftChild.Parent.NodeKey);
        assertTrue(bst.DeleteNodeByKey(30));
        assertTrue(bst.DeleteNodeByKey(3));
        assertEquals(4, bst.Root.NodeKey);
        assertTrue(bst.DeleteNodeByKey(4));
        assertNull(bst.Root);
        assertFalse(bst.DeleteNodeByKey(1));
    }
}
