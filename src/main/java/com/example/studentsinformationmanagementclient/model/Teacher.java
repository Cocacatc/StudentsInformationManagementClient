package com.example.studentsinformationmanagementclient.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Teacher {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty number = new SimpleStringProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty college = new SimpleStringProperty();
    private Button eraseButton;
    private Button modifyButton;
    private Button courseButton;

    public Teacher(int id,String name, String number,String gender, String college,Button eraseButton,Button modifyButton,Button courseButton) {
        this.id.set(id);
        this.name.set(name);
        this.number.set(number);
        this.gender.set(gender);
        this.college.set(college);
        this.eraseButton = eraseButton;
        this.modifyButton = modifyButton;
        this.courseButton = courseButton;
    }

    public Button getCourseButton() {
        return courseButton;
    }

    public void setCourseButton(Button courseButton) {
        this.courseButton = courseButton;
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getCollege() {
        return college.get();
    }

    public StringProperty collegeProperty() {
        return college;
    }

    public void setCollege(String college) {
        this.college.set(college);
    }
}
