package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.*;
import com.example.studentsinformationmanagementclient.request.EraseStudentRequest;
import com.example.studentsinformationmanagementclient.request.EraseTeacherRequest;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button insertTeacherButton;
    @FXML
    private Button queryAllButton;

    @FXML
    private Button queryTeacherButton;

    @FXML
    private TextField queryTextField;
    @FXML
    private TableView<Teacher> table;

    @FXML
    void onInsertTeacherAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("insertTeacher.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StudentsInformationManagementClientApplication.resetStage("添加教师",scene);
    }
    public static ObservableList<Teacher> data = FXCollections.observableArrayList();

    boolean in(String s1,String s2){
        for(int i = 0;i < s1.length();i++){
            boolean ok = false;
            for(int j = 0;j < s2.length();j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    ok = true;
                    break;
                }
            }
            if(!ok)return false;
        }
        return true;
    }
    @FXML
    void onQueryAllAction(ActionEvent event) {
        data = FXCollections.observableArrayList();
        String str = queryTextField.getText();
        for(Teacher teacher : UserInformation.teacherArrayList){
            data.add(teacher);
        }
        //System.out.println(in("泰山学堂","泰山学堂"));
        table.setItems(data);
    }

    @FXML
    void onQueryTeacherAction(ActionEvent event) {
        data = FXCollections.observableArrayList();
        String str = queryTextField.getText();
        for(Teacher teacher : UserInformation.teacherArrayList){
            boolean ok = false;
            if(in(str,teacher.getName()) || in(str,teacher.getNumber()) || in(str,teacher.getCollege()) || in(str,teacher.getGender())){
                data.add(teacher);
            }
        }
        table.setItems(data);
    }
    public static Teacher teacherIsModifying;
    public static String teacherIsChecking;
    @FXML
    void initialize() {
        assert insertTeacherButton != null : "fx:id=\"insertTeacherButton\" was not injected: check your FXML file 'teacher.fxml'.";
        assert queryAllButton != null : "fx:id=\"queryAllButton\" was not injected: check your FXML file 'teacher.fxml'.";
        assert queryTeacherButton != null : "fx:id=\"queryTeacherButton\" was not injected: check your FXML file 'teacher.fxml'.";
        assert queryTextField != null : "fx:id=\"queryTextField\" was not injected: check your FXML file 'teacher.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'teacher.fxml'.";

        data = FXCollections.observableArrayList();

        for(int i = 0; i < UserInformation.teacherArrayList.size(); i++){
            Teacher teacher = UserInformation.teacherArrayList.get(i);
            teacher.setId(i + 1);
            teacher.getEraseButton().setText("删除");
            teacher.getModifyButton().setText("修改");
            teacher.getCourseButton().setText("查看课程");
            data.add(teacher);
        }

        for(Teacher teacher : UserInformation.teacherArrayList){
            teacher.getEraseButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    EraseTeacherRequest eraseTeacherRequest = new EraseTeacherRequest(teacher.getNumber());
                    String message = HttpRequestUtil.eraseTeacher(eraseTeacherRequest);
                    //message = "删除成功";
                    if(message.equals("删除失败")){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("删除失败");
                        alert.setContentText("删除失败！");
                        alert.show();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("删除成功");
                        alert.setContentText("删除成功！");
                        alert.show();
                        for(Course course : UserInformation.courseArrayList){
                            if(course.getTeacherNumberName().equals(teacher.getNumber() + "-" +teacher.getName())){
                                course.setTeacherNumberName("");
                            }
                        }
                        UserInformation.teacherArrayList.remove(teacher);
                        data.remove(teacher);
                    }
                }
            });
            teacher.getModifyButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    teacherIsModifying = teacher;
                    FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("modifyTeacher.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 600, 400);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    StudentsInformationManagementClientApplication.resetStage("修改教师信息",scene);
                }
            });
            teacher.getCourseButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String message = HttpRequestUtil.queryTeacherCourse(teacher.getNumber());
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonArray jsonArray = parser.parse(message).getAsJsonArray();
                    ArrayList<Course> courseArrayList = new ArrayList<>();
                    for(JsonElement course : jsonArray){
                        CourseInLogin courseInLogin = gson.fromJson(course, CourseInLogin.class);
                        if(courseInLogin.getTeacher() != null)courseArrayList.add(new Course(1,courseInLogin.getName(),courseInLogin.getNumber(),courseInLogin.getCredit(),courseInLogin.getTeacher().getNumber()+"-"+courseInLogin.getTeacher().getName(),new Button(),new Button(),new Button(),new Button()));
                        else courseArrayList.add(new Course(1,courseInLogin.getName(),courseInLogin.getNumber(),courseInLogin.getCredit(),"",new Button(),new Button(),new Button(),new Button()));
                    }
                    ObservableList<Course> data = FXCollections.observableArrayList();

                    for(int i = 0;i < courseArrayList.size();i++){
                        Course course = courseArrayList.get(i);
                        course.setId(i + 1);
                        data.add(course);
                    }
                    TableColumn<Course,String> nameColumn = new TableColumn<>("课程");
                    TableColumn<Course,String> numberColumn = new TableColumn<>("课序号");
                    TableColumn<Course,String> creditColumn = new TableColumn<>("学分");

                    nameColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
                    numberColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("number"));
                    creditColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("credit"));

                    TableView<Course> tableView = new TableView<>();
                    tableView.getColumns().add(nameColumn);
                    tableView.getColumns().add(numberColumn);
                    tableView.getColumns().add(creditColumn);

                    tableView.setItems(data);

                    Scene scene = new Scene(tableView);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }
            });
        }
        TableColumn<Teacher,String> nameColumn = new TableColumn<>("姓名");
        TableColumn<Teacher,String> idColumn = new TableColumn<>("序号");
        TableColumn<Teacher,String> numberColumn = new TableColumn<>("学工号");
        TableColumn<Teacher,String> genderColumn = new TableColumn<>("性别");
        TableColumn<Teacher,String> collegeColumn = new TableColumn<>("学院");
        TableColumn<Teacher,String> eraseButtonColumn = new TableColumn<>("删除");
        TableColumn<Teacher,String> modifyButtonColumn = new TableColumn<>("修改");
        TableColumn<Teacher,String> courseButtonColumn = new TableColumn<>("查看课程");

        nameColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("id"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("number"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("gender"));
        collegeColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("college"));
        eraseButtonColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("eraseButton"));
        modifyButtonColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("modifyButton"));
        courseButtonColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("courseButton"));

        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(numberColumn);
        table.getColumns().add(genderColumn);
        table.getColumns().add(collegeColumn);
        table.getColumns().add(eraseButtonColumn);
        table.getColumns().add(modifyButtonColumn);
        table.getColumns().add(courseButtonColumn);

        table.setItems(data);
    }

}
