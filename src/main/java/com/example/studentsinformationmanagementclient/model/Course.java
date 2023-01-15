package com.example.studentsinformationmanagementclient.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Course {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty number = new SimpleStringProperty();
    private final IntegerProperty credit = new SimpleIntegerProperty();
    private final StringProperty teacherNumberName = new SimpleStringProperty();
    private Button eraseButton;
    private Button modifyButton;
    private Button scoreButton;
    private Button chartButton;

    public Course(int id, String name,String number,int credit,String teacherNumberName, Button eraseButton,Button modifyButton,Button scoreButton,Button chartButton) {
        this.id.set(id);
        this.name.set(name);
        this.number.set(number);
        this.credit.set(credit);
        this.teacherNumberName.set(teacherNumberName);
        this.eraseButton = eraseButton;
        this.modifyButton = modifyButton;
        this.scoreButton = scoreButton;
        this.chartButton = chartButton;
    }

    public String getTeacherNumberName() {
        return teacherNumberName.get();
    }

    public StringProperty teacherNumberNameProperty() {
        return teacherNumberName;
    }

    public void setTeacherNumberName(String teacherNumberName) {
        this.teacherNumberName.set(teacherNumberName);
    }

    public Button getChartButton() {
        return chartButton;
    }

    public void setChartButton(Button chartButton) {
        this.chartButton = chartButton;
    }

    public Button getScoreButton() {
        return scoreButton;
    }

    public void setScoreButton(Button scoreButton) {
        this.scoreButton = scoreButton;
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public int getCredit() {
        return credit.get();
    }

    public IntegerProperty creditProperty() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit.set(credit);
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
}
