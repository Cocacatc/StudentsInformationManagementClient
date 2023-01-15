package com.example.studentsinformationmanagementclient.controller;

public class TeacherInLogin {
    int id;
    String name;
    String gender;
    String college;
    String number;

    public TeacherInLogin(int id, String name, String gender, String college, String number) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.college = college;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
