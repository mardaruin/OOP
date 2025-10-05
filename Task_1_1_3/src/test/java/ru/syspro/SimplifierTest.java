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
        assertEquals(expr, simplified); // Порядок сохранён
    }

    @Test
    void testNotAffectedByComplexOperations() {
        Expression expr = new Add(new Mul(new Number(3), new Variable("x")), new Number(5));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(expr, simplified); // Нет изменений
    }


    @Test
    void testUnsimplifiableExpression() {
        Expression expr = new Add(new Variable("x"), new Variable("y"));
        Expression simplified = Simplifier.simplify(expr);
        assertEquals(expr, simplified); // Никаких изменений
    }




}