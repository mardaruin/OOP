package ru.syspro;

import java.util.Map;
import java.util.HashMap;

/**
 * The heir of BinaryOperation class.
 * Implements logic for binary operation mul.
 *
 */

public class Mul extends BinaryOperation {

    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    char getOperator() {
        return '*';
    }

    @Override
    int apply(int a, int b) {
        return a * b;
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(
                new Mul(left.derivative(variable), right),
                new Mul(left, right.derivative(variable))
        );
    }


}