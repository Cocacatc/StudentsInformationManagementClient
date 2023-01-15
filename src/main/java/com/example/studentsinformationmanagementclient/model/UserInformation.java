package com.example.studentsinformationmanagementclient.model;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class UserInformation {
    public static String username;
    public static String password;
    public static String email;
    /*public static ArrayList<Student> studentArrayList = new ArrayList<>(){{
        add(new Student(6,"lyuzlion","202200130076","男","泰山学堂",100,new Button(),new Button())), add(new Student(6,"lyuzlion","202200130076","男","泰山学堂",100,new Button(),new Button()));
        add(new Student(3,"lyuzlion","202200130076","男","泰山学堂",100,new Button(),new Button())), add(new Student(6,"lyuzlion","202200130076","男","泰山学堂",100,new Button(),new Button()));
    }};*/
    public static ArrayList<Student> studentArrayList = new ArrayList<>();
    public static ArrayList<Teacher> teacherArrayList = new ArrayList<>();
    public static ArrayList<Course> courseArrayList = new ArrayList<>();

}
