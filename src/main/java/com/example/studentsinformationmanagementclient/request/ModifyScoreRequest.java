package com.example.studentsinformationmanagementclient.request;

public class ModifyScoreRequest {
    int score;
    String studentNumber;
    String courseNumber;

    public ModifyScoreRequest(int score, String studentNumber, String courseNumber) {
        this.score = score;
        this.studentNumber = studentNumber;
        this.courseNumber = courseNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }
}
