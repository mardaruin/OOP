package ru.syspro;

import java.util.Map;
import java.util.HashMap;

/**
 * The heir of Expression class.
 * Implements variable logic for numbers.
 *
 */

public class Number extends Expression {
    private final int value;

    public int getValue() {
        return value;
    }

    public Number(int value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }

    @Override
    public int eval(Map<String, Integer> variables) {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Number)) {
            return false;
        }
        return ((Number) obj).value == this.value;
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}