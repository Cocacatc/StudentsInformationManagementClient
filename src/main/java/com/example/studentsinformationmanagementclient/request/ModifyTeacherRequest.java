package com.example.studentsinformationmanagementclient.request;

public class ModifyTeacherRequest {
    String number;
    String name;
    String gender;
    String college;

    public ModifyTeacherRequest(String number, String name, String gender, String college) {
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.college = college;
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
