package ru.syspro;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AddTest {

    @Test
    void testGetOperator() {
        Add add = new Add(new Number(6), new Number(12));
        assertEquals('+', add.getOperator());
    }

    @Test
    void testApply() {
        Add add = new Add(new Number(6), new Number(12));
        assertEquals(18, add.apply(6, 12));
    }

    @Test
    void testDerivative() {
        Add add = new Add(new Variable("x"), new Variable("y"));
        assertEquals(new Add(new Number(1), new Number(0)), add.derivative("x"));
    }
}