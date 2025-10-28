package ru.syspro;


/**
 * Basic class containing grade logic.
 *
 */
public class Grade {

    enum GradeType {
        ОТЛИЧНО(5),
        ХОРОШО(4),
        УДОВЛЕТВОРИТЕЛЬНО(3),
        НЕУДОВЛЕТВОРИТЕЛЬНО(2);

        private final int numericValue;

        GradeType(int numericValue) {
            this.numericValue = numericValue;
        }

        public int getNumericValue() {
            return numericValue;
        }
    }

    /**
     * subjectName.
     *
     */
    protected final String subjectName;

    /**
     * type.
     *
     */
    protected final GradeType type;

    /**
     * sessionNumber.
     *
     */
    private final int sessionNumber;

    /**
     * assessmentForm.
     *
     */
    private final FormOfAssessment assessmentForm;

    /**
     * Creates a grade with next params:.
     *
     * @param subjectName Name name of subj
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

    /**
     * Get a grade type.
     *
     * @return type
     */
    public GradeType getType() {
        return type;
    }

    /**
     * Get a subjectname.
     *
     * @return name
     */
    public  String getSubjectName() {
        return subjectName;
    }

    /**
     * Get a session number.
     *
     * @return number
     */
    public int getSessionNumber() {
        return sessionNumber;
    }

    /**
     * Get assessement form.
     *
     * @return form
     */
    public FormOfAssessment getAssessmentForm() {
        return assessmentForm;
    }
}

