package ru.syspro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * Graph as Adjacency Matrix.
 *
 */
public class AdjacencyMatrixGraph implements IGraph {
    private final int MAX_NODES = 100;
    private int[][] adjacencyMatrix;
    private int numNodes;

    @Override
    public int getNumNodes() {
        return numNodes;
    }

    public AdjacencyMatrixGraph() {
        this.adjacencyMatrix = new int[MAX_NODES][MAX_NODES];
        this.numNodes = 0;
    }

    @Override
    public void addNode() {
        if (numNodes + 1 > MAX_NODES) {
            throw new IllegalStateException("Maximum number of nodes already reached.");
        }
        numNodes++;
    }

    @Override
    public boolean deleteNode(int node) {
        if (node < 0 || node >= numNodes) {
            return false;
        }
        for (int i = node + 1; i < numNodes; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, adjacencyMatrix[i - 1], 0, numNodes);
        }

        for (int i = 0; i < numNodes; i++) {
            for (int j = node + 1; j < numNodes; j++) {
                adjacencyMatrix[i][j - 1] = adjacencyMatrix[i][j];
            }
        }

        for (int i = 0; i < numNodes; i++) {
            adjacencyMatrix[i][numNodes - 1] = 0;
            adjacencyMatrix[numNodes - 1][i] = 0;
        }

        numNodes--;
        return true;
    }

    @Override
    public void addEdge(int fromNode, int toNode) {
        checkNode(fromNode);
        checkNode(toNode);
        adjacencyMatrix[fromNode][toNode] = 1;
    }

    @Override
    public boolean deleteEdge(int fromNode, int toNode) {
        checkNode(fromNode);
        checkNode(toNode);
        if (adjacencyMatrix[fromNode][toNode] == 0) {
            return false;
        }
        adjacencyMatrix[fromNode][toNode] = 0;
        return true;
    }

    @Override
    public List<Integer> getNeighbors(int node) {
        checkNode(node);
        List<Integer> nb = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            if (adjacencyMatrix[node][i] > 0) {
                nb.add(i);
            }
        }
        return nb;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    sb.append(j).append(' ');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private void checkNode(int node) {
        if (node < 0 || node >= numNodes) {
            throw new IllegalStateException("Node does not exist.");
        }
    }

}