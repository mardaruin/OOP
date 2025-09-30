package ru.syspro;

import java.util.Map;
import java.util.HashMap;

/**
 * The heir of Expression class.
 * Implements variable logic for expressions.
 *
 */

public class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.print(name);
    }

    @Override
    public int eval(Map<String, Integer> variables) {
        return variables.getOrDefault(name, 0);
    }

    @Override
    public Expression derivative(String variable) {
        if (variable.equals(name)) {
            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)) return false;
        return ((Variable) obj).name.equals(this.name);
    }

    @Override
    public String toString() {
        return name;
    }
}