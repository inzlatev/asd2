package ru.skillsmart.asd2.task1_Trees;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTreeNodeTest {
    SimpleTreeNode<String> rootNode = new SimpleTreeNode<>("0", null);
    SimpleTreeNode<String> firstLevelNode1 = new SimpleTreeNode<>("1-1", rootNode);
    SimpleTreeNode<String> secondLevelNode1 = new SimpleTreeNode<>("2-1", firstLevelNode1);
    SimpleTreeNode<String> secondLevelNode2 = new SimpleTreeNode<>("2-2", firstLevelNode1);
    SimpleTreeNode<String> thirdLevelNode1 = new SimpleTreeNode<>("3-1", secondLevelNode1);
    SimpleTreeNode<String> fourthLevelNode1 = new SimpleTreeNode<>("4-1", thirdLevelNode1);
    SimpleTreeNode<String> fourthLevelNode2 = new SimpleTreeNode<>("4-2", thirdLevelNode1);
    SimpleTreeNode<String> fifthLevelNode1 = new SimpleTreeNode<>("5-1", fourthLevelNode1);

    @Test
    public void addChildTest() {
        SimpleTree<String> tree = new SimpleTree<>(null);
        assertNull(tree.Root);

        tree.AddChild(null, rootNode);
        assertSame("0", tree.Root.NodeValue);
        assertNull(tree.Root.Children);

        tree.AddChild(rootNode, firstLevelNode1);
        assertTrue(tree.Root.Children.contains(firstLevelNode1));
        assertSame(tree.Root.Children.get(0).Parent, tree.Root);

        tree.AddChild(firstLevelNode1, secondLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode2);
        assertTrue(tree.Root.Children.get(0).Children.containsAll(List.of(secondLevelNode1, secondLevelNode2)));
        assertSame(tree.Root.Children.get(0).Children.get(0).Parent, tree.Root.Children.get(0));
        assertSame(tree.Root.Children.get(0).Children.get(1).Parent, tree.Root.Children.get(0));

    }

    @Test
    public void deleteNodeTest() {
        SimpleTree<String> tree = new SimpleTree<>(rootNode);

        tree.AddChild(rootNode, firstLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode2);

        tree.DeleteNode(firstLevelNode1);
        assertNull(rootNode.Children);
        assertNull(firstLevelNode1.Parent);
        assertNull(firstLevelNode1.Children);
        assertNull(secondLevelNode1.Parent);
        assertNull(secondLevelNode2.Parent);
    }

    @Test
    public void getAllNodesTest() {
        SimpleTree<String> tree = new SimpleTree<>(rootNode);
        tree.AddChild(rootNode, firstLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode2);

        List<SimpleTreeNode<String>> allNodes = tree.GetAllNodes();
        assertEquals(4, allNodes.size());
    }

    @Test
    public void moveNodeTest() {
        SimpleTree<String> tree = new SimpleTree<>(rootNode);
        tree.AddChild(rootNode, firstLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode2);

        tree.AddChild(secondLevelNode1, thirdLevelNode1);
        tree.AddChild(thirdLevelNode1, fourthLevelNode1);
        tree.AddChild(thirdLevelNode1, fourthLevelNode2);
        tree.AddChild(fourthLevelNode1, fifthLevelNode1);

        List<SimpleTreeNode<String>> allNodes = tree.GetAllNodes();
        System.out.println(allNodes);
        tree.MoveNode(thirdLevelNode1, secondLevelNode2);

        assertNull(secondLevelNode1.Children);
        assertEquals(thirdLevelNode1.Parent, secondLevelNode2);
        assertTrue(thirdLevelNode1.Children.contains(fourthLevelNode1));
        assertTrue(thirdLevelNode1.Children.contains(fourthLevelNode2));
        assertTrue(fourthLevelNode1.Children.contains(fifthLevelNode1));
    }

    @Test
    public void findNodesByValueTest() {
        SimpleTree<String> tree = new SimpleTree<>(null);

        tree.AddChild(null, rootNode);
        tree.AddChild(rootNode, firstLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode2);

        tree.AddChild(secondLevelNode1, thirdLevelNode1);
        tree.AddChild(thirdLevelNode1, fourthLevelNode1);
        tree.AddChild(thirdLevelNode1, fourthLevelNode2);
        tree.AddChild(fourthLevelNode1, fifthLevelNode1);

        firstLevelNode1.NodeValue = "0";
        fifthLevelNode1.NodeValue = "0";
        assertTrue(tree.FindNodesByValue("2-1").contains(secondLevelNode1));
        assertEquals(3, tree.FindNodesByValue("0").size());
        assertTrue(tree.FindNodesByValue("0").containsAll(List.of(rootNode, firstLevelNode1, fifthLevelNode1)));
    }

    @Test
    public void countAndLeafCountTest() {
        SimpleTree<String> tree = new SimpleTree<>(null);

        tree.AddChild(null, rootNode);
        tree.AddChild(rootNode, firstLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode2);

        assertEquals(4, tree.Count());
        assertEquals(2, tree.LeafCount());

        tree.AddChild(secondLevelNode1, thirdLevelNode1);
        tree.AddChild(thirdLevelNode1, fourthLevelNode1);
        tree.AddChild(thirdLevelNode1, fourthLevelNode2);
        tree.AddChild(fourthLevelNode1, fifthLevelNode1);

        assertEquals(8, tree.Count());
        assertEquals(3, tree.LeafCount());
    }

    @Test
    public void getNodesWithLevelsTest() {
        SimpleTree<String> tree = new SimpleTree<>(null);

        tree.AddChild(null, rootNode);
        tree.AddChild(rootNode, firstLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode2);
        tree.AddChild(secondLevelNode1, thirdLevelNode1);
        tree.AddChild(thirdLevelNode1, fourthLevelNode1);
        tree.AddChild(thirdLevelNode1, fourthLevelNode2);
        tree.AddChild(fourthLevelNode1, fifthLevelNode1);

        Map<SimpleTreeNode<String>, Integer> nodesWithLevels = tree.GetNodesWithLevels();

        assertEquals(0, nodesWithLevels.get(rootNode));
        assertEquals(1, nodesWithLevels.get(firstLevelNode1));
        assertEquals(2, nodesWithLevels.get(secondLevelNode1));
        assertEquals(2, nodesWithLevels.get(secondLevelNode2));
        assertEquals(3, nodesWithLevels.get(thirdLevelNode1));
        assertEquals(4, nodesWithLevels.get(fourthLevelNode1));
        assertEquals(4, nodesWithLevels.get(fourthLevelNode1));
        assertEquals(5, nodesWithLevels.get(fifthLevelNode1));
    }

    @Test
    public void getNodeLevelTest() {
        SimpleTree<String> tree = new SimpleTree<>(null);

        tree.AddChild(null, rootNode);
        tree.AddChild(rootNode, firstLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode1);
        tree.AddChild(firstLevelNode1, secondLevelNode2);
        tree.AddChild(secondLevelNode1, thirdLevelNode1);
        tree.AddChild(thirdLevelNode1, fourthLevelNode1);
        tree.AddChild(thirdLevelNode1, fourthLevelNode2);
        tree.AddChild(fourthLevelNode1, fifthLevelNode1);

        assertEquals(5, tree.GetNodeLevel(fifthLevelNode1));
        assertEquals(1, tree.GetNodeLevel(firstLevelNode1));
        assertEquals(0, tree.GetNodeLevel(rootNode));
    }

}
