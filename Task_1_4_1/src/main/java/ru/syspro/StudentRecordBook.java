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
    private StudingForm isPaidForm;

    /**
     * Creates a record book with
     *  paid or unpaid form of studing.
     *
     * @param form paid or unpaid form of studing
     */
    public StudentRecordBook(StudingForm form) {
        this.isPaidForm = form;
    }

    /**
     * Addes a mark on grade(exam or diff zachet).
     *
     * @param subjectname name of subject
     * @param grade gradetype
     * @param sessionNumber session number
     * @param assessmentForm exam or diff zach or qualification work
     */
    public void addGrade(String subjectname, Grade.GradeType grade,
                         int sessionNumber, FormOfAssessment assessmentForm) {
        this.grades.add(new Grade(subjectname, grade, sessionNumber, assessmentForm));
    }


    /**
     * Counts amount of requiremented grade.
     *
     * @param type type
     * @return amount of requiremented grade
     */
    public int countGrades(Grade.GradeType type) {
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
        List<Grade.GradeType> lastTwoSessions = extractLastTwoSessions();
        if (!lastTwoSessions.contains(Grade.GradeType.УДОВЛЕТВОРИТЕЛЬНО)
                && !lastTwoSessions.contains(Grade.GradeType.НЕУДОВЛЕТВОРИТЕЛЬНО)
                && this.isPaidForm.equals(StudingForm.ПЛАТНОЕ)) {
            isPaidForm = StudingForm.БЮДЖЕТ;
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
    private List<Grade.GradeType> extractLastTwoSessions() {
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
        List<Grade.GradeType> gradesInLastSessions = extractLastTwoSessions();
        long excellentCount = gradesInLastSessions.stream()
                .filter(type -> type == Grade.GradeType.ОТЛИЧНО)
                .count();

        boolean hasBadGrades = gradesInLastSessions.contains(Grade.GradeType.УДОВЛЕТВОРИТЕЛЬНО)
                || gradesInLastSessions.contains(Grade.GradeType.НЕУДОВЛЕТВОРИТЕЛЬНО);

        boolean enoughExcellence = (double) excellentCount / gradesInLastSessions.size() >= 0.75;

        Optional<Grade> qualifyingWork = grades.stream()
                .filter(grade -> grade.getAssessmentForm() == FormOfAssessment.КВАЛИФИКАЦИОННАЯ_РАБОТА)
                .findAny();

        if (hasBadGrades || !enoughExcellence) {
            return false;
        }

        return !qualifyingWork.isPresent() || (qualifyingWork.get().getType() == Grade.GradeType.ОТЛИЧНО);
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
}