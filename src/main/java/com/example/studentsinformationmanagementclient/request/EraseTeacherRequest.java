package com.example.studentsinformationmanagementclient.request;

public class EraseTeacherRequest {//给用户名为username的用户删除学工号为number的老师
    String number;

    public EraseTeacherRequest(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
