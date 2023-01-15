package com.example.studentsinformationmanagementclient.request;

public class QueryUserRequest {
    String username;

    public QueryUserRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
