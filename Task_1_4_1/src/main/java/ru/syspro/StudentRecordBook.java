package ru.syspro;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


/**
 * Class that contains record book logic.
 *
 */
public class StudentRecordBook {
    private List<Exam> exams = new ArrayList<>();
    private List<DiffZachet> diffZachets = new ArrayList<>();
    private boolean isPaidForm;

    /**
     * Creates a record book with
     *  paid or unpaid form of studing.
     *
     * @param studingForm paid or unpaid form of studing
     */
    public StudentRecordBook(String studingForm) {
        if (studingForm.equals("бюджет")) {
            this.isPaidForm = false;
        } else {
            this.isPaidForm = true;
        }
    }

    /**
     * Addes a mark on exam.
     *
     * @param subjectname name of subject
     * @param grade grade
     * @param sessionNumber session number
     */
    public void addExam(String subjectname, String grade, int sessionNumber) {
        this.exams.add(new Exam(subjectname, grade, sessionNumber));
    }

    /**
     * Addes a mark on diff zachet.
     *
     * @param subjectname name of subject
     * @param grade grade
     * @param sessionNumber session number
     */
    public void addDiffZachet(String subjectname, String grade, int sessionNumber) {
        this.diffZachets.add(new DiffZachet(subjectname, grade, sessionNumber));
    }

    /**
     * Counts amount of requiremented grade.
     *
     * @param grade grade
     * @return amount of requiremented grade
     */
    public int countGrades(String grade) {
        int count = 0;
        for (Exam exam : exams) {
            if (exam.getGrade().equals(grade)) {
                count += 1;
            }
        }
        for (DiffZachet diffZachet : diffZachets) {
            if (diffZachet.getGrade().equals(grade)) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Calculates average grade.
     *
     * @return average score
     */
    public double calculateAverageScore() {
        int totalA = countGrades("отлично");
        int totalB = countGrades("хорошо");
        int totalC = countGrades("удовлетворительно");
        int totalD = countGrades("неудовлетворительно");

        return ((totalA * 5 + totalB * 4 + totalC * 3 + totalD * 2) /
                (double) (exams.size() + diffZachets.size()));
    }


    /**
     * Checks for ability to transfer on budget.
     *
     * @return true if can be transfered, false otherwise
     */
    public boolean canTransferToBudget() {
        List<String> lastTwoSessions = extractLastTwoSessions();
        if (!lastTwoSessions.contains("удовлетворительно")
                && !lastTwoSessions.contains("неудовлетворительно") && isPaidForm) {
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
    private List<String> extractLastTwoSessions() {
        List<String> grades = new ArrayList<>();
        int lastSession = Math.max(exams.stream().mapToInt(Exam::getSessionNumber).max().orElse(0),
                diffZachets.stream().mapToInt(DiffZachet::getSessionNumber).max().orElse(0));

        for (Exam exam : exams) {
            if (exam.getSessionNumber() == lastSession
                    || exam.getSessionNumber() == lastSession - 1) {
                grades.add(exam.getGrade());
            }
        }

        for (DiffZachet diffZachet : diffZachets) {
            if (diffZachet.getSessionNumber() == lastSession
                    || diffZachet.getSessionNumber() == lastSession - 1) {
                grades.add(diffZachet.getGrade());
            }
        }

        return grades;
    }

    /**
     * Extracts last grades on each of exams.
     * Method for canGetRedDiploma().
     *
     * @return last grades
     */
    private List<String> extractLastGrades() {
        Map<String, String> lastGrades = new HashMap<>();

        for (Exam exam : exams) {
            lastGrades.put(exam.getSubjectName(), exam.getGrade());
        }

        for (DiffZachet dz : diffZachets) {
            lastGrades.put(dz.getSubjectName(), dz.getGrade());
        }

        return new ArrayList<>(lastGrades.values());
    }

    /**
     * Chaeck for ability to get red diploma.
     *
     * @return true if able to get red diploma, false otherwise
     */
    public boolean canGetRedDiploma() {
        List<String> lastGrades = extractLastGrades();
        int totalLastA = 0;
        for (String grade : lastGrades) {
            if (grade == "отлично") {
                totalLastA += 1;
            }
        }
        return !lastGrades.contains("удовлетворительно")
                && !lastGrades.contains("неудовлетворительно")
                && ((double)(totalLastA / lastGrades.size()) >= 0.75);
    }

    /**
     * Checks for ability to get an increased scholarship.
     *
     * @return
     */
    public boolean eligibleForScholarshipIncrease() {
        return calculateAverageScore() > 4.5;
    }

}