package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.Course;
import com.example.studentsinformationmanagementclient.model.Student;
import com.example.studentsinformationmanagementclient.model.Teacher;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    void onLoginButton(ActionEvent event) throws IOException {
        if(!checkTextField.getText().equalsIgnoreCase(checkText.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("登陆失败");
            alert.setContentText("验证码错误！");
            alert.show();
            checkText.setText(getCode());
            return;
        }

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        LoginRequest loginRequest = new LoginRequest(username,password);
        String message = HttpRequestUtil.login(loginRequest);
        if(!message.equals("登陆成功")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("登陆失败");
            alert.setContentText("用户名或密码错误！");
            alert.show();
            checkText.setText(getCode());
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("登陆成功");
            alert.setContentText("欢迎！");
            alert.show();
            Gson gson = new Gson();
            message = HttpRequestUtil.queryUser(new QueryUserRequest(username));
            gson = new Gson();
            UserInLogin user = gson.fromJson(message,UserInLogin.class);
            UserInformation.username = user.username;
            UserInformation.password = user.password;
            UserInformation.email = user.email;

            message = HttpRequestUtil.queryStudent();


            UserInformation.studentArrayList.clear();
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(message).getAsJsonArray();
            for(JsonElement student : jsonArray){
                StudentInLogin studentInLogin = gson.fromJson(student, StudentInLogin.class);
                UserInformation.studentArrayList.add(new Student(studentInLogin.id,studentInLogin.name,studentInLogin.number,studentInLogin.gender,studentInLogin.college,new Button(),new Button(),new Button()));
            }

            message = HttpRequestUtil.queryTeacher();

            gson = new Gson();

            HashMap<String,String> hashMap = new HashMap<String,String>();

            UserInformation.teacherArrayList.clear();
            parser = new JsonParser();
            jsonArray = parser.parse(message).getAsJsonArray();
            for(JsonElement teacher : jsonArray){
                TeacherInLogin teacherInLogin = gson.fromJson(teacher, TeacherInLogin.class);
                hashMap.put(teacherInLogin.number,teacherInLogin.name);
                UserInformation.teacherArrayList.add(new Teacher(teacherInLogin.id,teacherInLogin.name,teacherInLogin.number,teacherInLogin.gender,teacherInLogin.college,new Button(),new Button(),new Button()));
            }

            message = HttpRequestUtil.queryCourse();
            gson = new Gson();

            UserInformation.courseArrayList.clear();
            parser = new JsonParser();
            jsonArray = parser.parse(message).getAsJsonArray();
            for(JsonElement course : jsonArray){
                CourseInLogin courseInLogin = gson.fromJson(course, CourseInLogin.class);
                if(courseInLogin.getTeacher() != null) UserInformation.courseArrayList.add(new Course(courseInLogin.id,courseInLogin.name,courseInLogin.number,courseInLogin.credit,courseInLogin.getTeacher().getNumber() + "-" + courseInLogin.getTeacher().getName(),new Button(),new Button(),new Button(),new Button()));
                else UserInformation.courseArrayList.add(new Course(courseInLogin.id,courseInLogin.name,courseInLogin.number,courseInLogin.credit,"",new Button(),new Button(),new Button(),new Button()));
            }

            FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 940, 590);
            StudentsInformationManagementClientApplication.resetStage("学生信息管理系统", scene);
        }
    }

    @FXML
    void onRegisterAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StudentsInformationManagementClientApplication.resetStage("注册", scene);
    }
    @FXML
    private Button refreshButton;
    @FXML
    private Text checkText;
    @FXML
    private TextField checkTextField;
    @FXML
    void onRefreshButton(ActionEvent event) {
        checkText.setText(getCode());
    }
    @FXML
    void initialize() {
        assert easterEggButton != null : "fx:id=\"easterEggButton\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'login.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'login.fxml'.";
        assert checkText != null : "fx:id=\"checkText\" was not injected: check your FXML file 'login.fxml'.";
        assert checkTextField != null : "fx:id=\"checkTextField\" was not injected: check your FXML file 'login.fxml'.";
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'login.fxml'.";
        checkText.setText(getCode());
    }

    public String getCode(){
        char arr[] = new char[52];
        for (int i = 0; i < 26; i++) {
            arr[i] = (char) ('A' + i);
        }
        for(int i = 26; i < 52; i++){
            arr[i] = (char)('a' + i - 26);
        }

        String str = "";

        for (int i = 0 ; i < 4 ; i++) {
            Random r = new Random();
            int rand = r.nextInt(52);
            str = str + arr[rand];
        }

        //添加数字
        Random r = new Random();
        str = str + r.nextInt(10);

        //转换为数组
        char arr2[] = str.toCharArray();
        for (int i = 0; i < arr2.length; i++) {
            Random r2 = new Random();
            int rand = r2.nextInt(arr2.length);
            char temp = arr2[i];
            arr2[i] = arr2[rand];
            arr2[rand] = temp;
        }
        String code = "";
        for(int i = 0; i < arr2.length; i++){
            code = code + arr2[i];
        }
        return code;
    }

    @FXML
    private Button easterEggButton;
    @FXML
    void onEasterEggButton(ActionEvent event) {
        HostServices hostServices = StudentsInformationManagementClientApplication.hostServices;
        hostServices.showDocument("https://www.bilibili.com/video/BV1GJ411x7h7/?spm_id_from=333.788.recommend_more_video.1&vd_source=20f7feb459f3e017e935aa0dcb950320");
    }
}
