package ru.skillsmart.asd2.task10_depth_first_graph_search;

import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepthFirstGraphTest {

    @Test
    public void depthFirstSearchNoWay() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(1, 2);
        graph.AddEdge(1, 3);
        assertTrue(graph.DepthFirstSearch(0, 4).isEmpty());
    }

    @Test
    public void depthFirstSearchSimpleWay() {
        SimpleGraph graph = new SimpleGraph(4);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);

        assertEquals(List.of(graph.vertex[0], graph.vertex[1], graph.vertex[2], graph.vertex[3]),
                graph.DepthFirstSearch(0, 3));
    }

    @Test
    public void depthFirstSearchComplexWay() {
        SimpleGraph graph = new SimpleGraph(6);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(1, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(4, 5);

        assertEquals(List.of(graph.vertex[0], graph.vertex[1], graph.vertex[4], graph.vertex[5]),
                graph.DepthFirstSearch(0, 5));
        graph.AddEdge(3, 4);
        assertEquals(List.of(graph.vertex[0], graph.vertex[1], graph.vertex[3], graph.vertex[4], graph.vertex[5]),
                graph.DepthFirstSearch(0, 5));
    }

}
