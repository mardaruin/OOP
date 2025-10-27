package ru.syspro;


/**
 * Basic class containing exam logic.
 *
 */
public class Exam extends Grade {
    /**
     * Creates an exam with next param:
     * @param subjectName Name name of subj
     * @param type Grade type
     * @param sessionNumber session number
     */
    public Exam(String subjectName, GradeType type, int sessionNumber) {
        super(subjectName, type, sessionNumber, FormOfAssessment.ЭКЗАМЕН);
    }

}