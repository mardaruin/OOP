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
        var expected = new Number(5);
        assertEquals(expected, expr);
    }

    @Test
    public void testParseVariable() {
        Expression expr = new Parser("x").parse();
        var expected = new Variable("x");
        assertEquals(expected, expr);
    }

    @Test
    public void testParseAddOperation() {
        Expression expr = new Parser("x+y").parse();
        var expected = new Add(new Variable("x"), new Variable("y"));
        assertEquals(expected, expr);
    }

    @Test
    public void testParseSubtractOperation() {
        Expression expr = new Parser("z-x").parse();
        var expected = new Sub(new Variable("z"), new Variable("x"));
        assertEquals(expected, expr);
    }

    @Test
    public void testParseMultiplyOperation() {
        Expression expr = new Parser("x*y").parse();
        var expected = new Mul(new Variable("x"), new Variable("y"));
        assertEquals(expected, expr);
    }

    @Test
    public void testParseDivideOperation() {
        Expression expr = new Parser("z/y").parse();
        var expected = new Div(new Variable("z"), new Variable("y"));
        assertEquals(expected, expr);
    }

    @Test
    public void testParseNestedOperations() {
        Expression expr = new Parser("x+(y*z)").parse();
        var expected = new Add(new Variable("x"), new Mul(new Variable("y"), new Variable("z")));
        assertEquals(expected, expr);
    }

    @Test
    public void testParseParenthesizedExpressions() {
        Expression expr = new Parser("(x+y)*z").parse();
        var expected = new Mul(new Add(new Variable("x"), new Variable("y")), new Variable("z"));
        assertEquals(expected, expr);
    }

    @Test
    public void testParseNegativeNumbers() {
        Expression expr = new Parser("0-x").parse();
        var expected = new Sub(new Number(0), new Variable("x"));
        assertEquals(expected, expr);
    }

    @Test
    public void testParseWhitespaceHandling() {
        Expression expr = new Parser("  x  ").parse();
        var expected = new Variable("x");
        assertEquals(expected, expr);
    }

    @Test
    public void testParseUnrecognizedVariables() {
        Expression expr = new Parser("w").parse();
        var expected = new Variable("w");
        assertEquals(expected, expr);
    }

    @Test
    public void testParseErrorOnMalformedInput() {
        assertThrows(RuntimeException.class, () -> new Parser("x+").parse());
    }

    @Test
    public void testSimpleAdditionWithoutBrackets() {
        Expression expr = new Parser("x+y+z").parse();
        var expected = new Add(new Add(new Variable("x"), new Variable("y")), new Variable("z"));
        assertEquals(expected, expr);
    }

    @Test
    public void testSimpleSubtractionWithoutBrackets() {
        Expression expr = new Parser("z-y-x").parse();
        var expected = new Sub(new Sub(new Variable("z"), new Variable("y")), new Variable("x"));
        assertEquals(expected, expr);
    }

    @Test
    public void testMixedOperationsWithoutBrackets() {
        Expression expr = new Parser("x+y-z").parse();
        var expected = new Sub(new Add(new Variable("x"), new Variable("y")), new Variable("z"));
        assertEquals(expected, expr);
    }

    @Test
    public void testMultiplicationAndDivisionWithoutBrackets() {
        Expression expr = new Parser("z*y/x").parse();
        var expected = new Div(new Mul(new Variable("z"), new Variable("y")), new Variable("x"));
        assertEquals(expected, expr);
    }

    @Test
    public void testComplexArithmeticSequence() {
        Expression expr = new Parser("x+y*z/x").parse();
        var expected = new Add(new Variable("x"), new Div(new Mul(new Variable("y"), new Variable("z")), new Variable("x")));
        assertEquals(expected, expr);
    }

    @Test
    public void testMultipleOperatorsInRow() {
        Expression expr = new Parser("x+x*x").parse();
        var expected = new Add(new Variable("x"), new Mul(new Variable("x"), new Variable("x")));
        assertEquals(expected, expr);
    }

    @Test
    public void testNegationInChain() {
        Expression expr = new Parser("0-(x+y)").parse();
        var expected = new Sub(new Number(0), new Add(new Variable("x"), new Variable("y")));
        assertEquals(expected, expr);
    }

}