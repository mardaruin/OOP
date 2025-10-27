package ru.syspro;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Class that contains record book logic.
 *
 */
public class StudentRecordBook {

    private List<Grade> grades = new ArrayList<>();
    private boolean isPaidForm;

    /**
     * Creates a record book with
     *  paid or unpaid form of studing.
     *
     * @param form paid or unpaid form of studing
     */
    public StudentRecordBook(StudingForm form) {
        this.isPaidForm = form.getForm();
    }

    /**
     * Addes a mark on grade(exam or diff zachet).
     *
     * @param subjectname name of subject
     * @param grade gradetype
     * @param sessionNumber session number
     * @param assessmentForm exam or diff zach or qualification work
     */
    public void addGrade(String subjectname, GradeType grade,
                         int sessionNumber, FormOfAssessment assessmentForm) {
        this.grades.add(new Grade(subjectname, grade, sessionNumber, assessmentForm));
    }


    /**
     * Counts amount of requiremented grade.
     *
     * @param type type
     * @return amount of requiremented grade
     */
    public int countGrades(GradeType type) {
        return (int) grades.stream()
                .filter(grade -> grade.getType() == type)
                .count();
    }

    /**
     * Calculates average grade.
     *
     * @return average score
     */
    public double calculateAverageScore() {
        int sumOfScores = grades.stream()
                .mapToInt(grade -> grade.getType().getNumericValue())
                .sum();

        return (double) sumOfScores / grades.size();
    }


    /**
     * Checks for ability to transfer on budget.
     *
     * @return true if can be transfered, false otherwise
     */
    public boolean canTransferToBudget() {
        List<GradeType> lastTwoSessions = extractLastTwoSessions();
        if (!lastTwoSessions.contains(GradeType.УДОВЛЕТВОРИТЕЛЬНО)
                && !lastTwoSessions.contains(GradeType.НЕУДОВЛЕТВОРИТЕЛЬНО) && isPaidForm) {
            isPaidForm = false;
            return true;
        }
        return false;
    }

    /**
     * Extracts grades on last two sessions.
     * Method for canTransferToBudget().
     *
     * @return grades on last two sessions
     */
    private List<GradeType> extractLastTwoSessions() {
        Optional<Integer> maxSession = grades.stream()
                .map(Grade::getSessionNumber)
                .max(Integer::compareTo);
        if (maxSession.isPresent()) {
            int currentSession = maxSession.get();
            int prevSession = currentSession - 1;

            return grades.stream()
                    .filter(grade -> grade.getSessionNumber() == currentSession
                            || grade.getSessionNumber() == prevSession)
                    .map(Grade::getType)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * Chaeck for ability to get red diploma.
     *
     * @return true if able to get red diploma, false otherwise
     */
    public boolean canGetRedDiploma() {
        List<GradeType> gradesInLastSessions = extractLastTwoSessions();
        long excellentCount = gradesInLastSessions.stream()
                .filter(type -> type == GradeType.ОТЛИЧНО
                        || type == GradeType.КВАЛИФИКАЦИОННАЯ_РАБОТА_ОТЛИЧНО)
                .count();

        return !gradesInLastSessions.contains(GradeType.УДОВЛЕТВОРИТЕЛЬНО)
                && !gradesInLastSessions.contains(GradeType.НЕУДОВЛЕТВОРИТЕЛЬНО)
                && (double) excellentCount / gradesInLastSessions.size() >= 0.75;
    }

    /**
     * Checks for ability to get an increased scholarship.
     *
     * @return true if can be transfered, false otherwise
     */
    public boolean eligibleForScholarshipIncrease() {
        return calculateAverageScore() > 4.5;
    }

}

enum StudingForm {
    БЮДЖЕТ,
    ПЛАТНОЕ;

    public boolean getForm() {
        switch (this) {
            case БЮДЖЕТ:
                return false;
            case ПЛАТНОЕ:
                return true;
            default:
                throw new IllegalStateException("Unexpected StudingForm: " + this.name());
        }
    }
}