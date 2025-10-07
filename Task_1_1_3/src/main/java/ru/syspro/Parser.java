package ru.syspro;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;

/**
 * This class provides functionality to parse mathematical expressions represented as strings
 * and convert them into appropriate instances of the Expression hierarchy.
 * It supports basic operations like addition (+), subtraction (-), multiplication (*),
 * and division (/), along with parentheses for grouping subexpressions.
 */
public class Parser {

    private final String exprStr;
    private int pos = 0;

    public Parser(String exprStr) {
        this.exprStr = exprStr.replaceAll("\\s+", "");
    }

    public Expression parse() {
        return parseExpression();
    }

    /**
     * Parse simple expression.
     * Working with low priority operators(+, -).
     * @return expr after parsing.
     */

    private Expression parseExpression() {
        Expression result = parseTerm();

        while (pos < exprStr.length()) {
            char currChar = exprStr.charAt(pos);

            if (currChar == '+' || currChar == '-') {
                pos++;
                Expression nextTerm = parseTerm();

                if (currChar == '+') {
                    result = new Add(result, nextTerm);
                } else {
                    result = new Sub(result, nextTerm);
                }
            } else break;
        }

        return result;
    }

    /**
     * Parsing one simple expression.
     * Working with hidh priority operators (*, /).
     * @return expr after parsing.
     */
    private Expression parseTerm() {
        Expression result = parseFactor();

        while (pos < exprStr.length()) {
            char currChar = exprStr.charAt(pos);

            if (currChar == '*' || currChar == '/') {
                pos++;
                Expression nextFactor = parseFactor();

                if (currChar == '*') {
                    result = new Mul(result, nextFactor);
                } else {
                    result = new Div(result, nextFactor);
                }
            } else break;
        }

        return result;
    }

    /**
     * Parsing simple element (number, variable
     * or expression in ()).
     * @return expression after parsing.
     */
    private Expression parseFactor() {
        if (pos >= exprStr.length()) {
            throw new RuntimeException("Unexpected end of expression");
        }

        char currChar = exprStr.charAt(pos++);

        if (currChar == '(') {
            Expression innerExpr = parseExpression();

            if (pos >= exprStr.length() || exprStr.charAt(pos++) != ')') {
                throw new RuntimeException("Missing closing parenthesis");
            }
            return innerExpr;
        } else if ('0' <= currChar && currChar <= '9') {
            return parseNumber(currChar);
        } else if ((currChar >= 'a' && currChar <= 'z') ||
                (currChar >= 'A' && currChar <= 'Z')) {
            return new Variable(Character.toString(currChar));
        } else {
            throw new RuntimeException("Invalid character at position " + (pos - 1));
        }
    }

    /**
     * Parsing number from startDigit.
     * @param startDigit first digit of the number.
     * @return parsed expression (number).
     */
    private Expression parseNumber(char startDigit) {
        StringBuilder numBuilder = new StringBuilder().append(startDigit);

        while (pos < exprStr.length() &&
                Character.isDigit(exprStr.charAt(pos))) {
            numBuilder.append(exprStr.charAt(pos++));
        }

        return new Number(Integer.parseInt(numBuilder.toString()));
    }

//    /**
//     * Parses a string representation of an expression and
//     * returns a corresponding instance of Expression.
//     *
//     * @param exprStr The string containing the expression to be parsed
//     * @return An instance of Expression corresponding to the provided string
//     * @throws IllegalArgumentException If the input string
//     * is malformed or contains unsupported constructs
//     */
//    public static Expression parse(String exprStr) {
//        exprStr = exprStr.trim();
//
//        if (exprStr.startsWith('(') && exprStr.endsWith(')')) {
//            exprStr = exprStr.substring(1, exprStr.length() - 1).trim();
//        }
//
//        try {
//            return new Number(Integer.parseInt(exprStr));
//        } catch (NumberFormatException ignored) {
//            // Ignored intentionally: we're expecting this might happen in certain cases
//        }
//
//        if (exprStr.matches('[a-zA-Z]')) {
//            return new Variable(Character.toString(exprStr.charAt(0)));
//        }
//
//        List<Character> operators = Arrays.asList('+', '-', '*', '/');
//        char op = findOperator(exprStr, operators);
//
//        if (op != '\0') {
//            String leftExpr = exprStr.substring(0, exprStr.indexOf(op)).trim();
//            String rightExpr = exprStr.substring(exprStr.indexOf(op) + 1).trim();
//
//            Expression left = parse(leftExpr);
//            Expression right = parse(rightExpr);
//
//            switch (op) {
//                case '+': return new Add(left, right);
//                case '-': return new Sub(left, right);
//                case '*': return new Mul(left, right);
//                case '/': return new Div(left, right);
//                default: throw new RuntimeException('Unknown operator: ' + op);
//            }
//        }
//
//        throw new RuntimeException('Unable to parse expression: ' + exprStr);
//    }
//
//    /**
//     * Finds the first occurrence of one of the given operators that appears outside of parentheses.
//     *
//     * @param str       The string where we search for operators
//     * @param operators A list of characters representing supported operators
//     * @return The first encountered operator or '\0' if no such operator exists
//     */
//    private static char findOperator(String str, List<Character> operators) {
//        Deque<Integer> stack = new ArrayDeque<>();
//        for (int i = 0; i < str.length(); ++i) {
//            char ch = str.charAt(i);
//            if (ch == '(') {
//                stack.push(i);
//            } else if (ch == ')') {
//                stack.pop();
//            } else if (stack.isEmpty() && operators.contains(ch)) {
//                return ch;
//            }
//        }
//        return '\0';
//    }
}