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
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.БЮДЖЕТ);
        recordBook.addGrade("Math", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Physics", Grade.GradeType.ХОРОШО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ХОРОШО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);

        double averageScore = recordBook.calculateAverageScore();
        assertEquals(4.5, averageScore, 0.01);
    }

    @Test
    public void testCanTransferToBudget() {
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.ПЛАТНОЕ);
        recordBook.addGrade("Math", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Physics", Grade.GradeType.ХОРОШО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ХОРОШО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);

        boolean canTransfer = recordBook.canTransferToBudget();
        assertTrue(canTransfer);
        canTransfer = recordBook.canTransferToBudget();
        assertFalse(canTransfer);
    }

    @Test
    public void testCanNotTransferToBudgetFromBudget() {
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.БЮДЖЕТ);
        recordBook.addGrade("Math", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Physics", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ХОРОШО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);

        boolean canTransfer = recordBook.canTransferToBudget();
        assertFalse(canTransfer);
    }

    @Test
    public void testCanNotTransferToBudget() {
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.ПЛАТНОЕ);
        recordBook.addGrade("Math", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Physics", Grade.GradeType.УДОВЛЕТВОРИТЕЛЬНО, 1,
                FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ХОРОШО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);

        boolean canTransfer = recordBook.canTransferToBudget();
        assertFalse(canTransfer);
    }

    @Test
    public void testCanGetRedDiploma() {
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.БЮДЖЕТ);
        recordBook.addGrade("Math", Grade.GradeType.ОТЛИЧНО, 1,
                FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Physics", Grade.GradeType.ОТЛИЧНО, 1,
                FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.КВАЛИФИКАЦИОННАЯ_РАБОТА);

        boolean canGetRedDiploma = recordBook.canGetRedDiploma();
        assertTrue(canGetRedDiploma);
    }

    @Test
    public void testCanGetRedDiplomaWithSatisfactory() {
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.БЮДЖЕТ);
        recordBook.addGrade("Math", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Physics", Grade.GradeType.УДОВЛЕТВОРИТЕЛЬНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ХОРОШО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.КВАЛИФИКАЦИОННАЯ_РАБОТА);

        boolean canGetRedDiploma = recordBook.canGetRedDiploma();
        assertFalse(canGetRedDiploma);
    }

    @Test
    public void testCanNotGetRedDiplomaQualificationWork() {
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.БЮДЖЕТ);
        recordBook.addGrade("Math", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Physics", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ХОРОШО, 2,
                FormOfAssessment.КВАЛИФИКАЦИОННАЯ_РАБОТА);

        boolean canGetRedDiploma = recordBook.canGetRedDiploma();
        assertFalse(canGetRedDiploma);
    }

    @Test
    public void testCanGetRedDiplomaWithoutQualificationWork() {
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.БЮДЖЕТ);
        recordBook.addGrade("Math", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Physics", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);

        boolean canGetRedDiploma = recordBook.canGetRedDiploma();
        assertTrue(canGetRedDiploma);
    }

    @Test
    public void testCanNotGetRedDiplomaWithoutQualificationWork() {
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.БЮДЖЕТ);
        recordBook.addGrade("Math", Grade.GradeType.ХОРОШО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Physics", Grade.GradeType.ХОРОШО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ХОРОШО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);

        boolean canGetRedDiploma = recordBook.canGetRedDiploma();
        assertFalse(canGetRedDiploma);
    }

    @Test
    public void testEligibleForScholarshipIncrease() {
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.БЮДЖЕТ);
        recordBook.addGrade("Math", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Physics", Grade.GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", Grade.GradeType.ОТЛИЧНО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);

        boolean eligibleForScholarshipIncrease = recordBook.eligibleForScholarshipIncrease();
        assertTrue(eligibleForScholarshipIncrease);
    }

}