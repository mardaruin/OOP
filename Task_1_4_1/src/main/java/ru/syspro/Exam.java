package ru.syspro;


/**
 * Basic class containing exam logic.
 *
 */
public class Exam extends Grade {
    private final int sessionNumber;

    public Exam(String subjectName, String grade, int sessionNumber) {
        super(subjectName, grade);
        this.sessionNumber = sessionNumber;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

}