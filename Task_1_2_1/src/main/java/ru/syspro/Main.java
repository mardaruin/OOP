package ru.syspro;

/**
 * Entry point for running the program
 * directly from the command-line.
 *
 */
public class Main {

    /**
     * Main entry point for launching the application.
     */
    public static void main(String[] args) {
        Igraph matrixGraph = new AdjacencyMatrixGraph();
        matrixGraph.addNode();
        matrixGraph.addNode();
        matrixGraph.addEdge(0, 1);
        System.out.println(matrixGraph.toString());

        Igraph incidenceGraph = new IncidenceMatrixGraph();
        incidenceGraph.addNode();
        incidenceGraph.addNode();
        incidenceGraph.addEdge(0, 1);
        System.out.println(incidenceGraph.toString());

        Igraph listGraph = new AdjacencyListGraph();
        listGraph.addNode();
        listGraph.addNode();
        listGraph.addEdge(0, 1);
        System.out.println(listGraph.toString());
    }
}