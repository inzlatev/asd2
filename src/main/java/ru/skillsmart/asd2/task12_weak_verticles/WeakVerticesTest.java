package ru.skillsmart.asd2.task12_weak_verticles;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeakVerticesTest {

    @Test
    public void weakVerticlesTest() {

        SimpleGraph graph = new SimpleGraph(9);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);
        graph.AddVertex(8);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(2, 3);
        graph.AddEdge(2, 4);
        graph.AddEdge(4, 7);
        graph.AddEdge(2, 5);
        graph.AddEdge(5, 7);
        graph.AddEdge(5, 6);
        graph.AddEdge(5, 8);
        graph.AddEdge(6, 8);
        graph.AddEdge(7, 8);

        ArrayList<Vertex> weakVerticles = graph.WeakVertices();

        int[] expectedResult = new int[]{0, 4};
        for (int i = 0; i < expectedResult.length; i++) {
            assertEquals(expectedResult[i], weakVerticles.get(i).Value);
        }
    }
}
