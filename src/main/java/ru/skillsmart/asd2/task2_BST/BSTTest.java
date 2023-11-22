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
        BSTNode<Integer> bstNodeRoot = new BSTNode<>(25, 1, null);
        BST<Integer> bst = new BST<>(bstNodeRoot);

        BSTFind<Integer> searchResult = bst.FindNodeByKey(25);
        assertTrue(searchResult.NodeHasKey);
        assertEquals(bstNodeRoot, searchResult.Node);

        searchResult = bst.FindNodeByKey(10);
        assertFalse(searchResult.NodeHasKey);
        assertTrue(searchResult.ToLeft);

        searchResult = bst.FindNodeByKey(30);
        assertFalse(searchResult.NodeHasKey);
        assertFalse(searchResult.ToLeft);
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
    }
}
