package ru.skillsmart.asd2.task9_even_trees_woods;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvenTreesWoodsTest {

    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
    SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(8, null);
    SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(9, null);
    SimpleTreeNode<Integer> node10 = new SimpleTreeNode<>(10, null);


    @Test
    public void generateAndCheckTree() {

        SimpleTree<Integer> tree = new SimpleTree<>(rootNode);
        tree.AddChild(rootNode, node2);
        tree.AddChild(rootNode, node3);
        tree.AddChild(rootNode, node6);
        tree.AddChild(node2, node5);
        tree.AddChild(node2, node7);
        tree.AddChild(node3, node4);
        tree.AddChild(node6, node8);
        tree.AddChild(node8, node9);
        tree.AddChild(node8, node10);

        assertEquals(List.of(1, 3, 1, 6), tree.EvenTrees());
    }

    @Test
    public void generateAndCheckTree2() {

        SimpleTree<Integer> tree = new SimpleTree<>(rootNode);
        tree.AddChild(rootNode, node2);
        tree.AddChild(rootNode, node3);
        tree.AddChild(rootNode, node6);
        tree.AddChild(node2, node5);
        tree.AddChild(node2, node7);
        tree.AddChild(node3, node4);
        tree.AddChild(node6, node8);
        tree.AddChild(node8, node9);
        tree.AddChild(node8, node10);
        tree.AddChild(node7, new SimpleTreeNode<>(11, null));
        tree.AddChild(node5, new SimpleTreeNode<>(12, null));
        tree.AddChild(node10, new SimpleTreeNode<>(13, null));
        tree.AddChild(node10, new SimpleTreeNode<>(14, null));
        tree.AddChild(node8, new SimpleTreeNode<>(15, null));
        tree.AddChild(node8, new SimpleTreeNode<>(16, null));
        tree.AddChild(node8, new SimpleTreeNode<>(18, null));
        tree.AddChild(node2, new SimpleTreeNode<>(17, null));

        assertEquals(List.of(2, 5, 2, 7, 1, 2, 1, 3, 6, 8), tree.EvenTrees());
    }

}
