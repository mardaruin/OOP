package ru.syspro;


import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestIncidenceMatrixGraph {


    @Test
    void testAddAndDeleteNode() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        assertEquals(0, graph.getNumNodes());
        graph.addNode();
        assertEquals(1, graph.getNumNodes());
        graph.deleteNode(0);
        assertEquals(0, graph.getNumNodes());
    }

    @Test
    void testAddAndDeleteEdge() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        assertTrue(graph.deleteEdge(0, 1));
        assertFalse(graph.deleteEdge(0, 1));
    }

    @Test
    void testGetNeighbors() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        assertEquals(List.of(1), graph.getNeighbors(0));
    }

    @Test
    void testToStringRepresentation() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        assertEquals("1 \n\n", graph.toString());
    }

    @Test
    void testAddingTooManyNodesThrowsError() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        for (int i = 0; i < 100; i++) {
            graph.addNode();
        }
        assertThrows(IllegalStateException.class, () -> graph.addNode());
    }

    @Test
    void testAddingTooManyEdgesThrowsError() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode();
        graph.addNode();
        for (int i = 0; i < 100; i++) {
            graph.addEdge(0, 1);
        }
        assertThrows(IllegalStateException.class, () -> graph.addEdge(0, 1));
    }

    @Test
    void testDeletingNonExistingNodeReturnsFalse() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        assertFalse(graph.deleteNode(-1));
        assertFalse(graph.deleteNode(100));
    }

    @Test
    void testAddingEdgeBetweenNonexistentNodesThrowsError() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        assertThrows(IllegalStateException.class, () -> graph.addEdge(0, 1));
    }

    @Test
    void testDeletingEdgeThatDoesntExistReturnsFalse() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode();
        graph.addNode();
        assertFalse(graph.deleteEdge(0, 1));
    }

    @Test
    void testGraphEdgeCreationOrder() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        assertEquals(2, graph.getNumEdges());
    }

    @Test
    void testAddingDuplicateEdge() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.addEdge(0, 1);
        assertEquals(2, graph.getNumEdges());
    }

    @Test
    void testGettingNeighborsWhenEdgeRemoved() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.deleteEdge(0, 1);
        assertTrue(graph.getNeighbors(0).isEmpty());
    }

    @Test
    void testGetNeighborsForDisjointNodes() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        assertTrue(graph.getNeighbors(2).isEmpty());
    }

    @Test
    void testRemovingMiddleNodeBreaksChain() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.deleteNode(1);
        assertTrue(graph.getNeighbors(0).isEmpty());
    }

}