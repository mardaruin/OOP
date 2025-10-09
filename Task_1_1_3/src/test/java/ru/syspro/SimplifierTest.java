package ru.syspro;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Simplifier functionality.
 */

public class SimplifierTest {

    @Test
    void testMultiplyByZero() {
        Expression expr = new Mul(new Number(5), new Number(0));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(new Number(0), simplified);
    }

    @Test
    void testMultiplyByOne() {
        Expression expr = new Mul(new Number(5), new Number(1));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(new Number(5), simplified);
    }

    @Test
    void testSubtractSameOperands() {
        Expression expr = new Sub(new Number(5), new Number(5));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(new Number(0), simplified);
    }

    @Test
    void testSimpleVariableExpression() {
        Expression expr = new Add(new Number(3), new Variable("x"));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(expr, simplified);
    }


    @Test
    void testIncompleteSimplification() {
        Expression expr = new Add(new Variable("x"), new Number(5));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(expr, simplified);
    }


    @Test
    void testFullyEvaluatableExpression() {
        Expression expr = new Add(new Number(3), new Number(5));
        Expression simplified = Simplifier.fullSimplify(expr);
        assertEquals(new Number(8), simplified);
    }

    @Test
    void testStructurePreservation() {
        Expression expr = new Add(new Variable("x"), new Variable("y"));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(expr, simplified);
    }


    @Test
    void testOrderRetention() {
        Expression expr = new Add(new Variable("x"), new Number(5));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(expr, simplified);

    }

    @Test
    void testNotAffectedByComplexOperations() {
        Expression expr = new Add(new Mul(new Number(3), new Variable("x")), new Number(5));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(expr, simplified);
    }


    @Test
    void testUnsimplifiableExpression() {
        Expression expr = new Add(new Variable("x"), new Variable("y"));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(expr, simplified);
    }



    @Test
    void testFullEvaluationOfConstantExpressions() {
        Expression expr = new Mul(new Number(3), new Number(4));
        Expression result = Simplifier.fullSimplify(expr);
        assertEquals(new Number(12), result);
    }


    @Test
    void testNestedMultiplicationAndAddition() {
        Expression nestedExpr = new Add(
                new Mul(new Number(2), new Variable("a")),
                new Mul(new Number(3), new Variable("b"))
        );
        Expression simplified = Simplifier.simplify(nestedExpr);
        assertEquals(nestedExpr, simplified);
    }


    @Test
    void testSimplifyLargeConstantAdditions() {
        Expression largeSum = new Add(new Number(1000), new Number(2000));
        Expression simplified = Simplifier.fullSimplify(largeSum);
        assertEquals(new Number(3000), simplified);
    }

    @Test
    void testNonReducibleExpression() {
        Expression complexExpr = new Add(new Mul(new Number(2), new Variable("x")), new Number(3));
        Expression simplified = Simplifier.simplify(complexExpr);
        assertEquals(complexExpr, simplified);
    }

    @Test
    void testSubtractionFromVariable() {
        Expression subtractionExpr = new Sub(new Variable("x"), new Number(5));
        Expression simplified = Simplifier.simplify(subtractionExpr);
        assertEquals(subtractionExpr, simplified);
    }


    @Test
    void testEmptyExpressionHandling() throws Exception {
        Expression emptyExpr = null;
        try {
            Simplifier.simplify(emptyExpr);
        } catch (RuntimeException ex) {
            assertEquals("Unsupported expression type", ex.getMessage());
        }
    }

    @Test
    void testEvaluationErrorOnUnknownVariable() {
        Expression unknownVarExpr = new Add(new Variable("z"), new Number(5));
        try {
            Simplifier.fullyEvaluateIfPossible(unknownVarExpr);
        } catch (IllegalArgumentException ignored) {
            // Empty purposely
        }
    }

}