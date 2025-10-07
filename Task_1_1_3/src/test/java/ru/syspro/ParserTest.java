package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for Parser functionality.
 */

public class ParserTest {

    private Map<String, Integer> variables;


    /**
     * setup for tests: creating HashMap
     * with variable's values
     */
    @BeforeEach
    public void setUp() {
        variables = new HashMap<>();
        variables.put("x", 2);
        variables.put("y", 3);
        variables.put("z", 4);
    }

    @Test
    public void testParseConstant() {
        Expression expr = new Parser("5").parse();
        assertTrue(expr instanceof Number);
        assertEquals(5, ((Number) expr).getValue());
    }

    @Test
    public void testParseVariable() {
        Expression expr = new Parser("x").parse();
        assertTrue(expr instanceof Variable);
        assertEquals("x", ((Variable) expr).getName());
    }

    @Test
    public void testParseAddOperation() {
        Expression expr = new Parser("x+y").parse();
        assertTrue(expr instanceof Add);
        assertTrue(((BinaryOperation) expr).left instanceof Variable);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseSubtractOperation() {
        Expression expr = new Parser("z-x").parse();
        assertTrue(expr instanceof Sub);
        assertTrue(((BinaryOperation) expr).left instanceof Variable);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseMultiplyOperation() {
        Expression expr = new Parser("x*y").parse();
        assertTrue(expr instanceof Mul);
        assertTrue(((BinaryOperation) expr).left instanceof Variable);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseDivideOperation() {
        Expression expr = new Parser("z/y").parse();
        assertTrue(expr instanceof Div);
        assertTrue(((BinaryOperation) expr).left instanceof Variable);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseNestedOperations() {
        Expression expr = new Parser("x+(y*z)").parse();
        assertTrue(expr instanceof Add);
        assertTrue(((BinaryOperation) expr).left instanceof Variable);
        assertTrue(((BinaryOperation) expr).right instanceof Mul);
    }

    @Test
    public void testParseParenthesizedExpressions() {
        Expression expr = new Parser("(x+y)*z").parse();
        assertTrue(expr instanceof Mul);
        assertTrue(((BinaryOperation) expr).left instanceof Add);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseNegativeNumbers() {
        Expression expr = new Parser("0-x").parse();
        assertTrue(expr instanceof Sub);
        assertTrue(((BinaryOperation) expr).left instanceof Number);
        assertTrue(((BinaryOperation) expr).right instanceof Variable);
    }

    @Test
    public void testParseWhitespaceHandling() {
        Expression expr = new Parser("  x  ").parse();
        assertTrue(expr instanceof Variable);
        assertEquals(((Variable) expr).getName(), "x");
    }

    @Test
    public void testParseUnrecognizedVariables() {
        Expression expr = new Parser("w").parse();
        assertTrue(expr instanceof Variable);
        assertEquals(((Variable) expr).getName(), "w");
    }

    @Test
    public void testParseErrorOnMalformedInput() {
        assertThrows(RuntimeException.class, () -> new Parser("x+").parse());
    }

    @Test
    public void testSimpleAdditionWithoutBrackets() {
        Expression expr = new Parser("x+y+z").parse();
        assertTrue(expr instanceof Add);
        assertEquals(9, expr.eval(variables));
    }

    @Test
    public void testSimpleSubtractionWithoutBrackets() {
        Expression expr = new Parser("z-y-x").parse();
        assertTrue(expr instanceof Sub);
        assertEquals(-1, expr.eval(variables));
    }

    @Test
    public void testMixedOperationsWithoutBrackets() {
        Expression expr = new Parser("x+y-z").parse();
        assertTrue(expr instanceof Sub);
        assertEquals(1, expr.eval(variables));
    }

    @Test
    public void testMultiplicationAndDivisionWithoutBrackets() {
        Expression expr = new Parser("z*y/x").parse();
        assertTrue(expr instanceof Div);
        assertEquals(6, expr.eval(variables));
    }

    @Test
    public void testComplexArithmeticSequence() {
        Expression expr = new Parser("x+y*z/x").parse();
        assertTrue(expr instanceof Add);
        assertEquals(8, expr.eval(variables));
    }

    @Test
    public void testMultipleOperatorsInRow() {
        Expression expr = new Parser("x+x*x").parse();
        assertTrue(expr instanceof Add);
        assertEquals(6, expr.eval(variables));
    }

    @Test
    public void testNegationInChain() {
        Expression expr = new Parser("0-(x+y)").parse();
        assertTrue(expr instanceof Sub);
        assertEquals(-5, expr.eval(variables));
    }

}