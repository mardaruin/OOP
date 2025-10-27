package ru.syspro;


/**
 * Basic class containing diff zachet logic.
 *
 */
public class DiffZachet extends Grade {
    public DiffZachet(String subjectName, GradeType type, int sessionNumber) {
        super(subjectName, type, sessionNumber, FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
    }
}