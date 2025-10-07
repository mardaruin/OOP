package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for Parser functionality.
 */

public class ParserTest {

    private Map<String, Integer> variables;

    @BeforeEach
    public void setUp() {
        variables = new HashMap<>();
        variables.put("x", 2);
        variables.put("y", 3);
        variables.put("z", 4);
    }

    @Test
    public void testParseConstant() {
        Expression expr = Parser.parse("5");
        assertTrue(expr instanceof Number);
        assertEquals(5, ((Number) expr).getValue());
    }

    @Test
    public void testParseVariable() {
        Expression expr = Parser.parse("x");
        assertTrue(expr instanceof Variable);
        assertEquals("x", ((Variable) expr).getName());
    }

    @Test
    public void testParseAddOperation() {
        Expression expr = Parser.parse("x+y");
        assertTrue(expr instanceof Add);
        assertTrue(((BinaryOperation) expr).left instanceof Variable);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseSubtractOperation() {
        Expression expr = Parser.parse("z-x");
        assertTrue(expr instanceof Sub);
        assertTrue(((BinaryOperation) expr).left instanceof Variable);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseMultiplyOperation() {
        Expression expr = Parser.parse("x*y");
        assertTrue(expr instanceof Mul);
        assertTrue(((BinaryOperation) expr).left instanceof Variable);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseDivideOperation() {
        Expression expr = Parser.parse("z/y");
        assertTrue(expr instanceof Div);
        assertTrue(((BinaryOperation) expr).left instanceof Variable);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseNestedOperations() {
        Expression expr = Parser.parse("x+(y*z)");
        assertTrue(expr instanceof Add);
        assertTrue(((BinaryOperation) expr).left instanceof Variable);
        assertTrue(((BinaryOperation) expr).right instanceof Mul);
    }

    @Test
    public void testParseParenthesizedExpressions() {
        Expression expr = Parser.parse("(x+y)*z");
        assertTrue(expr instanceof Mul);
        assertTrue(((BinaryOperation) expr).left instanceof Add);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseNegativeNumbers() {
        Expression expr = Parser.parse("0-x");
        assertTrue(expr instanceof Sub);
        assertTrue(((BinaryOperation) expr).left instanceof Number);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseWhitespaceHandling() {
        Expression expr = Parser.parse(" x ");
        assertTrue(expr instanceof Variable);
        assertEquals(((Variable) expr).getName(), "x");
    }

    @Test
    public void testParseUnrecognizedVariables() {
        Expression expr = Parser.parse("w");
        assertTrue(expr instanceof Variable);
        assertEquals(((Variable) expr).getName(), "w");
    }
}