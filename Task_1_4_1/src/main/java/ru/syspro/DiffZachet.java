package ru.syspro;

public class DiffZachet extends Grade {
    private final int sessionNumber;

    public DiffZachet(String subjectName, String grade, int sessionNumber) {
        super(subjectName, grade);
        this.sessionNumber = sessionNumber;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }
}