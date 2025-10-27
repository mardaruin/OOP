package ru.syspro;


/**
 * Basic class containing diff zachet logic.
 *
 */
public class DiffZachet extends Grade {
    /**
     * Creates a dyff zachet with next param:
     * @param subjectName Name name of subj
     * @param type Grade type
     * @param sessionNumber session number
     */
    public DiffZachet(String subjectName, GradeType type, int sessionNumber) {
        super(subjectName, type, sessionNumber, FormOfAssessment.ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ);
    }
}