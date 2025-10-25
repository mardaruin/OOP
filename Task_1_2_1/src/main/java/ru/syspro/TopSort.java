package ru.syspro;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Topological sort for graph.
 *
 */
public class TopSort {

    /**
     * Topological sort.
     *
     * @param graph
     * @return
     */
    public static List<Integer> topSort(Igraph graph) {
        List<Integer> result = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[graph.getNumNodes()];

        for (int u = 0; u < graph.getNumNodes(); u++) {
            for (int v : graph.getNeighbors(u)) {
                inDegree[v]++;
            }
        }

        for (int i = 0; i < graph.getNumNodes(); i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            for (int next : graph.getNeighbors(current)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return result.size() == graph.getNumNodes() ? result : null;
    }
}