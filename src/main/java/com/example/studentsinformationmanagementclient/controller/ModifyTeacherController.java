package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.Student;
import com.example.studentsinformationmanagementclient.model.Teacher;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.example.studentsinformationmanagementclient.request.ModifyStudentRequest;
import com.example.studentsinformationmanagementclient.request.ModifyTeacherRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class ModifyTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirmButton;

    @FXML
    private ChoiceBox<String> collegeChoiceBox;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton male;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField numberTextField;

    @FXML
    private Button returnButton;
    public static String name,number,myGender = "未选择",college;
    @FXML
    void onConfirmAction(ActionEvent event) {
        name = nameTextField.getText();
        number = numberTextField.getText();
        college = collegeChoiceBox.getValue();
        //college = collegeTextField.getText();

        if(numberTextField.getText().equals("") || nameTextField.getText().equals("") || college == null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("修改失败");
            alert.setContentText("请填入完整信息！");
            alert.show();
            return;
        }
        if(myGender.equals("未选择")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("修改失败");
            alert.setContentText("性别请选择’男‘或’女‘！");
            alert.show();
        }else{
            ModifyTeacherRequest modifyTeacherRequest = new ModifyTeacherRequest(number,name,myGender,college);
            String message = HttpRequestUtil.modifyTeacher(modifyTeacherRequest);
            //message = "修改成功";
            if(!message.equals("修改成功")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("修改失败");
                alert.setContentText("修改失败");
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("修改成功");
                alert.setContentText("修改成功!");
                alert.show();
                for(Teacher teacher : UserInformation.teacherArrayList){
                    if(teacher.getNumber().equals(number)){
                        teacher.setCollege(college);
                        teacher.setName(name);
                        teacher.setGender(myGender);
                        break;
                    }
                }
            }
        }
    }

    @FXML
    void onFemaleAction(ActionEvent event) {
        myGender = "女";
    }

    @FXML
    void onMaleAction(ActionEvent event) {
        myGender = "男";
    }

    @FXML
    void onReturnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 940, 590);
        StudentsInformationManagementClientApplication.resetStage("学生信息管理系统",scene);
    }

    @FXML
    void initialize() {
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'modifyTeacher.fxml'.";
        assert collegeChoiceBox != null : "fx:id=\"collegeChoiceBox\" was not injected: check your FXML file 'modifyTeacher.fxml'.";
        assert female != null : "fx:id=\"female\" was not injected: check your FXML file 'modifyTeacher.fxml'.";
        assert gender != null : "fx:id=\"gender\" was not injected: check your FXML file 'modifyTeacher.fxml'.";
        assert male != null : "fx:id=\"male\" was not injected: check your FXML file 'modifyTeacher.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'modifyTeacher.fxml'.";
        assert numberTextField != null : "fx:id=\"numberTextField\" was not injected: check your FXML file 'modifyTeacher.fxml'.";
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'modifyTeacher.fxml'.";

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

        numberTextField.setText(TeacherController.teacherIsModifying.getNumber());
        nameTextField.setText(TeacherController.teacherIsModifying.getName());
        collegeChoiceBox.setValue(TeacherController.teacherIsModifying.getCollege());
        //collegeTextField.setText(TeacherController.teacherIsModifying.getCollege());
        if(TeacherController.teacherIsModifying.getGender().equals("男")) {
            gender.selectToggle(male);
            myGender = "男";
        }else{
            gender.selectToggle(female);
            myGender = "女";
        }
    }

}
