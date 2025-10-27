package ru.syspro;


/**
 * Basic class containing exam logic.
 *
 */
public class Exam extends Grade {
    public Exam(String subjectName, GradeType type, int sessionNumber) {
        super(subjectName, type, sessionNumber, FormOfAssessment.ЭКЗАМЕН);
    }

}