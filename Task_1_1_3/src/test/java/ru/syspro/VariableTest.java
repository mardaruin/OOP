package ru.syspro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class VariableTest {

    private Variable variable;
    private Map<String, Integer> variables;

    @BeforeEach
    void setup() {
        variable = new Variable("x");
        variables = new HashMap<>();
    }

    @Test
    void testEvalWhenExists() {
        variables.put("x", 10);
        assertEquals(10, variable.eval(variables));
    }

    @Test
    void testEvalDoesntWhenExist() {
        assertEquals(0, variable.eval(variables));
    }

    @Test
    void testDerivativeWhenMatchingVar() {
        assertEquals(new Number(1), variable.derivative("x"));
    }

    @Test
    void testDerivativeWhenNotMatchingVar() {
        assertEquals(new Number(0), variable.derivative("y"));
    }

    @Test
    void testToString() {
        assertEquals("x", variable.toString());
    }

    @Test
    void testEqualsReturnTrue() {
        assertTrue(variable.equals(variable));
    }

    @Test
    void testEqualsReturnFalse() {
        Variable anotherVariable = new Variable("y");
        assertFalse(variable.equals(anotherVariable));
    }

}