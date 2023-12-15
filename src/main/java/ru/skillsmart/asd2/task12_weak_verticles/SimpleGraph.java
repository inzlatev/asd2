package ru.skillsmart.asd2.task12_weak_verticles;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Vertex {
    public int Value;
    public boolean Hit;

    public Vertex(int val) {
        Value = val;
        Hit = false;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "Value=" + Value +
                '}';
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
        // ваш код добавления новой вершины
        // с значением value
        // в незанятую позицию vertex
        int slot = getEmptySlotIndex();
        if (slot == -1)
            return;
        Vertex newVertex = new Vertex(value);
        vertex[slot] = newVertex;
    }

    private int getEmptySlotIndex() {
        return IntStream.range(0, vertex.length)
                .filter(i -> vertex[i] == null)
                .findFirst().orElse(-1);
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        // ваш код удаления вершины со всеми её рёбрами
        if (v < 0 || v > max_vertex - 1)
            return;
        IntStream.range(0, max_vertex).forEach(i -> {
            m_adjacency[i][v] = 0;
            m_adjacency[v][i] = 0;
        });
        vertex[v] = null;
    }

    public boolean IsEdge(int v1, int v2) {
        // true если есть ребро между вершинами v1 и v2
        if (v1 < 0 || v1 > max_vertex - 1 || v2 < 0 || v2 > max_vertex - 1)
            return false;
        return m_adjacency[v1][v2] == 1;
    }

    public void AddEdge(int v1, int v2) {
        // добавление ребра между вершинами v1 и v2
        if (v1 < 0 || v1 > max_vertex - 1 || v2 < 0 || v2 > max_vertex - 1)
            return;
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        // удаление ребра между вершинами v1 и v2
        if (v1 < 0 || v1 > max_vertex - 1 || v2 < 0 || v2 > max_vertex - 1)
            return;
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        Arrays.stream(vertex).forEach(v -> v.Hit = false);

        Stack<Vertex> stack = new Stack<>();
        findPath(VFrom, VTo, stack);

        return new ArrayList<>(stack);
    }

    private boolean findPath(int VFrom, int VTo, Stack<Vertex> stack) {
        vertex[VFrom].Hit = true;
        stack.push(vertex[VFrom]);
        boolean wayFound = false;

        Queue<Integer> availableIndexes = getAvailableIndexes(VFrom);

        if (availableIndexes.contains(VTo)) {
            stack.push(vertex[VTo]);
            return true;
        }

        if (availableIndexes.isEmpty()) {
            stack.pop();
            return false;
        }
        while (!wayFound) {
            availableIndexes = getAvailableIndexes(VFrom);
            Integer availableIndex = availableIndexes.poll();
            if (availableIndex == null) {
                stack.pop();
                return false;
            }
            wayFound = findPath(availableIndex, VTo, stack);
        }
        return wayFound;
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        Arrays.stream(vertex).forEach(v -> v.Hit = false);

        Integer[] elementParents = collectParents(VFrom, VTo);

        return tracePath(VFrom, VTo, elementParents);
    }

    public Integer[] collectParents(int VFrom, int VTo) {
        vertex[VFrom].Hit = true;

        Queue<Integer> vertexIndexQueue = new ArrayDeque<>();
        vertexIndexQueue.add(VFrom);
        Queue<Integer> availableNeighborsList;
        Integer[] parents = new Integer[vertex.length];
        boolean wayFound = false;

        while (!vertexIndexQueue.isEmpty()) {
            int currentIndex = vertexIndexQueue.poll();
            availableNeighborsList = getAvailableIndexes(currentIndex);
            vertexIndexQueue.addAll(availableNeighborsList.stream().filter(neighbor -> !vertexIndexQueue.contains(neighbor)).toList());

            for (Integer nextIndex : availableNeighborsList) {
                if (nextIndex == null)
                    break;

                vertex[nextIndex].Hit = true;
                availableNeighborsList = getAvailableIndexes(nextIndex);
                vertexIndexQueue.addAll(availableNeighborsList.stream().filter(neighbor -> !vertexIndexQueue.contains(neighbor)).toList());
                parents[nextIndex] = currentIndex;

                if (nextIndex == VTo) {
                    wayFound = true;
                    break;
                }

            }
            if (wayFound)
                break;
        }
        return parents;
    }

    private ArrayList<Vertex> tracePath(int VFrom, int VTo, Integer[] parents) {
        if (!vertex[VTo].Hit)
            return new ArrayList<>();

        LinkedList<Vertex> resultList = new LinkedList<>();
        int currentIndex = VTo;

        while (currentIndex != VFrom) {
            resultList.addFirst(vertex[currentIndex]);
            currentIndex = parents[currentIndex];
        }
        resultList.addFirst(vertex[VFrom]);

        return new ArrayList<>(resultList);
    }

    private Queue<Integer> getAvailableIndexes(int v) {
        return IntStream.range(0, max_vertex)
                .filter(i -> m_adjacency[v][i] == 1 && !vertex[i].Hit)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public ArrayList<Vertex> WeakVertices() {
        Arrays.stream(vertex).forEach(v -> v.Hit = false);

        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i].Hit)
                continue;
            for (int j = 0; j < max_vertex; j++) {
                for (int k = 0; k < max_vertex; k++) {
                    if (i != j && i != k && j != k &&
                            m_adjacency[i][j] == 1 &&
                            m_adjacency[j][k] == 1 &&
                            m_adjacency[k][i] == 1) {
                        vertex[i].Hit = true;
                        vertex[j].Hit = true;
                        vertex[k].Hit = true;
                        break;
                    }
                }
                if (vertex[i].Hit) {
                    break;
                }
            }
        }
        return Arrays.stream(vertex).filter(v -> !v.Hit).collect(Collectors.toCollection(ArrayList<Vertex>::new));
    }

}