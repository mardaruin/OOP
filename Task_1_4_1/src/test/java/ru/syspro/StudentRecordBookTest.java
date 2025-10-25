package ru.syspro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for StudentRecordBook.
 *
 */
public class StudentRecordBookTest {

    @Test
    public void testCalculateAverageScore() {
        StudentRecordBook recordBook = new StudentRecordBook("бюджет");
        recordBook.addExam("Math", "отлично", 1);
        recordBook.addExam("Physics", "хорошо", 1);
        recordBook.addDiffZachet("Programming", "отлично", 2);
        recordBook.addDiffZachet("Algorithms", "хорошо", 2);

        double averageScore = recordBook.calculateAverageScore();
        assertEquals(4.5, averageScore, 0.01);
    }

    @Test
    public void testCanTransferToBudget() {
        StudentRecordBook recordBook = new StudentRecordBook("платное");
        recordBook.addExam("Math", "отлично", 1);
        recordBook.addExam("Physics", "хорошо", 1);
        recordBook.addDiffZachet("Programming", "отлично", 2);
        recordBook.addDiffZachet("Algorithms", "хорошо", 2);

        boolean canTransfer = recordBook.canTransferToBudget();
        assertTrue(canTransfer);
    }

    @Test
    public void testCanNotTransferToBudgetFromBudget() {
        StudentRecordBook recordBook = new StudentRecordBook("бюджет");
        recordBook.addExam("Math", "отлично", 1);
        recordBook.addExam("Physics", "отлично", 1);
        recordBook.addDiffZachet("Programming", "отлично", 2);
        recordBook.addDiffZachet("Algorithms", "хорошо", 2);

        boolean canTransfer = recordBook.canTransferToBudget();
        assertFalse(canTransfer);
    }

    @Test
    public void testCanNotTransferToBudget() {
        StudentRecordBook recordBook = new StudentRecordBook("платное");
        recordBook.addExam("Math", "отлично", 1);
        recordBook.addExam("Physics", "удовлетворительно", 1);
        recordBook.addDiffZachet("Programming", "отлично", 2);
        recordBook.addDiffZachet("Algorithms", "хорошо", 2);

        boolean canTransfer = recordBook.canTransferToBudget();
        assertFalse(canTransfer);
    }

    @Test
    public void testCanGetRedDiploma() {
        StudentRecordBook recordBook = new StudentRecordBook("бюджет");
        recordBook.addExam("Math", "отлично", 1);
        recordBook.addExam("Physics", "отлично", 1);
        recordBook.addDiffZachet("Programming", "отлично", 2);
        recordBook.addDiffZachet("Algorithms", "отлично", 2);

        boolean canGetRedDiploma = recordBook.canGetRedDiploma();
        assertTrue(canGetRedDiploma);
    }

    @Test
    public void testCanGetRedDiplomaWithSatisfactory() {
        StudentRecordBook recordBook = new StudentRecordBook("бюджет");
        recordBook.addExam("Math", "отлично", 1);
        recordBook.addExam("Physics", "удовлетворительно", 1);
        recordBook.addDiffZachet("Programming", "отлично", 2);
        recordBook.addDiffZachet("Algorithms", "хорошо", 2);

        boolean canGetRedDiploma = recordBook.canGetRedDiploma();
        assertFalse(canGetRedDiploma);
    }

    @Test
    public void testEligibleForScholarshipIncrease() {
        StudentRecordBook recordBook = new StudentRecordBook("бюджет");
        recordBook.addExam("Math", "отлично", 1);
        recordBook.addExam("Physics", "отлично", 1);
        recordBook.addDiffZachet("Programming", "отлично", 2);
        recordBook.addDiffZachet("Algorithms", "отлично", 2);

        boolean eligibleForScholarshipIncrease = recordBook.eligibleForScholarshipIncrease();
        assertTrue(eligibleForScholarshipIncrease);
    }

}