package ru.skillsmart.asd2.task1_Trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }

    @Override
    public String toString() {
        return "SimpleTreeNode{" +
                "NodeValue=" + NodeValue +
                '}';
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        // ваш код добавления нового дочернего узла существующему ParentNode

        if (ParentNode == null && Root == null && NewChild.Parent == null) {
            Root = NewChild;
            return;
        } else if (ParentNode == null) {
            throw new IllegalArgumentException("Root node is already set, can't add another node with ParentNode = null");
        }

        if (ParentNode.Children == null) {
            ParentNode.Children = new LinkedList<>();
        }
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        // ваш код удаления существующего узла NodeToDelete

        if (NodeToDelete.Parent == null)
            throw new IllegalArgumentException("Can't delete the root node");

        NodeToDelete.Parent.Children.remove(NodeToDelete);
        if (NodeToDelete.Parent.Children.isEmpty()) {
            NodeToDelete.Parent.Children = null;
        }
        NodeToDelete.Parent = null;

        if (NodeToDelete.Children != null) {
            List<SimpleTreeNode<T>> childrenCopy = new LinkedList<>(NodeToDelete.Children);
            for (SimpleTreeNode<T> node : childrenCopy) {
                DeleteNode(node);
            }
        }

    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        // ваш код выдачи всех узлов дерева в определённом порядке
        List<SimpleTreeNode<T>> allNodesList = new LinkedList<>();
        if (Root == null)
            return allNodesList;
        GetAllNodesRecursive(Root, allNodesList);
        return allNodesList;
    }

    private void GetAllNodesRecursive(SimpleTreeNode<T> currentNode, List<SimpleTreeNode<T>> allNodesList) {
        allNodesList.add(currentNode);
        if (currentNode.Children == null) {
            return;
        }
        for (SimpleTreeNode<T> child : currentNode.Children) {
            GetAllNodesRecursive(child, allNodesList);
        }
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        // ваш код поиска узлов по значению
        List<SimpleTreeNode<T>> foundNodesList = new LinkedList<>();
        if (Root == null)
            return foundNodesList;
        FindNodesByValueRecursive(val, Root, foundNodesList);
        return foundNodesList;
    }

    private void FindNodesByValueRecursive(T val, SimpleTreeNode<T> currentNode, List<SimpleTreeNode<T>> foundNodesList) {
        if (currentNode.NodeValue.equals(val))
            foundNodesList.add(currentNode);
        if (currentNode.Children == null)
            return;
        for (SimpleTreeNode<T> child : currentNode.Children) {
            FindNodesByValueRecursive(val, child, foundNodesList);
        }
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
        if (OriginalNode == null || OriginalNode.equals(Root) || NewParent == null)
            throw new IllegalArgumentException("Can't move the node");

        OriginalNode.Parent.Children.remove(OriginalNode);
        if (OriginalNode.Parent.Children.isEmpty()) {
            OriginalNode.Parent.Children = null;
        }
        AddChild(NewParent, OriginalNode);
    }

    public int Count() {
        // количество всех узлов в дереве
        return GetAllNodes().size();
    }

    public int LeafCount() {
        // количество листьев в дереве
        return GetAllNodes().stream().filter(node -> node.Children == null).toList().size();
    }

    public Map<SimpleTreeNode<T>, Integer> GetNodesWithLevels() {
        Map<SimpleTreeNode<T>, Integer> nodesWithLevels = new HashMap<>();
        if (Root == null)
            return nodesWithLevels;
        GetNodesWithLevelsRecursive(Root, 0, nodesWithLevels);
        return nodesWithLevels;
    }

    private void GetNodesWithLevelsRecursive(SimpleTreeNode<T> currentNode, int level, Map<SimpleTreeNode<T>, Integer> nodesWithLevels) {
        nodesWithLevels.put(currentNode, level);
        if (currentNode.Children == null)
            return;
        for (SimpleTreeNode<T> child : currentNode.Children) {
            GetNodesWithLevelsRecursive(child, level + 1, nodesWithLevels);
        }
    }

    public int GetNodeLevel(SimpleTreeNode<T> node) {
        if (Root == null)
            return 0;
        return GetNodeLevelRecursive(node, 0);

    }

    private int GetNodeLevelRecursive(SimpleTreeNode<T> node, int currentLevel) {
        if (node.Parent == null)
            return currentLevel;
        return GetNodeLevelRecursive(node.Parent, currentLevel + 1);
    }

    @Override
    public String toString() {
        return "SimpleTree{" +
                "Root=" + Root +
                '}';
    }
}
