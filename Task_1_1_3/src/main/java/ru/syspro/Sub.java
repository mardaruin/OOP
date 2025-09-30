package ru.syspro;

import java.util.*;

public class Sub extends BinaryOperation {

    public Sub(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    char getOperator() {
        return '-';
    }

    @Override
    int apply(int a, int b) {
        return a - b;
    }

    @Override
    public Expression derivative(String variable) {
        return new Sub(
                left.derivative(variable),
                right.derivative(variable)
        );
    }

}