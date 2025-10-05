package ru.syspro;

import java.util.*;

/**
 * This class provides functionality to parse mathematical expressions represented as strings
 * and convert them into appropriate instances of the Expression hierarchy.
 * It supports basic operations like addition (+), subtraction (-), multiplication (*),
 * and division (/), along with parentheses for grouping subexpressions.
 */
public class Parser {

    /**
     * Parses a string representation of an expression and returns a corresponding instance of Expression.
     *
     * @param exprStr The string containing the expression to be parsed
     * @return An instance of Expression corresponding to the provided string
     * @throws IllegalArgumentException If the input string is malformed or contains unsupported constructs
     */
    public static Expression parse(String exprStr) {
        exprStr = exprStr.trim();

        if (exprStr.startsWith("(") && exprStr.endsWith(")")) {
            exprStr = exprStr.substring(1, exprStr.length() - 1).trim();
        }

        try {
            return new Number(Integer.parseInt(exprStr));
        } catch (NumberFormatException ignored) {}

        if (exprStr.matches("[a-zA-Z]")) {
            return new Variable(Character.toString(exprStr.charAt(0)));
        }

        List<Character> operators = Arrays.asList('+', '-', '*', '/');
        char op = findOperator(exprStr, operators);

        if (op != '\0') {
            String leftExpr = exprStr.substring(0, exprStr.indexOf(op)).trim();
            String rightExpr = exprStr.substring(exprStr.indexOf(op) + 1).trim();

            Expression left = parse(leftExpr);
            Expression right = parse(rightExpr);

            switch (op) {
                case '+': return new Add(left, right);
                case '-': return new Sub(left, right);
                case '*': return new Mul(left, right);
                case '/': return new Div(left, right);
                default: throw new RuntimeException("Unknown operator: " + op);
            }
        }

        throw new RuntimeException("Unable to parse expression: " + exprStr);
    }

    /**
     * Finds the first occurrence of one of the given operators that appears outside of parentheses.
     *
     * @param str       The string where we search for operators
     * @param operators A list of characters representing supported operators
     * @return The first encountered operator or '\0' if no such operator exists
     */
    private static char findOperator(String str, List<Character> operators) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                stack.pop();
            } else if (stack.isEmpty() && operators.contains(ch)) {
                return ch;
            }
        }
        return '\0';
    }
}