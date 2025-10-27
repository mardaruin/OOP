package ru.syspro;

/**
 * Entry point for running the program
 * directly from the command-line.
 *
 */
public class Main {

    /**
     * Main entry point for launching the application.
     */
    public static void main(String[] args) {
        StudentRecordBook recordBook = new StudentRecordBook(StudingForm.БЮДЖЕТ);
        recordBook.addGrade("Math", GradeType.ОТЛИЧНО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("English", GradeType.ХОРОШО, 1, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Programming", GradeType.ОТЛИЧНО, 1,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Algorithms", GradeType.ХОРОШО, 2,
                FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
        recordBook.addGrade("Math", GradeType.ОТЛИЧНО, 2, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Algorithms", GradeType.ХОРОШО, 3, FormOfAssessment.ЭКЗАМЕН);
        recordBook.addGrade("Math", GradeType.ОТЛИЧНО, 3, FormOfAssessment.ЭКЗАМЕН);

        System.out.println("Средний балл: " + recordBook.calculateAverageScore());
        System.out.println("Можно перевестись на бюджет: " + recordBook.canTransferToBudget());
        System.out.println("Можно получить красный диплом: " + recordBook.canGetRedDiploma());
        System.out.println("Можно получить повышенную стипендию: "
                + recordBook.eligibleForScholarshipIncrease());

    }
}