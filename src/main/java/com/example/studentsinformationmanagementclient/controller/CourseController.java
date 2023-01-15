package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.Course;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.EraseCourseRequest;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CourseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button insertCourseButton;
    @FXML
    private Button queryAllButton;

    @FXML
    private Button queryCourseButton;

    @FXML
    private TextField queryCourseTextField;
    @FXML
    private TableView<Course> table;

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
    public static ObservableList<Course> data = FXCollections.observableArrayList();
    @FXML
    void onQueryAllAction(ActionEvent event) {
        data = FXCollections.observableArrayList();

        for(Course course : UserInformation.courseArrayList){
            data.add(course);
        }

        table.setItems(data);
    }

    @FXML
    void onQueryCourseAction(ActionEvent event) {
        data = FXCollections.observableArrayList();
        String str = queryCourseTextField.getText();
        for(Course course : UserInformation.courseArrayList){
            boolean ok = false;

            if(in(str,course.getName()) || in(str,course.getNumber()) || in(str,""+course.getCredit())){
                //System.out.println(111);
                data.add(course);
            }
        }
        //System.out.println(in("泰山学堂","泰山学堂"));
        table.setItems(data);
    }
    @FXML
    void onInsertCourseAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("insertCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StudentsInformationManagementClientApplication.resetStage("添加课程",scene);
    }
    public static Course courseIsModifying;
    public static String courseIsChecking;
    public static String courseNameIsChecking;
    @FXML
    void initialize() {
        assert insertCourseButton != null : "fx:id=\"insertCourseButton\" was not injected: check your FXML file 'course.fxml'.";
        assert queryAllButton != null : "fx:id=\"queryAllButton\" was not injected: check your FXML file 'course.fxml'.";
        assert queryCourseButton != null : "fx:id=\"queryCourseButton\" was not injected: check your FXML file 'course.fxml'.";
        assert queryCourseTextField != null : "fx:id=\"queryCourseTextField\" was not injected: check your FXML file 'course.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'course.fxml'.";

        data = FXCollections.observableArrayList();

        for(int i = 0; i < UserInformation.courseArrayList.size(); i++){
            Course course = UserInformation.courseArrayList.get(i);
            course.setId(i + 1);
            course.getEraseButton().setText("删除");
            course.getModifyButton().setText("修改");
            course.getScoreButton().setText("查看学生成绩");
            course.getChartButton() .setText("查看条形图");
            data.add(course);
        }

        for(Course course : UserInformation.courseArrayList){
            course.getEraseButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    EraseCourseRequest eraseCourseRequest = new EraseCourseRequest(course.getNumber());
                    String message = HttpRequestUtil.eraseCourse(eraseCourseRequest);

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
                        UserInformation.courseArrayList.remove(course);
                        data.remove(course);
                    }
                }
            });
            course.getModifyButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    courseIsModifying = course;
                    FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("modifyCourse.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 600, 400);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    StudentsInformationManagementClientApplication.resetStage("修改课程信息",scene);
                }
            });
            course.getScoreButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    courseIsChecking = course.getNumber();
                    courseNameIsChecking = course.getName();
                    FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("score.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 600, 400);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    StudentsInformationManagementClientApplication.resetStage(courseIsChecking + "-" + course.getName(),scene);
                }
            });
            course.getChartButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    CategoryAxis xAxis = new CategoryAxis();
                    xAxis.setLabel("分数区间");
                    NumberAxis yAxis = new NumberAxis();
                    yAxis.setLabel("人数/人");
                    BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);
                    barChart.setTitle("学生成绩条形图");
                    ObservableList<XYChart.Series<String,Number>> chartData = StudentXYChartDataUtil.getChartData(course.getNumber());
                    barChart.setData(chartData);
                    StackPane root = new StackPane(barChart);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("学生成绩条形图");
                    stage.show();
                }
            });
        }
        TableColumn<Course,String> nameColumn = new TableColumn<>("课程");
        TableColumn<Course,String> idColumn = new TableColumn<>("序号");
        TableColumn<Course,String> numberColumn = new TableColumn<>("课序号");
        TableColumn<Course,String> creditColumn = new TableColumn<>("学分");
        TableColumn<Course,String> teacherColumn = new TableColumn<>("教师");
        TableColumn<Course,String> eraseButtonColumn = new TableColumn<>("删除");
        TableColumn<Course,String> modifyButtonColumn = new TableColumn<>("修改");
        TableColumn<Course,String> scoreColumn = new TableColumn<>("查看学生成绩");
        TableColumn<Course,String> chartColumn = new TableColumn<>("查看成绩条形图");

        nameColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("id"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("number"));
        creditColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("credit"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("teacherNumberName"));
        eraseButtonColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("eraseButton"));
        modifyButtonColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("modifyButton"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("scoreButton"));
        chartColumn.setCellValueFactory(new PropertyValueFactory<>("chartButton"));

        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(numberColumn);
        table.getColumns().add(creditColumn);
        table.getColumns().add(teacherColumn);
        table.getColumns().add(eraseButtonColumn);
        table.getColumns().add(modifyButtonColumn);
        table.getColumns().add(scoreColumn);
        table.getColumns().add(chartColumn);

        table.setItems(data);
    }

}
