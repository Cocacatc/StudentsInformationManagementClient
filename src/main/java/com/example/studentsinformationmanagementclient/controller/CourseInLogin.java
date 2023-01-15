package com.example.studentsinformationmanagementclient.controller;

import com.example.studentsinformationmanagementclient.model.Teacher;

public class CourseInLogin {
    int id;
    String name;
    String number;
    int credit;
    TeacherInLogin teacher;

    public CourseInLogin(int id, String name, String number, int credit,TeacherInLogin teacher){
        this.id = id;
        this.name = name;
        this.number = number;
        this.credit = credit;
        this.teacher = teacher;
    }

    public TeacherInLogin getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherInLogin teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
