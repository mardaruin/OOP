package ru.syspro;


/**
 * Entry point for running the program directly from the command-line.
 */

public class Main {
    public static void main(String[] args) {
        IGraph matrixGraph = new AdjacencyMatrixGraph();
        matrixGraph.addNode();
        matrixGraph.addNode();
        matrixGraph.addEdge(0, 1);
        System.out.println(matrixGraph.toString());

        IGraph incidenceGraph = new IncidenceMatrixGraph();
        incidenceGraph.addNode();
        incidenceGraph.addNode();
        incidenceGraph.addEdge(0, 1);
        System.out.println(incidenceGraph.toString());

        IGraph listGraph = new AdjacencyListGraph();
        listGraph.addNode();
        listGraph.addNode();
        listGraph.addEdge(0, 1);
        System.out.println(listGraph.toString());
    }
}