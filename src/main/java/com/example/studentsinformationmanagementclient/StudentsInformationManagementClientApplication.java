package com.example.studentsinformationmanagementclient;

import com.example.studentsinformationmanagementclient.model.Course;
import com.example.studentsinformationmanagementclient.model.Student;
import com.example.studentsinformationmanagementclient.model.Teacher;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentsInformationManagementClientApplication extends Application {
    public static HostServices hostServices;
    public static void resetStage(String name, Scene scene) {
        mainStage.setTitle(name);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("登录");
        stage.setScene(scene);
        stage.show();
        mainStage = stage;
        hostServices = getHostServices();
    }

    public static void main(String[] args) {
        launch();
    }
}