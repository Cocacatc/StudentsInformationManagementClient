package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.Course;
import com.example.studentsinformationmanagementclient.model.Student;
import com.example.studentsinformationmanagementclient.model.Teacher;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.example.studentsinformationmanagementclient.request.ModifyCourseRequest;
import com.example.studentsinformationmanagementclient.request.ModifyStudentRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ModifyCourseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField creditTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField numberTextField;

    @FXML
    private Button returnButton;
    public static String name,number,teacherNumber;
    public static int credit;
    @FXML
    void onConfirmAction(ActionEvent event) {
        name = nameTextField.getText();
        number = numberTextField.getText();
        String str = teacherChoiceBox.getValue();
        teacherNumber = "";
        for(int i = 0;i < str.length();i++){
            if(str.charAt(i) == '-')break;
            teacherNumber += str.charAt(i);
        }
        //System.out.println(teacherNumber);
        credit = 0;//必须初始化变量

        if(teacherNumber.equals("") || creditTextField.getText().equals("") || numberTextField.getText().equals("") || nameTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("修改失败");
            alert.setContentText("请填入完整信息！");
            alert.show();
            return;
        }

        for(int i = 0;i < creditTextField.getText().length();i++){
            if(creditTextField.getText().charAt(i) < '0' || creditTextField.getText().charAt(i) > '9'){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("修改失败");
                alert.setContentText("成绩格式错误！");
                alert.show();
                return;
            }else{
                credit = credit * 10 + creditTextField.getText().charAt(i) - '0';
            }
        }

        ModifyCourseRequest modifyCourseRequest = new ModifyCourseRequest(name,number,credit,teacherNumber);
        String message = HttpRequestUtil.modifyCourse(modifyCourseRequest);

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
            for(Course course : UserInformation.courseArrayList){
                if(course.getNumber().equals(number)){
                    course.setName(name);
                    course.setCredit(credit);
                    course.setTeacherNumberName(str);
                    break;
                }
            }
        }
    }

    @FXML
    void onReturnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 940, 590);
        StudentsInformationManagementClientApplication.resetStage("学生信息管理系统",scene);
    }
    @FXML
    private ChoiceBox<String> teacherChoiceBox;
    @FXML
    void initialize() {
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'modifyCourse.fxml'.";
        assert creditTextField != null : "fx:id=\"creditTextField\" was not injected: check your FXML file 'modifyCourse.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'modifyCourse.fxml'.";
        assert numberTextField != null : "fx:id=\"numberTextField\" was not injected: check your FXML file 'modifyCourse.fxml'.";
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'modifyCourse.fxml'.";
        assert teacherChoiceBox != null : "fx:id=\"teacherChoiceBox\" was not injected: check your FXML file 'modifyCourse.fxml'.";

        for(Teacher teacher : UserInformation.teacherArrayList){
            teacherChoiceBox.getItems().add(teacher.getNumber()+"-"+teacher.getName());
            if(CourseController.courseIsModifying.getTeacherNumberName().equals(teacher.getNumber()+"-"+teacher.getName()))teacherChoiceBox.setValue(teacher.getNumber()+"-"+teacher.getName());
        }

        numberTextField.setText(CourseController.courseIsModifying.getNumber());
        nameTextField.setText(CourseController.courseIsModifying.getName());
        creditTextField.setText(CourseController.courseIsModifying.getCredit() + "");

    }

}
