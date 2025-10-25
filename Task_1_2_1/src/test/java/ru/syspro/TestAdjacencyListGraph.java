package ru.syspro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;
import java.util.HashSet;

class TestAdjacencyListGraph {

    @Test
    void testAddAndDeleteNode() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        assertEquals(0, graph.getNumNodes());
        graph.addNode();
        assertEquals(1, graph.getNumNodes());
        graph.deleteNode(0);
        assertEquals(0, graph.getNumNodes());
    }

    @Test
    void testAddAndDeleteEdge() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        assertTrue(graph.deleteEdge(0, 1));
        assertFalse(graph.deleteEdge(0, 1));
    }

    @Test
    void testGetNeighbors() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        assertEquals(Set.of(1), new HashSet<>(graph.getNeighbors(0)));
    }

    @Test
    void testToStringRepresentation() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        assertEquals("[1]\n[]\n", graph.toString());
    }

    @Test
    void testDeletingNonExistingNodeReturnsFalse() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        assertFalse(graph.deleteNode(-1));
        assertFalse(graph.deleteNode(100));
    }

    @Test
    void testAddingEdgeBetweenNonexistentNodesThrowsError() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        assertThrows(IllegalStateException.class, () -> graph.addEdge(0, 1));
    }

    @Test
    void testDeletingEdgeThatDoesntExistReturnsFalse() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addNode();
        graph.addNode();
        assertFalse(graph.deleteEdge(0, 1));
    }

}