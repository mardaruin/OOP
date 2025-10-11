package ru.syspro;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


/**
 * Graph as Incidence Matrix.
 *
 */
public class IncidenceMatrixGraph implements IGraph {
    private final int MAX_NODES = 100;
    private final int MAX_EDGES = 100;
    private int[][] incidenceMatrix;
    private int numNodes;
    private int numEdges;

    @Override
    public int getNumNodes() {
        return numNodes;
    }

    public IncidenceMatrixGraph() {
        this.incidenceMatrix = new int[MAX_NODES][MAX_EDGES];
        this.numNodes = 0;
        this.numEdges = 0;
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

        // delete all edges with node.
        int nodeEdges = 0;
        for (int i = 0; i < numEdges;) {
            if (incidenceMatrix[node][i] != 0) {
                nodeEdges++;
                for (int j = i + 1; j < numEdges; j++) {
                    for (int k = 0; k < numNodes; k++) {
                        incidenceMatrix[k][j - 1] = incidenceMatrix[k][j];
                    }
                }
                numEdges--;
            } else {
                i++;
            }
        }

        // lift (rename) nodes after deleted one.
        for (int i = node + 1; i < numNodes; i++) {
            System.arraycopy(incidenceMatrix[i], 0, incidenceMatrix[i - 1], 0, numEdges);
        }

        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < nodeEdges; j++) {
                incidenceMatrix[i][numEdges - 1 - j] = 0;
            }
            incidenceMatrix[numNodes - 1][i] = 0;
        }

        numNodes--;
        return true;
    }

    @Override
    public void addEdge(int fromNode, int toNode) {
        checkNode(fromNode);
        checkNode(toNode);
        if (numEdges + 1 > MAX_EDGES) {
            throw new IllegalStateException("Maximum number of nodes already reached.");
        }
        numEdges++;
        incidenceMatrix[fromNode][numEdges - 1] = 1;
        incidenceMatrix[toNode][numEdges - 1] = -1;
    }

    @Override
    public boolean deleteEdge(int fromNode, int toNode) {
        checkNode(fromNode);
        checkNode(toNode);

        int ind = findEdgeIndex(fromNode, toNode);
        if (ind == -1) {
            return false;
        }

        // delete edge and rename (move) edges after deleted one.
        for (int i = ind + 1; i < numEdges; i++) {
            for (int j = 0; j < numNodes; j++) {
                incidenceMatrix[j][i - 1] = incidenceMatrix[j][i];
            }
        }

        for (int i = 0; i < numNodes; i++) {
            incidenceMatrix[i][numEdges - 1] = 0;
        }
        numEdges--;
        return true;
    }

    private int findEdgeIndex(int fromNode, int toNode) {
        for (int i = 0; i < numEdges; i++) {
            if ((incidenceMatrix[fromNode][i] == 1 && incidenceMatrix[toNode][i] == -1) ||
                    (incidenceMatrix[fromNode][i] == -1 && incidenceMatrix[toNode][i] == 1)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Integer> getNeighbors(int node) {
        checkNode(node);
        List<Integer> nb = new ArrayList<>();
        for (int i = 0; i < numEdges; i++) {
            if (incidenceMatrix[node][i] > 0) {
                for (int j = 0; j < numNodes; j++) {
                    if (incidenceMatrix[j][i] < 0) {
                        nb.add(j);
                        break;
                    }
                }
            }
        }
        return nb;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numEdges; j++) {
                if (incidenceMatrix[i][j] > 0) {
                    int neigh = 0;
                    while (neigh < numNodes && incidenceMatrix[neigh][j] >= 0) {
                        neigh++;
                    }
                    sb.append(neigh).append(' ');
                    break;
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