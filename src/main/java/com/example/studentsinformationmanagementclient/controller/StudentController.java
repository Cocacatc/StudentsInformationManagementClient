package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.studentsinformationmanagementclient.StudentsInformationManagementClientApplication;
import com.example.studentsinformationmanagementclient.model.Score;
import com.example.studentsinformationmanagementclient.model.Student;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.EraseStudentRequest;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.example.studentsinformationmanagementclient.request.ModifyStudentRequest;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button insertStudentButton;

    @FXML
    private TableView<Student> table;

    @FXML
    private TextField QueryTextField;

    @FXML
    private Button queryStudentButton;

    @FXML
    void onInsertStudentButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("insertStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StudentsInformationManagementClientApplication.resetStage("添加学生",scene);
    }
    //public static ObservableList<Student> data;

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
    public static ObservableList<Student> data = FXCollections.observableArrayList();
    @FXML
    void onQueryStudentAction(ActionEvent event) {
        data = FXCollections.observableArrayList();
        String str = QueryTextField.getText();
        for(Student student : UserInformation.studentArrayList){
            boolean ok = false;
            if(in(str,student.getName()) || in(str,student.getNumber()) || in(str,student.getCollege()) || in(str,student.getGender())){
                //System.out.println(111);
                data.add(student);
            }
        }
        //System.out.println(in("泰山学堂","泰山学堂"));
        table.setItems(data);
    }
    @FXML
    private Button queryAllButton;
    @FXML
    void onQueryAllButton(ActionEvent event) {
        data = FXCollections.observableArrayList();

        for(Student student : UserInformation.studentArrayList){
            data.add(student);
        }

        table.setItems(data);
    }
//    @FXML
//    private Button chartButton;
//    @FXML
//    void onChartAction(ActionEvent event) throws IOException {
//        CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setLabel("分数区间");
//        NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("人数/人");
//        BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);
//        barChart.setTitle("学生成绩条形图");
//        ObservableList<XYChart.Series<String,Number>> chartData = StudentXYChartDataUtil.getChartData();
//        barChart.setData(chartData);
//        StackPane root = new StackPane(barChart);
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.setTitle("学生成绩条形图");
//        stage.show();
//    }
    public static Student studentIsModifying;
    public static String studentIsChecking;
    @FXML
    void initialize() {
        assert insertStudentButton != null : "fx:id=\"insertStudentButton\" was not injected: check your FXML file 'student.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'student.fxml'.";
        assert QueryTextField != null : "fx:id=\"QueryTextField\" was not injected: check your FXML file 'student.fxml'.";
        assert queryStudentButton != null : "fx:id=\"queryStudentButton\" was not injected: check your FXML file 'student.fxml'.";
        assert queryAllButton != null : "fx:id=\"queryAllButton\" was not injected: check your FXML file 'student.fxml'.";
        //assert chartButton != null : "fx:id=\"chartButton\" was not injected: check your FXML file 'student.fxml'.";

        data = FXCollections.observableArrayList();

        for(int i = 0;i < UserInformation.studentArrayList.size();i++){
            Student student = UserInformation.studentArrayList.get(i);
            student.setId(i + 1);
            student.getEraseButton().setText("删除");
            student.getModifyButton().setText("修改");
            student.getScoreButton().setText("查看各科成绩");
            data.add(student);
        }

        for(Student student : UserInformation.studentArrayList){
            student.getEraseButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    EraseStudentRequest eraseStudentRequest = new EraseStudentRequest(student.getNumber());
                    String message = HttpRequestUtil.eraseStudent(eraseStudentRequest);

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
                        UserInformation.studentArrayList.remove(student);
                        data.remove(student);
                    }
                }
            });
            student.getModifyButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    studentIsModifying = student;
                    FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("modifyStudent.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 600, 400);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    StudentsInformationManagementClientApplication.resetStage("修改学生",scene);
                }
            });
            student.getScoreButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String message = HttpRequestUtil.queryStudentScore(student.getNumber());
//                    System.out.println(666);
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonArray jsonArray = parser.parse(message).getAsJsonArray();
                    ArrayList<Score> scoreArrayList = new ArrayList<>();
                    for(JsonElement score : jsonArray){
                        ScoreInLogin scoreInLogin = gson.fromJson(score, ScoreInLogin.class);
                        scoreArrayList.add(new Score(1,scoreInLogin.getStudent().getNumber(),scoreInLogin.getCourse().getNumber(), scoreInLogin.getScore(),new Button(),new Button()));
                    }
                    ObservableList<Score> data = FXCollections.observableArrayList();

                    for(int i = 0;i < scoreArrayList.size();i++){
                        Score score = scoreArrayList.get(i);
                        score.setId(i + 1);
                        data.add(score);
                    }
                    TableColumn<Score,String> nameColumn = new TableColumn<>("课程");
                    TableColumn<Score,String> scoreColumn = new TableColumn<>("分数");

                    nameColumn.setCellValueFactory(new PropertyValueFactory<Score,String>("course"));
                    scoreColumn.setCellValueFactory(new PropertyValueFactory<Score,String>("score"));

                    TableView<Score> tableView = new TableView<>();
                    tableView.getColumns().add(nameColumn);
                    tableView.getColumns().add(scoreColumn);

                    tableView.setItems(data);

                    Scene scene = new Scene(tableView);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }
            });
        }
        TableColumn<Student,String> nameColumn = new TableColumn<>("姓名");
        TableColumn<Student,String> idColumn = new TableColumn<>("序号");
        TableColumn<Student,String> numberColumn = new TableColumn<>("学号");
        TableColumn<Student,String> genderColumn = new TableColumn<>("性别");
        TableColumn<Student,String> collegeColumn = new TableColumn<>("学院");
        TableColumn<Student,String> eraseButtonColumn = new TableColumn<>("删除");
        TableColumn<Student,String> modifyButtonColumn = new TableColumn<>("修改");
        TableColumn<Student,String> scoreButtonColumn = new TableColumn<>("查看各科成绩");

        nameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("number"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("gender"));
        collegeColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("college"));
        eraseButtonColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("eraseButton"));
        modifyButtonColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("modifyButton"));
        scoreButtonColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("scoreButton"));

        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(numberColumn);
        table.getColumns().add(genderColumn);
        table.getColumns().add(collegeColumn);
        table.getColumns().add(eraseButtonColumn);
        table.getColumns().add(modifyButtonColumn);
        table.getColumns().add(scoreButtonColumn);

        table.setItems(data);
    }

}
