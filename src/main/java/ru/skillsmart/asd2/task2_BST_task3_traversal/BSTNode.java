package ru.skillsmart.asd2.task2_BST_task3_traversal;

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
        return
                "NodeKey : " +
                        NodeKey
                        +
                        ", LeftChild : {" + LeftChild + "}" +
                        ", RightChild : {" + RightChild +
                        "}"
                ;
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

        if (searchResult == null || !searchResult.NodeHasKey)
            return false; // если узел не найден

        BSTNode<T> initialNode = searchResult.Node;
        BSTNode<T> replacementNode = findReplacementNode(initialNode);

        if (replacementNode == initialNode) {
            handleRemovalWithNoRightNodes(initialNode);
            return true;
        }

        if (initialNode.RightChild == replacementNode) {
            replaceByRightChild(initialNode, replacementNode);
            return true;
        }

        if (replacementNode.RightChild != null) {
            replacementNode.Parent.LeftChild = replacementNode.RightChild;
            replacementNode.RightChild.Parent = replacementNode.Parent;
        } else {
            relinkRightOrLeftChildWithNodeParent(null, replacementNode);
        }

        if (initialNode.Parent != null) {
            relinkRightOrLeftChildWithNodeParent(replacementNode, initialNode);
        } else {
            Root = replacementNode;
            replacementNode.Parent = null;
        }
        replacementNode.LeftChild = initialNode.LeftChild;
        if (initialNode.LeftChild != null) {
            initialNode.LeftChild.Parent = replacementNode;
        }
        replacementNode.RightChild = initialNode.RightChild;
        initialNode.RightChild.Parent = replacementNode;
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

    private void handleRemovalWithNoRightNodes(BSTNode<T> node) {
        if (node == Root && node.LeftChild != null) {
            node.LeftChild.Parent = null;
            Root = node.LeftChild;
            CleanUpNode(node);
            return;
        }
        if (node == Root) {
            Root = null;
            return;
        }
        relinkRightOrLeftChildWithNodeParent(node.LeftChild, node);
        CleanUpNode(node);
    }

    private void relinkRightOrLeftChildWithNodeParent(BSTNode<T> child, BSTNode<T> node) {
        boolean isRightChild = node.Parent.RightChild == node;
        if (isRightChild) {
            node.Parent.RightChild = child;
        } else {
            node.Parent.LeftChild = child;
        }
        if (child != null) {
            child.Parent = node.Parent;
        }
    }

    private void replaceByRightChild(BSTNode<T> initialNode, BSTNode<T> replacementNode) {
        replacementNode.Parent = initialNode.Parent;
        if (initialNode.Parent != null) {
            relinkRightOrLeftChildWithNodeParent(replacementNode, initialNode);
        } else {
            Root = replacementNode;
        }
        replacementNode.LeftChild = initialNode.LeftChild;
        if (initialNode.LeftChild != null) {
            initialNode.LeftChild.Parent = replacementNode;
        }
        CleanUpNode(initialNode);
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

    public ArrayList<BSTNode> WideAllNodes() {
        ArrayList<BSTNode> allNodesList = new ArrayList<>();
        if (Root == null) {
            return allNodesList;
        }
        WideAllNodesRecursive(new ArrayList<>(List.of(Root)), allNodesList);
        return allNodesList;
    }

    private void WideAllNodesRecursive(ArrayList<BSTNode> currentLevelNodes, ArrayList<BSTNode> allNodesList) {
        if (currentLevelNodes.isEmpty())
            return;
        allNodesList.addAll(currentLevelNodes);

        ArrayList<BSTNode> tempList = new ArrayList<>();
        for (BSTNode node : currentLevelNodes) {
            if (node.LeftChild != null) {
                tempList.add(node.LeftChild);
            }
            if (node.RightChild != null) {
                tempList.add(node.RightChild);
            }
        }
        WideAllNodesRecursive(tempList, allNodesList);
    }

    public ArrayList<BSTNode> DeepAllNodes(int traversalAlgorithm) {
        ArrayList<BSTNode> allNodesList = new ArrayList<>();
        if (Root == null) {
            return allNodesList;
        }
        switch (traversalAlgorithm) {
            case 0:
                TraverseInOrder(Root, allNodesList);
                break;
            case 1:
                TraversePostOrder(Root, allNodesList);
                break;
            case 2:
                TraversePreOrder(Root, allNodesList);
                break;
        }

        return allNodesList;
    }

    private void TraverseInOrder(BSTNode currentNode, ArrayList<BSTNode> allNodesList) {
        if (currentNode.LeftChild != null) {
            TraverseInOrder(currentNode.LeftChild, allNodesList);
        }
        allNodesList.add(currentNode);
        if (currentNode.RightChild != null) {
            TraverseInOrder(currentNode.RightChild, allNodesList);
        }
    }

    private void TraversePostOrder(BSTNode currentNode, ArrayList<BSTNode> allNodesList) {
        if (currentNode.LeftChild != null) {
            TraversePostOrder(currentNode.LeftChild, allNodesList);
        }
        if (currentNode.RightChild != null) {
            TraversePostOrder(currentNode.RightChild, allNodesList);
        }
        allNodesList.add(currentNode);
    }

    private void TraversePreOrder(BSTNode currentNode, ArrayList<BSTNode> allNodesList) {
        allNodesList.add(currentNode);
        if (currentNode.LeftChild != null) {
            TraversePreOrder(currentNode.LeftChild, allNodesList);
        }
        if (currentNode.RightChild != null) {
            TraversePreOrder(currentNode.RightChild, allNodesList);
        }
    }


    @Override
    public String toString() {
        return "{" + Root +
                '}';
    }
}