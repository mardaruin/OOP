package ru.syspro;

import java.util.*;

public class Div extends BinaryOperation {

    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    char getOperator() {
        return '/';
    }

    @Override
    int apply(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    @Override
    public Expression derivative(String variable) {
        return new Div(
                new Sub(
                        new Mul(left.derivative(variable), right),
                        new Mul(left, right.derivative(variable))
                ),
                new Mul(right, right)
        );
    }

}