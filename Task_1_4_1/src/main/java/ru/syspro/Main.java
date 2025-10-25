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
        StudentRecordBook recordBook = new StudentRecordBook("бюджет");
        recordBook.addExam("Math", "отлично", 1);
        recordBook.addExam("English", "хорошо", 1);
        recordBook.addDiffZachet("Programming", "отлично", 1);
        recordBook.addDiffZachet("Algorithms", "хорошо", 2);
        recordBook.addExam("Math", "отлично", 2);
        recordBook.addExam("Algorithms", "хорошо", 3);
        recordBook.addExam("Math", "отлично", 3);

        System.out.println("Средний балл: " + recordBook.calculateAverageScore());
        System.out.println("Можно перевестись на бюджет: " + recordBook.canTransferToBudget());
        System.out.println("Можно получить красный диплом: " + recordBook.canGetRedDiploma());
        System.out.println("Можно получить повышенную стипендию: " + recordBook.eligibleForScholarshipIncrease());

    }
}