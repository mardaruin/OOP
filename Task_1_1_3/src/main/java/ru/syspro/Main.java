package ru.syspro;

import java.util.Map;
import java.util.HashMap;

/**
 * Entry point for running the program directly from the command-line.
 */

public class Main {

    /**
     * Main entry point for launching the application.
     */
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));

        System.out.println("Исходное выражение:");
        e.print();
        System.out.println("\nСтроковое представление: " + e.toString());

        Map<String, Integer> variables = new HashMap<>();
        variables.put("x", 5);
        int result = e.eval(variables);
        System.out.println("\nОценка выражения при x = 5: " + result);

        Expression de = e.derivative("x");
        System.out.println("\nПроизводная по x:");
        de.print();
        System.out.println("\nСтроковое представление производной: " + de.toString());

        int derResult = de.eval(variables);
        System.out.println("\nОценка производной при x = 5: " + derResult);
    }
}