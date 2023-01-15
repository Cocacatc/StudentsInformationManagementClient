package com.example.studentsinformationmanagementclient.request;

public class InsertCourseRequest {
    String name;
    String number;
    int credit;
    String teacherNumber;

    public InsertCourseRequest(String name, String number, int credit,String teacherNumber) {
        this.name = name;
        this.number = number;
        this.credit = credit;
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
