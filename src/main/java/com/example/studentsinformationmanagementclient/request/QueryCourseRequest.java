package com.example.studentsinformationmanagementclient.request;

public class QueryCourseRequest {
    String number;

    public QueryCourseRequest(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
