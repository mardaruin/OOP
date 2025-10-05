package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(5, expr.eval(variables));
    }

    @Test
    public void testParseVariable() {
        Expression expr = Parser.parse("x");
        assertEquals(2, expr.eval(variables));
    }

    @Test
    public void testParseAddOperation() {
        Expression expr = Parser.parse("x+y");
        assertEquals(5, expr.eval(variables));
    }

    @Test
    public void testParseSubtractOperation() {
        Expression expr = Parser.parse("z-x");
        assertEquals(2, expr.eval(variables));
    }

    @Test
    public void testParseMultiplyOperation() {
        Expression expr = Parser.parse("x*y");
        assertEquals(6, expr.eval(variables));
    }

    @Test
    public void testParseDivideOperation() {
        Expression expr = Parser.parse("z/y");
        assertEquals(1, expr.eval(variables));
    }

    @Test
    public void testNestedOperations() {
        Expression expr = Parser.parse("x+(y*z)");
        assertEquals(14, expr.eval(variables));
    }


    @Test
    public void testNegativeNumbers() {
        Expression expr = Parser.parse("0-x");
        assertEquals(-2, expr.eval(variables));
    }

    @Test
    public void testLargeNumbers() {
        Expression expr = Parser.parse("1000*x");
        assertEquals(2000, expr.eval(variables));
    }

    @Test
    public void testNoParentheses() {
        Expression expr = Parser.parse("x+y+z");
        assertEquals(9, expr.eval(variables));
    }

    @Test
    public void testWhitespaceHandling() {
        Expression expr = Parser.parse("   x  ");
        assertEquals(2, expr.eval(variables));
    }


    @Test
    public void testNegativeOutcome() {
        Expression expr = Parser.parse("0-(x+y)");
        assertEquals(-5, expr.eval(variables));
    }


    @Test
    public void testSurroundingSpaces() {
        Expression expr = Parser.parse(" ( x + y ) ");
        assertEquals(5, expr.eval(variables));
    }

    @Test
    public void testEvaluateUndefinedVariable() {
        Expression expr = Parser.parse("k");
        assertEquals(0, expr.eval(variables));
    }
}