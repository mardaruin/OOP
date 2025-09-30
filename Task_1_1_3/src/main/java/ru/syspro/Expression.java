package ru.syspro;

import java.util.Map;
import java.util.HashMap;

/**
 * Abstract class Expression that
 * contains basic methods for descendants.
 *
 */

abstract class Expression {
    abstract void print();

    abstract int eval(Map<String, Integer> variables);

    abstract Expression derivative(String variable);

    public abstract boolean equals(Object obj);
}