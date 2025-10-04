package ru.syspro;

import java.util.Map;
import java.util.HashMap;

/**
 * Abstract class BinaryOperation that
 * contains basic methods for all binary operations.
 *
 */

abstract class BinaryOperation extends Expression {
    protected final Expression left;
    protected final Expression right;

    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    abstract char getOperator();

    abstract int apply(int a, int b);

    @Override
    public void print() {
        System.out.println(toString());
    }

    @Override
    public int eval(Map<String, Integer> variables) {
        return apply(left.eval(variables), right.eval(variables));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BinaryOperation)) {
            return false;
        }
        BinaryOperation other = (BinaryOperation) obj;
        return left.equals(other.left) && right.equals(other.right);
    }

    @Override
    public String toString() {
        return '(' + left.toString() + ' ' + getOperator() + ' ' + right.toString() + ')';
    }
}