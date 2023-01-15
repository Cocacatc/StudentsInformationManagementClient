package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.example.studentsinformationmanagementclient.request.RegisterRequest;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordAgainPasswordField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    void onRegisterAction(ActionEvent event) throws IOException {

        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        String passwordAgain = passwordAgainPasswordField.getText();
        String email = emailTextField.getText();

        //post request type
        if(password.equals(passwordAgain)){

            RegisterRequest registerRequest = new RegisterRequest(username,password,email);

            String message = HttpRequestUtil.register(registerRequest);
            if(!message.equals("注册成功")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("登陆失败");
                alert.setContentText("用户名已存在！");
                alert.show();
                return;
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("注册成功");
                alert.setContentText("注册成功！");
                alert.show();
                FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(),600,400);
                StudentsInformationManagementClientApplication.resetStage("登录",scene);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("注册失败");
            alert.setContentText("两次密码输入不一致！");
            alert.show();
        }
    }

    @FXML
    void onReturnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StudentsInformationManagementClientApplication.resetStage("登录",scene);
    }

    @FXML
    void initialize() {
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'register.fxml'.";
        assert passwordAgainPasswordField != null : "fx:id=\"passwordAgainPasswordField\" was not injected: check your FXML file 'register.fxml'.";
        assert passwordPasswordField != null : "fx:id=\"passwordPasswordField\" was not injected: check your FXML file 'register.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'register.fxml'.";
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'register.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'register.fxml'.";

    }

}
