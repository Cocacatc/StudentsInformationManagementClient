package com.example.studentsinformationmanagementclient.request;

public class QueryStudentRequest {
    String number;

    public QueryStudentRequest(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
