package ru.syspro;

public class Grade {
    protected final String subjectName;
    protected final String grade;

    public Grade(String subjectName, String grade) {
        this.grade = grade;
        this.subjectName = subjectName;
    }

    public String getGrade() {
        return grade;
    }

    public  String getSubjectName() {
        return subjectName;
    }
}