package com.example.studentsinformationmanagementclient.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.example.studentsinformationmanagementclient.request.ModifyUserRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class UserMessageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField AgainPasswordField;

    @FXML
    private Label emailLabel;

    @FXML
    private Button modifyPasswordButton;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    void onModifyPasswordAction(ActionEvent event) {
        if(!AgainPasswordField.getText().equals(newPasswordField.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("修改失败");
            alert.setContentText("两次新密码输入不一致!");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("修改成功");
            alert.setContentText("修改成功!");
            alert.show();

            UserInformation.password = newPasswordField.getText();

            String message = HttpRequestUtil.modifyUser(new ModifyUserRequest(UserInformation.username,UserInformation.password,UserInformation.email));

        }
    }

    @FXML
    void initialize() {
        assert AgainPasswordField != null : "fx:id=\"AgainPasswordField\" was not injected: check your FXML file 'userMessage.fxml'.";
        assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'userMessage.fxml'.";
        assert modifyPasswordButton != null : "fx:id=\"modifyPasswordButton\" was not injected: check your FXML file 'userMessage.fxml'.";
        assert newPasswordField != null : "fx:id=\"newPasswordField\" was not injected: check your FXML file 'userMessage.fxml'.";
        assert passwordLabel != null : "fx:id=\"passwordLabel\" was not injected: check your FXML file 'userMessage.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'userMessage.fxml'.";
        usernameLabel.setText(UserInformation.username);
        passwordLabel.setText(UserInformation.password);
        emailLabel.setText(UserInformation.email);
    }

}
