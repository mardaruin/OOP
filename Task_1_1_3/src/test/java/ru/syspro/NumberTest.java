package ru.syspro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NumberTest {

    @Test
    void testEvalReturnsCorrectValue() {
        Number num = new Number(5);
        assertEquals(5, num.eval(null));
    }

    @Test
    void testDerivativeReturnsZero() {
        Number num = new Number(5);
        assertEquals(new Number(0), num.derivative("x"));
    }

    @Test
    void testToString() {
        Number num = new Number(5);
        assertEquals("5", num.toString());
    }

    @Test
    void testEqualsReturnTrue() {
        Number num1 = new Number(5);
        Number num2 = new Number(5);
        assertTrue(num1.equals(num2));
    }

    @Test
    void testEqualsReturnFalse() {
        Number num1 = new Number(5);
        Number num2 = new Number(-5);
        assertFalse(num1.equals(num2));
    }

}