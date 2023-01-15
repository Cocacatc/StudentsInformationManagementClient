package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.Student;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.example.studentsinformationmanagementclient.request.InsertScoreRequest;
import com.example.studentsinformationmanagementclient.request.InsertStudentRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class InsertScoreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirmButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextField scoreTextField;

    @FXML
    private ChoiceBox<String> studentChoiceBox;

    @FXML
    void onConfirmAction(ActionEvent event) {
        int score = 0;
        for(int i = 0;i < scoreTextField.getText().length();i++){
            if(scoreTextField.getText().charAt(i) < '0' || scoreTextField.getText().charAt(i) > '9'){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("添加失败");
                alert.setContentText("成绩格式错误！");
                alert.show();
                return;
            }else{
                score = score * 10 + scoreTextField.getText().charAt(i) - '0';
            }
        }
        String str = studentChoiceBox.getValue();
        if(str == null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("添加失败");
            alert.setContentText("请填入完整信息！");
            alert.show();
        }else{
            String studentNumber = "";
            for(int i = 0;i < str.length();i++){
                if(str.charAt(i) == '-')break;
                studentNumber += str.charAt(i);
            }
            InsertScoreRequest insertScoreRequest = new InsertScoreRequest(score,studentNumber,CourseController.courseIsChecking);
            String message = HttpRequestUtil.insertScore(insertScoreRequest);

            //message = "添加成功";

            if(!message.equals("添加成功")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("添加失败");
                alert.setContentText("此成绩已存在！");
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("添加成功");
                alert.setContentText("添加成功!");
                alert.show();
            }
        }
    }

    @FXML
    void onReturnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("score.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StudentsInformationManagementClientApplication.resetStage(CourseController.courseIsChecking+"-"+CourseController.courseIsChecking,scene);
    }

    @FXML
    void initialize() {
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'insertScore.fxml'.";
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'insertScore.fxml'.";
        assert scoreTextField != null : "fx:id=\"scoreTextField\" was not injected: check your FXML file 'insertScore.fxml'.";
        assert studentChoiceBox != null : "fx:id=\"studentChoiceBox\" was not injected: check your FXML file 'insertScore.fxml'.";

        for(Student student : UserInformation.studentArrayList){
            studentChoiceBox.getItems().add(student.getNumber() + "-" + student.getName());
        }
    }

}
