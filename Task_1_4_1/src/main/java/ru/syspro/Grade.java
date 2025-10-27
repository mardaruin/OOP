package ru.syspro;


/**
 * Basic class containing grade logic.
 *
 */
public class Grade {
    protected final String subjectName;
    protected final GradeType type;
    private final int sessionNumber;
    private final FormOfAssessment assessmentForm;

    /**
     * Creates a grade with next param:
     * @param subject Name name of subj
     * @param type Grade type
     * @param sessionNumber session number
     * @param assessmentForm exam or diff zach or qualification work
     */
    public Grade(String subjectName, GradeType type,
                 int sessionNumber, FormOfAssessment assessmentForm) {
        this.type = type;
        this.subjectName = subjectName;
        this.sessionNumber = sessionNumber;
        this.assessmentForm = assessmentForm;
    }

    public GradeType getType() {
        return type;
    }

    public  String getSubjectName() {
        return subjectName;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }
}

