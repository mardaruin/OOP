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
     * Evaluates the value of the expression by
     * substituting given variable values.
     *
     * @param variables A map containing variable names
     *                  as keys and their corresponding integer values
     * @return The evaluated result of the expression
     * @throws ArithmeticException If an arithmetic
     *                  error occurs (such as division by zero).
     */
    public abstract int eval(Map<String, Integer> variables);

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