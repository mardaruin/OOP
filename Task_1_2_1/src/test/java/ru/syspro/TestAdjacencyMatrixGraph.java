package ru.syspro;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;



class TestAdjacencyMatrixGraph {

    @Test
    void testAddAndDeleteNode() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
        assertEquals(0, graph.getNumNodes());
        graph.addNode();
        assertEquals(1, graph.getNumNodes());
        graph.deleteNode(0);
        assertEquals(0, graph.getNumNodes());
    }

    @Test
    void testAddAndDeleteEdge() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        assertTrue(graph.deleteEdge(0, 1));
        assertFalse(graph.deleteEdge(0, 1));
    }

    @Test
    void testGetNeighbors() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        assertEquals(List.of(1), graph.getNeighbors(0));
    }

    @Test
    void testToStringRepresentation() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        assertEquals("1 \n\n", graph.toString());
    }

    @Test
    void testAddingTooManyNodesThrowsError() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
        for (int i = 0; i < 100; i++) {
            graph.addNode();
        }
        assertThrows(IllegalStateException.class, () -> graph.addNode());
    }

    @Test
    void testDeletingNonExistingNodeReturnsFalse() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
        assertFalse(graph.deleteNode(-1));
        assertFalse(graph.deleteNode(100));
    }

    @Test
    void testAddingEdgeBetweenNonexistentNodesThrowsError() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
        assertThrows(IllegalStateException.class, () -> graph.addEdge(0, 1));
    }

    @Test
    void testDeletingEdgeThatDoesntExistReturnsFalse() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
        graph.addNode();
        graph.addNode();
        assertFalse(graph.deleteEdge(0, 1));
    }
}