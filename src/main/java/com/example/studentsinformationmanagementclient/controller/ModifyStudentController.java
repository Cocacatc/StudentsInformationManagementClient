package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.Student;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.example.studentsinformationmanagementclient.request.ModifyStudentRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class ModifyStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> collegeChoiceBox;

    @FXML
    private Button confirmButton;

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

    @FXML
    public static String name,number,myGender = "未选择",college;
    @FXML
    void onConfirmAction(ActionEvent event) {
        name = nameTextField.getText();
        number = numberTextField.getText();
        college = collegeChoiceBox.getValue();
        //必须初始化变量score

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
            ModifyStudentRequest modifyStudentRequest = new ModifyStudentRequest(name,number,college,myGender);
            String message = HttpRequestUtil.modifyStudent(modifyStudentRequest);

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
                for(Student student : UserInformation.studentArrayList){
                    if(student.getNumber().equals(number)){
                        student.setCollege(college);
                        student.setName(name);
                        student.setGender(myGender);
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
        assert collegeChoiceBox != null : "fx:id=\"collegeChoiceBox\" was not injected: check your FXML file 'modifyStudent.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'modifyStudent.fxml'.";
        assert female != null : "fx:id=\"female\" was not injected: check your FXML file 'modifyStudent.fxml'.";
        assert gender != null : "fx:id=\"gender\" was not injected: check your FXML file 'modifyStudent.fxml'.";
        assert male != null : "fx:id=\"male\" was not injected: check your FXML file 'modifyStudent.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'modifyStudent.fxml'.";
        assert numberTextField != null : "fx:id=\"numberTextField\" was not injected: check your FXML file 'modifyStudent.fxml'.";
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'modifyStudent.fxml'.";

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

        numberTextField.setText(StudentController.studentIsModifying.getNumber());
        nameTextField.setText(StudentController.studentIsModifying.getName());
        collegeChoiceBox.setValue(StudentController.studentIsModifying.getCollege());
        //collegeTextField.setText(StudentController.studentIsModifying.getCollege());
        if(StudentController.studentIsModifying.getGender().equals("男")) {
            gender.selectToggle(male);
            myGender = "男";
        }else{
            gender.selectToggle(female);
            myGender = "女";
        }

    }

}
