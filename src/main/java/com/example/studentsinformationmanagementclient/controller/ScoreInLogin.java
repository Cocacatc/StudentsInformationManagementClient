package com.example.studentsinformationmanagementclient.controller;

public class ScoreInLogin {
    CourseInLogin course;
    StudentInLogin student;
    int score;

    public ScoreInLogin(CourseInLogin course, StudentInLogin student, int score) {
        this.course = course;
        this.student = student;
        this.score = score;
    }

    public CourseInLogin getCourse() {
        return course;
    }

    public void setCourse(CourseInLogin course) {
        this.course = course;
    }

    public StudentInLogin getStudent() {
        return student;
    }

    public void setStudent(StudentInLogin student) {
        this.student = student;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
