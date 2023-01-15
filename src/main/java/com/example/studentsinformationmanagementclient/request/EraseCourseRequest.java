package com.example.studentsinformationmanagementclient.request;

public class EraseCourseRequest {
    String number;

    public EraseCourseRequest(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
