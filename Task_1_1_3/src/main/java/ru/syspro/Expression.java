package ru.syspro;

import java.util.*;

abstract class Expression {
    abstract void print();
    abstract int eval(Map<String, Integer> variables);
    abstract Expression derivative(String variable);
    public abstract boolean equals(Object obj);
}