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

    public Grade(String subjectName, GradeType type, int sessionNumber, FormOfAssessment assessmentForm) {
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

enum GradeType {
    ОТЛИЧНО,
    ХОРОШО,
    УДОВЛЕТВОРИТЕЛЬНО,
    НЕУДОВЛЕТВОРИТЕЛЬНО,
    КВАЛИФИКАЦИОННАЯ_РАБОТА_ОТЛИЧНО;

    public int getNumericValue() {
        switch(this) {
            case ОТЛИЧНО:
            case КВАЛИФИКАЦИОННАЯ_РАБОТА_ОТЛИЧНО:
                return 5;
            case ХОРОШО:
                return 4;
            case УДОВЛЕТВОРИТЕЛЬНО:
                return 3;
            default:
                return 2;
        }
    }
}

enum FormOfAssessment {
    ЭКЗАМЕН,
    ДИФФЕРЕНЦИРОВАННЫЙ_ЗАЧЁТ,
    КВАЛИФИКАЦИОННАЯ_РАБОТА
}