package com.example.studentsinformationmanagementclient.controller;

import com.example.studentsinformationmanagementclient.model.Score;
import com.example.studentsinformationmanagementclient.model.Student;
import com.example.studentsinformationmanagementclient.model.UserInformation;
import com.example.studentsinformationmanagementclient.request.HttpRequestUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StudentXYChartDataUtil {
    public static ObservableList<XYChart.Series<String, Number>> getChartData(String courseNumber) {

        String message = HttpRequestUtil.queryCourseStudent(courseNumber);
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(message).getAsJsonArray();
        ArrayList<Score> scoreArrayList = new ArrayList<>();
        int sum1 = 0,sum2 = 0,sum3 = 0,sum4 = 0,sum5 = 0;
        for(JsonElement scores : jsonArray){
            ScoreInLogin scoreInLogin = gson.fromJson(scores, ScoreInLogin.class);
            int score = scoreInLogin.getScore();
            if(score >= 0 && score <= 59)sum1++;
            else if(score >= 60 && score <= 69)sum2++;
            else if(score >= 70 && score <= 79)sum3++;
            else if(score >= 80 && score <= 89)sum4++;
            else sum5++;
        }
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        //series.setName("1950");
        series.getData().addAll(new XYChart.Data<>("0~59",sum1),
                new XYChart.Data<>("60~69", sum2),
                new XYChart.Data<>("70~79", sum3),
                new XYChart.Data<>("80~89", sum4),
                new XYChart.Data<>("90及以上", sum5));
        ObservableList<XYChart.Series<String, Number>> data =
                FXCollections.<XYChart.Series<String, Number>>observableArrayList();
        data.addAll(series);
        return data;
    }
}
