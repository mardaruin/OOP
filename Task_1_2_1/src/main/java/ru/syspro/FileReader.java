package ru.syspro;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * Reading graph in requirement mode.
 *
 */
public class FileReader {
    public static IGraph readFromFile(String filePath) throws Exception {
        try (Scanner scanner = new Scanner(Files.newInputStream(Paths.get(filePath)))) {
            String firstLine = scanner.nextLine().trim();
            switch (firstLine) {
                case "#ADJACENCY_MATRIX":
                    return parseAdjacencyMatrix(scanner);
                case "#INCIDENCE_MATRIX":
                    return parseIncidenceMatrix(scanner);
                case "#ADJACENCY_LIST":
                    return parseAdjacencyList(scanner);
                default:
                    throw new IllegalArgumentException("Неправильный формат файла");
            }
        }
    }

    private static IGraph parseAdjacencyMatrix(Scanner scanner) {
        var graph = new AdjacencyMatrixGraph();
        while (scanner.hasNextLine()) {
            String[] row = scanner.nextLine().split("\\s+");
            for (int col = 0; col < row.length; col++) {
                int value = Integer.parseInt(row[col]);
                if (value > 0) {
                    graph.addEdge(col, graph.getNumNodes());
                }
            }
            graph.addNode();
        }
        return graph;
    }

    private static IGraph parseIncidenceMatrix(Scanner scanner) {
        var graph = new IncidenceMatrixGraph();
        while (scanner.hasNextLine()) {
            String[] row = scanner.nextLine().split("\\s+");
            for (int col = 0; col < row.length; col++) {
                int value = Integer.parseInt(row[col]);
                if (value > 0) {
                    graph.addEdge(col, graph.getNumNodes());
                }
            }
            graph.addNode();
        }
        return graph;
    }

    private static IGraph parseAdjacencyList(Scanner scanner) {
        var graph = new AdjacencyListGraph();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(":");
            int vertex = Integer.parseInt(parts[0].trim());
            graph.addNode();
            if (parts.length > 1) {
                String[] neighbors = parts[1].split(",");
                for (String neighbor : neighbors) {
                    int n = Integer.parseInt(neighbor.trim());
                    graph.addEdge(vertex, n);
                }
            }
        }
        return graph;
    }
}