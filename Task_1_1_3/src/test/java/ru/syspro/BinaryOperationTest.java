package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BinaryOperationTest {

    private Expression number10;
    private Expression number5;
    private BinaryOperation add;
    private BinaryOperation mul;

    @BeforeEach
    void setUp() {
        number10 = new Number(10);
        number5 = new Number(5);
        add = new Add(number10, number5);
        mul = new Mul(number10, number5);
    }

    @Test
    void testToStringForAdd() {
        assertEquals("(10 + 5)", add.toString());
    }

    @Test
    void testToStringForMul() {
        assertEquals("(10 * 5)", mul.toString());
    }

    @Test
    void testEvalForAdd() {
        Map<String, Integer> variables = new HashMap<>(); // Переменные не используются, т.к. это числа
        assertEquals(15, add.eval(variables)); // Проверяем операцию сложения
    }

    @Test
    void testEvalForMultiplication() {
        Map<String, Integer> variables = new HashMap<>();
        assertEquals(50, mul.eval(variables));
    }

    @Test
    void testEquals() {
        BinaryOperation sameAdd= new Add(new Number(10), new Number(5));
        assertTrue(add.equals(sameAdd));
    }

    @Test
    void testDerivative() {
        Expression derivativeOfAdd = add.derivative("x");
        assertEquals(new Add(new Number(0), new Number(0)), derivativeOfAdd);
    }

}