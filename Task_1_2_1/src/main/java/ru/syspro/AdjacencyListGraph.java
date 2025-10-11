package ru.syspro;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;



/**
 * Graph as Adjacency List.
 *
 */
public class AdjacencyListGraph implements IGraph {
    private Map<Integer, Set<Integer>> adjList;
    private int numNodes;

    @Override
    public int getNumNodes() {
        return numNodes;
    }

    public AdjacencyListGraph() {
        this.adjList = new HashMap<>();
        this.numNodes = 0;
    }

    @Override
    public void addNode() {
        adjList.put(numNodes++, new HashSet<>());
    }

    @Override
    public boolean deleteNode(int node) {
        if (!adjList.containsKey(node)) {
            return false;
        }
        adjList.remove(node);

        for (Set<Integer> edges : adjList.values()) {
            edges.remove(node);
        }

        numNodes--;
        return true;
    }

    @Override
    public void addEdge(int fromNode, int toNode) {
        checkNode(fromNode);
        checkNode(toNode);
        adjList.get(fromNode).add(toNode);
    }

    @Override
    public boolean deleteEdge(int fromNode, int toNode) {
        checkNode(fromNode);
        checkNode(toNode);
        return adjList.get(fromNode).remove(toNode);
    }

    @Override
    public List<Integer> getNeighbors(int node) {
        checkNode(node);
        return new ArrayList<>(adjList.getOrDefault(node, Collections.emptySet()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Set<Integer>> entry : adjList.entrySet()) {
            sb.append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    private void checkNode(int node) {
        if (node < 0 || node >= numNodes) {
            throw new IllegalStateException("Node does not exist.");
        }
    }
}