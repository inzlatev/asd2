package ru.skillsmart.asd2.task11_breadth_first_graph_search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BFSTest {

    @Test
    public void bfsTest() {
        SimpleGraph graph = new SimpleGraph(14);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);
        graph.AddVertex(8);
        graph.AddVertex(9);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);

        graph.AddEdge(0, 9);
        graph.AddEdge(0, 7);
        graph.AddEdge(0, 11);
        graph.AddEdge(9, 10);
        graph.AddEdge(9, 8);
        graph.AddEdge(10, 1);
        graph.AddEdge(8, 1);
        graph.AddEdge(8, 12);
        graph.AddEdge(12, 2);
        graph.AddEdge(7, 11);
        graph.AddEdge(7, 3);
        graph.AddEdge(7, 6);
        graph.AddEdge(7, 6);
        graph.AddEdge(3, 2);
        graph.AddEdge(3, 4);
        graph.AddEdge(7, 6);
        graph.AddEdge(6, 5);

        int[] expectedResult = new int[]{1};
        ArrayList<Vertex> actualResult = graph.BreadthFirstSearch(1, 1);
        for (int i = 0; i < expectedResult.length; i++) {
            assertEquals(expectedResult[i], actualResult.get(i).Value);
        }

        expectedResult = new int[]{12, 2, 3, 4};
        actualResult = graph.BreadthFirstSearch(12, 4);
        for (int i = 0; i < expectedResult.length; i++) {
            assertEquals(expectedResult[i], actualResult.get(i).Value);
        }
        expectedResult = new int[]{0, 11};
        actualResult = graph.BreadthFirstSearch(0, 11);
        for (int i = 0; i < expectedResult.length; i++) {
            assertEquals(expectedResult[i], actualResult.get(i).Value);
        }
        actualResult = graph.BreadthFirstSearch(0, 13);
        assertEquals(0, actualResult.size());

    }

}
