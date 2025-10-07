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
     * @return expr
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
            } else {
                break;
            }
        }

        return result;
    }

    /**
     * Parsing one simple expression.
     * Working with hidh priority operators (*, /).
     * @return expr
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
            } else {
                break;
            }
        }

        return result;
    }

    /**
     * Parsing simple element (number, variable
     * or expression in ()).
     * @return
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
        } else if ((currChar >= 'a' && currChar <= 'z')
                || (currChar >= 'A' && currChar <= 'Z')) {
            return new Variable(Character.toString(currChar));
        } else {
            throw new RuntimeException("Invalid character at position " + (pos - 1));
        }
    }

    /**
     * Parsing number from startDigit.
     * @param startDigit
     * @return
     */
    private Expression parseNumber(char startDigit) {
        StringBuilder numBuilder = new StringBuilder().append(startDigit);

        while (pos < exprStr.length()
                && Character.isDigit(exprStr.charAt(pos))) {
            numBuilder.append(exprStr.charAt(pos++));
        }

        return new Number(Integer.parseInt(numBuilder.toString()));
    }

}