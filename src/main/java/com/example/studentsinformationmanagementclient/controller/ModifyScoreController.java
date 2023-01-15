package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.example.studentsinformationmanagementclient.request.InsertScoreRequest;
import com.example.studentsinformationmanagementclient.request.ModifyScoreRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModifyScoreController {

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
    private TextField studentTextField;

    @FXML
    void onConfirmButton(ActionEvent event) {
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
        if(scoreTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("添加失败");
            alert.setContentText("请填入完整信息！");
            alert.show();
        }else{
            ModifyScoreRequest modifyScoreRequest = new ModifyScoreRequest(score,ScoreController.scoreIsModifying.getStudentNumber(),ScoreController.scoreIsModifying.getCourseNumber());
            String message = HttpRequestUtil.modifyScore(modifyScoreRequest);

            //message = "添加成功";

            if(!message.equals("修改成功")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("修改失败");
                alert.setContentText("修改失败！");
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("修改成功");
                alert.setContentText("修改成功!");
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
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'modifyScore.fxml'.";
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'modifyScore.fxml'.";
        assert scoreTextField != null : "fx:id=\"scoreTextField\" was not injected: check your FXML file 'modifyScore.fxml'.";
        assert studentTextField != null : "fx:id=\"studentTextField\" was not injected: check your FXML file 'modifyScore.fxml'.";
        studentTextField.setText(ScoreController.scoreIsModifying.getStudent());
        scoreTextField.setText(ScoreController.scoreIsModifying.getScore() + "");
    }

}
