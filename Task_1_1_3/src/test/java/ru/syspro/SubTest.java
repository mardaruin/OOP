package ru.syspro;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SubTest {

    @Test
    void testGetOperator() {
        Sub sub = new Sub(new Number(6), new Number(12));
        assertEquals('-', sub.getOperator());
    }

    @Test
    void testApply() {
        Sub sub = new Sub(new Number(12), new Number(6));
        assertEquals(6, sub.apply(12, 6));
    }

    @Test
    void testDerivative() {
        Sub sub = new Sub(new Variable("x"), new Variable("y"));
        assertEquals(new Sub(new Number(1), new Number(0)), sub.derivative("x"));
    }
}