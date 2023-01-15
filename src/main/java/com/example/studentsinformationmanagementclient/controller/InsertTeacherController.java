package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.Student;
import com.example.studentsinformationmanagementclient.model.Teacher;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.example.studentsinformationmanagementclient.request.InsertStudentRequest;
import com.example.studentsinformationmanagementclient.request.InsertTeacherRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class InsertTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton Female;

    @FXML
    private RadioButton Male;

    @FXML
    private Button confirmButton;


    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField numberTextField;

    @FXML
    private Button returnButton;

    @FXML
    void onConfirmAction(ActionEvent event) {
        name = nameTextField.getText();
        number = numberTextField.getText();
        college = collegeChoiceBox.getValue();

        if(numberTextField.getText().equals("") || nameTextField.getText().equals("") || college == null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("添加失败");
            alert.setContentText("请填入完整信息！");
            alert.show();
            return;
        }

        if(myGender.equals("未选择")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("添加失败");
            alert.setContentText("性别请填写’男‘或’女‘！");
            alert.show();
        }else{
            InsertTeacherRequest insertTeacherRequest = new InsertTeacherRequest(name,number,college,myGender);
            String message = HttpRequestUtil.insertTeacher(insertTeacherRequest);
            //message = "添加成功";
            if(!message.equals("添加成功")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("添加失败");
                alert.setContentText("此学工号已存在！");
                alert.show();
            }else{
                UserInformation.teacherArrayList.add(new Teacher(6,name,number,myGender,college,new Button(),new Button(),new Button()));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("添加成功");
                alert.setContentText("添加成功!");
                alert.show();
            }
        }
    }
    String name,number,myGender = "未选择",college;
    @FXML
    void onFemaleAction(ActionEvent event) {
        myGender = "女";
    }

    @FXML
    void onMaleAction(ActionEvent event) {
        myGender = "男";
    }

    @FXML
    void onReturnButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 940, 590);
        StudentsInformationManagementClientApplication.resetStage("学生信息管理系统",scene);
    }
    @FXML
    private ChoiceBox<String> collegeChoiceBox;
    @FXML
    void initialize() {
        assert Female != null : "fx:id=\"Female\" was not injected: check your FXML file 'insertTeacher.fxml'.";
        assert Male != null : "fx:id=\"Male\" was not injected: check your FXML file 'insertTeacher.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'insertTeacher.fxml'.";
        assert collegeChoiceBox != null : "fx:id=\"collegeChoiceBox\" was not injected: check your FXML file 'insertTeacher.fxml'.";
        assert gender != null : "fx:id=\"gender\" was not injected: check your FXML file 'insertTeacher.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'insertTeacher.fxml'.";
        assert numberTextField != null : "fx:id=\"numberTextField\" was not injected: check your FXML file 'insertTeacher.fxml'.";
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'insertTeacher.fxml'.";
        collegeChoiceBox.getItems().addAll(
                "泰山学堂",
                "软件学院",
                "哲学与社会发展学院",
                "经济学院",
                "文学院",
                "历史文化学院",
                "数学学院",
                "物理学院",
                "计算机学院",
                "管理学院",
                "化学与化工学院"
        );
    }

}
