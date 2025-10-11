package ru.syspro;

import java.util.List;

/**
 * Basic interface for graph.
 */
public interface Igraph {

    void addNode();

    boolean deleteNode(int node);

    void addEdge(int fromNode, int toNode);

    boolean deleteEdge(int fromNode, int toNode);

    public int getNumNodes();

    List<Integer> getNeighbors(int node);

    String toString();
}