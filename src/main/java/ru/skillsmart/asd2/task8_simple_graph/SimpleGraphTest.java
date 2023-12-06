package ru.skillsmart.asd2.task8_simple_graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleGraphTest {

    @Test
    public void addVertexTest() {
        SimpleGraph graph = new SimpleGraph(4);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        assertEquals(1, graph.vertex[0].Value);
        assertEquals(2, graph.vertex[1].Value);
        assertEquals(3, graph.vertex[2].Value);
        assertEquals(4, graph.vertex[3].Value);
    }

    @Test
    public void edgeActionsTest() {
        SimpleGraph graph = new SimpleGraph(4);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[0][1]);
        assertFalse(graph.IsEdge(0, 1));
        graph.AddEdge(0, 1);
        assertEquals(1, graph.m_adjacency[1][0]);
        assertEquals(1, graph.m_adjacency[0][1]);
        assertTrue(graph.IsEdge(0, 1));
        graph.RemoveEdge(1, 0);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[0][1]);
        assertFalse(graph.IsEdge(0, 1));
    }

    @Test
    public void removeVertexTest() {
        SimpleGraph graph = new SimpleGraph(4);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(1, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(2, 3);
        graph.RemoveVertex(3);
        assertNull(graph.vertex[3]);
        for (int i = 0; i < graph.max_vertex; i++) {
            assertEquals(0, graph.m_adjacency[i][3]);
            assertEquals(0, graph.m_adjacency[3][i]);
        }

        graph.AddVertex(5);
        graph.AddEdge(0, 3);
        assertEquals(1, graph.m_adjacency[0][3]);
        assertEquals(1, graph.m_adjacency[3][0]);
        assertEquals(5, graph.vertex[3].Value);
    }
}
