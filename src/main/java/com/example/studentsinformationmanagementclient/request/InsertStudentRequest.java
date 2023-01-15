package com.example.studentsinformationmanagementclient.request;

public class InsertStudentRequest {//给用户名为username的用户插入学号为number,学生信息如下的学生
    String name;
    String number;
    String college;
    String gender;

    public InsertStudentRequest(String name, String number, String college, String gender) {
        this.name = name;
        this.number = number;
        this.college = college;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

}
