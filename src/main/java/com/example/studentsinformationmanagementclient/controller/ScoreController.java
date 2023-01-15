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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScoreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button insertButton;

    @FXML
    private Button returnButton;

    @FXML
    private TableView<Score> table;

    @FXML
    void onInsertAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("insertScore.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StudentsInformationManagementClientApplication.resetStage("添加学生成绩",scene);
    }

    @FXML
    void onReturnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 940, 590);
        StudentsInformationManagementClientApplication.resetStage("学生信息管理系统",scene);
    }
    public static Score scoreIsModifying;
    @FXML
    void initialize() {
        assert insertButton != null : "fx:id=\"insertButton\" was not injected: check your FXML file 'score.fxml'.";
        assert returnButton != null : "fx:id=\"returnButton\" was not injected: check your FXML file 'score.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'score.fxml'.";
        String message = HttpRequestUtil.queryCourseStudent(CourseController.courseIsChecking);
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
            score.getEraseButton().setText("删除");
            score.getModifyButton().setText("修改");
            score.getEraseButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String message = HttpRequestUtil.eraseStudentScore(score.getStudentNumber(),score.getCourseNumber());

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
                        scoreArrayList.remove(score);
                        data.remove(score);
                    }
                }
            });
            score.getModifyButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    scoreIsModifying = score;
                    FXMLLoader fxmlLoader = new FXMLLoader(StudentsInformationManagementClientApplication.class.getResource("modifyScore.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 600, 400);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    StudentsInformationManagementClientApplication.resetStage("修改学生",scene);
                }
            });
        }
        TableColumn<Score,String> nameColumn = new TableColumn<>("学生");
        TableColumn<Score,String> scoreColumn = new TableColumn<>("分数");
        TableColumn<Score,String> eraseButtonColumn = new TableColumn<>("删除");
        TableColumn<Score,String> modifyButtonColumn = new TableColumn<>("修改");


        nameColumn.setCellValueFactory(new PropertyValueFactory<Score,String>("student"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Score,String>("score"));
        eraseButtonColumn.setCellValueFactory(new PropertyValueFactory<Score,String>("eraseButton"));
        modifyButtonColumn.setCellValueFactory(new PropertyValueFactory<Score,String>("modifyButton"));

        table.getColumns().add(nameColumn);
        table.getColumns().add(scoreColumn);
        table.getColumns().add(eraseButtonColumn);
        table.getColumns().add(modifyButtonColumn);

        table.setItems(data);
    }

}
