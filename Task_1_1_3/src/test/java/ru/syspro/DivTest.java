package ru.syspro;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DivTest {

    @Test
    void testGetOperator() {
        Div div = new Div(new Number(6), new Number(12));
        assertEquals('/', div.getOperator());
    }

    @Test
    void testApply() {
        Div div = new Div(new Number(12), new Number(6));
        assertEquals(2, div.apply(12, 6));
    }

    @Test
    void testDerivative() {
        Div div = new Div(new Variable("x"), new Variable("y"));
        assertEquals(new Div(new Sub(new Mul(new Number(1), new Variable("y")),
                new Mul(new Variable("x"), new Number(0))), new Mul(new Variable("y"),
                        new Variable("y"))),
                div.derivative("x"));
    }
}