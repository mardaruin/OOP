package ru.syspro;

import java.util.Map;
import java.util.HashMap;

/**
 * Arithmetic expression.
 *
 */

public abstract class Expression {
    /**
     * Outputs the string representation of this expression.
     */
    public abstract void print();

    /**
     * Evaluates the value of the expression using variable assignments from a string.
     *
     * @param assignmentString String specifying variable assignments in format 'var=value;...'
     *                         Example: "x=1;y=2;"
     * @return The evaluated result of the expression
     * @throws IllegalArgumentException If invalid input is provided or parsing fails
     */
    public final int eval(String assignmentString) throws IllegalArgumentException {
        Map<String, Integer> variables = parseAssignments(assignmentString);
        return eval(variables);
    }

    /**
     * Parses variable assignments from a string into a map.
     *
     * @param assignmentString Input string containing variable assignments
     * @return Map mapping variable names to their assigned integer values
     * @throws IllegalArgumentException If the input string cannot be parsed correctly
     */
    private static Map<String, Integer> parseAssignments(String assignmentString) {
        Map<String, Integer> variables = new HashMap<>();

        String[] assignments = assignmentString.split(";");

        for (String assign : assignments) {
            if (!assign.isEmpty()) {
                String[] parts = assign.trim().split("=");

                if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                    throw new IllegalArgumentException("Invalid assignment syntax");
                }

                try {
                    int value = Integer.parseInt(parts[1]);

                    variables.put(parts[0], value);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Non-integer value " +
                            "found in assignment: '" + assign + "'");
                }
            }
        }

        return variables;
    }

    /**
     * Protected helper method performing the core evaluation logic.
     *
     * @param variables Variable mappings passed down from public methods
     * @return Result of evaluating the expression
     */
    protected abstract int eval(Map<String, Integer> variables);

    /**
     * Differentiates the expression with respect to a specified variable.
     *
     * @param variable Name of the variable used for differentiation
     * @return New expression representing the derivative
     */
    public abstract Expression derivative(String variable);

    /**
     * Checks if two objects are equal based on structure and content.
     *
     * @param obj Object to compare against
     * @return True if both objects represent equivalent expressions
     */
    public abstract boolean equals(Object obj);
}