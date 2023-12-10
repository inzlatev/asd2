package ru.skillsmart.asd2.task10_depth_first_graph_search;

import java.util.*;

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
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == null)
                return i;
        }
        return -1;
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        // ваш код удаления вершины со всеми её рёбрами
        if (v < 0 || v > max_vertex - 1)
            return;
        for (int i = 0; i < max_vertex; i++) {
            m_adjacency[i][v] = 0;
            m_adjacency[v][i] = 0;
        }
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
        for (Vertex v : vertex) {
            v.Hit = false;
        }
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

    private Queue<Integer> getAvailableIndexes(int v) {
        Queue<Integer> indexesList = new ArrayDeque<>();
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[v][i] == 1 && !vertex[i].Hit)
                indexesList.add(i);
        }
        return indexesList;
    }
}