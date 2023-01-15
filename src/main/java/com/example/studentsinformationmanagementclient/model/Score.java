package com.example.studentsinformationmanagementclient.model;

import com.example.studentsinformationmanagementclient.controller.CourseInLogin;
import com.example.studentsinformationmanagementclient.controller.StudentInLogin;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.example.studentsinformationmanagementclient.request.QueryCourseRequest;
import com.example.studentsinformationmanagementclient.request.QueryStudentRequest;
import com.google.gson.Gson;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Score {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty student = new SimpleStringProperty();
    private final StringProperty course = new SimpleStringProperty();
    private final IntegerProperty score = new SimpleIntegerProperty();
    private Button eraseButton;
    private Button modifyButton;
    private String studentNumber;
    private String courseNumber;
    public Score(int id, String studentNumber,String courseNumber,int score, Button eraseButton,Button modifyButton){
        this.id.set(id);
        this.studentNumber = studentNumber;
        this.courseNumber = courseNumber;
        this.score.set(score);
        this.eraseButton = eraseButton;
        this.modifyButton = modifyButton;

        String message = HttpRequestUtil.queryAStudent(new QueryStudentRequest(studentNumber));
        Gson gson = new Gson();
        StudentInLogin studentInLogin = gson.fromJson(message,StudentInLogin.class);

        this.student.set(studentInLogin.getNumber()+"-"+studentInLogin.getName());

        message = HttpRequestUtil.queryACourse(new QueryCourseRequest(courseNumber));
        CourseInLogin courseInLogin = gson.fromJson(message,CourseInLogin.class);

        this.course.set(courseInLogin.getNumber()+"-"+courseInLogin.getName());

    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getStudent() {
        return student.get();
    }

    public StringProperty studentProperty() {
        return student;
    }

    public void setStudent(String student) {
        this.student.set(student);
    }

    public String getCourse() {
        return course.get();
    }

    public StringProperty courseProperty() {
        return course;
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public Button getEraseButton() {
        return eraseButton;
    }

    public void setEraseButton(Button eraseButton) {
        this.eraseButton = eraseButton;
    }

    public Button getModifyButton() {
        return modifyButton;
    }

    public void setModifyButton(Button modifyButton) {
        this.modifyButton = modifyButton;
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