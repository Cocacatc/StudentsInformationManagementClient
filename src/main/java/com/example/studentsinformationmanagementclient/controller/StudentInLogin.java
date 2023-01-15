package com.example.studentsinformationmanagementclient.controller;

public class StudentInLogin {
    int id;
    String number;
    String name;
    String gender;
    String college;

    public StudentInLogin(int id, String number, String name, String gender, String college) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.college = college;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

}
