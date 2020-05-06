package com.example.gradebook;

public class Assignment {

    private int assignmentNumber;
    private int grade;
    private String name;
    private String description;

    public Assignment(int grade, String name, String description) {
        assignmentNumber = 0;
        this.grade = grade;
        this.name = name;
        this.description = description;
    }

    public Assignment(String name, String description) {
        assignmentNumber = 0;
        this.name = name;
        this.description = description;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
