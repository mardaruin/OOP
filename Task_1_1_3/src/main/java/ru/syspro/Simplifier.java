package ru.syspro;

import java.util.Map;
import java.util.HashMap;


/**
 * Simplify expression as much as possible.
 *
 */

public class Simplifier {

    public static Expression simplify(Expression expr) {
        if (expr instanceof Mul) {
            Mul m = (Mul) expr;
            Expression left = simplify(m.left);
            Expression right = simplify(m.right);
            if (left instanceof Number && ((Number) left).getValue() == 0 ||
                    right instanceof Number && ((Number) right).getValue() == 0) {
                return new Number(0);
            }
            if (right instanceof Number && ((Number) right).getValue() == 1) {
                return left;
            }
            if (left instanceof Number && ((Number) left).getValue() == 1) {
                return right;
            }
            return new Mul(left, right);
        } else if (expr instanceof Sub) {
            Sub s = (Sub) expr;
            Expression left = simplify(s.left);
            Expression right = simplify(s.right);
            if (left.equals(right)) {
                return new Number(0);
            }
            return new Sub(left, right);
        } else if (expr instanceof Add || expr instanceof Div) {
            return expr;
        } else if (expr instanceof Number) {
            return expr;
        } else if (expr instanceof Variable) {
            return expr;
        } else {
            throw new RuntimeException("Unsupported expression type");
        }
    }


    public static Expression fullyEvaluateIfPossible(Expression expr) {
        try {
            Map<String, Integer> vars = new HashMap<>();
            return new Number(expr.eval(vars));
        } catch (IllegalArgumentException e) {
            return expr;
        }
    }

    public static Expression fullSimplify(Expression expr) {
        Expression simplifiedExpr = simplify(expr);
        return fullyEvaluateIfPossible(simplifiedExpr);
    }


}