package ru.syspro;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MulTest {

    @Test
    void testGetOperator() {
        Mul mul = new Mul(new Number(6), new Number(12));
        assertEquals('*', mul.getOperator());
    }

    @Test
    void testApply() {
        Mul mul = new Mul(new Number(12), new Number(6));
        assertEquals(72, mul.apply(12, 6));
    }

    @Test
    void testDerivative() {
        Mul mul = new Mul(new Variable("x"), new Variable("y"));
        assertEquals(new Add(new Mul(new Number(1), new Variable("y")),
                new Mul(new Variable("x"), new Number(0))),
                mul.derivative("x"));
    }
}