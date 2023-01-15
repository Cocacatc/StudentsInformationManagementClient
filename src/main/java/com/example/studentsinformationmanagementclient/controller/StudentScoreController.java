package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.Course;
import com.example.studentsinformationmanagementclient.model.Score;
import com.example.studentsinformationmanagementclient.model.Student;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.EraseStudentRequest;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentScoreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnButton;

    @FXML
    private TableView<Score> tableView;


    @FXML
    void onReturnButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 940, 590);
        StudentsInformationManagementClientApplication.resetStage("学生信息管理系统",scene);
    }

    @FXML
    void initialize() {
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'studentScore.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'studentScore.fxml'.";

    }
}
