package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

class TestTopSort {

    private Igraph graph;

    @BeforeEach
    void setup() {
        graph = new AdjacencyListGraph();
    }

    @Test
    void testSimpleLinearGraph() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        List<Integer> sorted = TopSort.topSort(graph);
        assertEquals(List.of(0, 1, 2), sorted);
    }

    @Test
    void testTreeLikeGraph() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        List<Integer> sorted = TopSort.topSort(graph);
        assertEquals(List.of(0, 1, 2, 3), sorted);
    }

    @Test
    void testDisconnectedGraph() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        List<Integer> sorted = TopSort.topSort(graph);
        assertEquals(List.of(0, 2, 1), sorted);
    }

    @Test
    void testCyclicGraph() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        List<Integer> sorted = TopSort.topSort(graph);
        assertNull(sorted);
    }

    @Test
    void testCompleteGraph() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        List<Integer> sorted = TopSort.topSort(graph);
        assertEquals(List.of(0, 1, 2), sorted);
    }

    @Test
    void testMultipleSources() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        List<Integer> sorted = TopSort.topSort(graph);
        assertEquals(List.of(0, 1, 2), sorted);
    }

    @Test
    void testEmptyGraph() {
        List<Integer> sorted = TopSort.topSort(new AdjacencyListGraph());
        assertEquals(List.of(), sorted);
    }

    @Test
    void testSingletonGraph() {
        graph.addNode();
        List<Integer> sorted = TopSort.topSort(graph);
        assertEquals(List.of(0), sorted);
    }

    @Test
    void testDiamondShapeGraph() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        List<Integer> sorted = TopSort.topSort(graph);
        assertEquals(List.of(0, 1, 2, 3), sorted);
    }

    @Test
    void testPartialOrdering() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        List<Integer> sorted = TopSort.topSort(graph);
        assertEquals(List.of(0, 1, 2), sorted);
    }
}