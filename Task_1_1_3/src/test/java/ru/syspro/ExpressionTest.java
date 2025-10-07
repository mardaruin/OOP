package ru.syspro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionTest {

    @Test
    public void testEvalCorrectAssignment() {
        Expression expr = new Add(new Variable("x"), new Variable("y"));
        String assignmentString = "x=2;y=3;";
        int result = expr.eval(assignmentString);
        assertEquals(result, 5);
    }

    @Test
    public void testInvalidAssignmentSyntax() {
        String badAssignmentString = "x 2";
        Expression expr = new Variable("x");
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> expr.eval(badAssignmentString));
        assertTrue(exception.getMessage().contains("Invalid assignment syntax"));
    }

    @Test
    public void testNonIntegerValueInAssignment() {
        String badAssignmentString = "x=a;";
        Expression expr = new Variable("x");
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> expr.eval(badAssignmentString));
        assertTrue(exception.getMessage().contains("Non-integer value found"));
    }
}