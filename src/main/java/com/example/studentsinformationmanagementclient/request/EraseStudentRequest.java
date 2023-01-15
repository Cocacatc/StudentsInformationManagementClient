package com.example.studentsinformationmanagementclient.request;

public class EraseStudentRequest {//给用户名为username的用户删除学号为number的学生
    String number;

    public EraseStudentRequest(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
