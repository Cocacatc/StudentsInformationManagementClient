package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane MainPane;

    @FXML
    private Button courseButton;

    @FXML
    private Button firstPageButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button studentButton;

    @FXML
    private Button teacherButton;

    @FXML
    private Button usernameButton;

    public MainController() throws IOException {
    }


    @FXML
    void onCourseAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(StudentsInformationManagementClientApplication.class.getResource("course.fxml"));
        MainPane.setCenter(root);
    }

    @FXML
    void onFirstPageAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(StudentsInformationManagementClientApplication.class.getResource("firstPage.fxml"));
        MainPane.setCenter(root);
    }

    @FXML
    void onLogoutAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StudentsInformationManagementClientApplication.resetStage("登录",scene);
    }

    @FXML
    void onStudentAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(StudentsInformationManagementClientApplication.class.getResource("student.fxml"));
        MainPane.setCenter(root);
    }

    @FXML
    void onTeacherAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(StudentsInformationManagementClientApplication.class.getResource("teacher.fxml"));
        MainPane.setCenter(root);
    }

    @FXML
    void onUsernameAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(StudentsInformationManagementClientApplication.class.getResource("userMessage.fxml"));
        MainPane.setCenter(root);
    }
    @FXML
    void initialize() throws IOException {
        assert MainPane != null : "fx:id=\"MainPane\" was not injected: check your FXML file 'main.fxml'.";
        assert courseButton != null : "fx:id=\"courseButton\" was not injected: check your FXML file 'main.fxml'.";
        assert firstPageButton != null : "fx:id=\"firstPageButton\" was not injected: check your FXML file 'main.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'main.fxml'.";
        assert studentButton != null : "fx:id=\"studentButton\" was not injected: check your FXML file 'main.fxml'.";
        assert teacherButton != null : "fx:id=\"teacherButton\" was not injected: check your FXML file 'main.fxml'.";
        assert usernameButton != null : "fx:id=\"usernameButton\" was not injected: check your FXML file 'main.fxml'.";

        Parent root = FXMLLoader.load(StudentsInformationManagementClientApplication.class.getResource("firstPage.fxml"));
        MainPane.setCenter(root);
        usernameButton.setText(UserInformation.username);
    }

}
