package com.example.studentsinformationmanagementclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FirstPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private ArrayList<Flake> flakes = new ArrayList<>(2000);
    private int flakeNum = 1200;
    private int w = 750;
    private int h = 550;
    private Random random = new Random();
    @FXML
    private AnchorPane rootPane;


    static final Image FLAKE_IMG = new Image(
            FirstPageController.class.getResource("/com/example/studentsinformationmanagementclient/snow.png").toExternalForm(),
            15, 15, true, true);

    class Flake extends ImageView {
        private double verSpeed;
        private double horSpeed;

        public Flake() {
            super(FLAKE_IMG);
        }

        public double getHorSpeed() {
            return horSpeed;
        }

        public void setHorSpeed(double horSpeed) {
            this.horSpeed = horSpeed;
        }

        public double getVerSpeed() {
            return verSpeed;
        }

        public void setVerSpeed(double verSpeed) {
            this.verSpeed = verSpeed;
        }
    }

    public void initSnow() throws IOException {
        for (int i = 0; i < flakeNum; i++) {
            Flake flake = new Flake();
            flake.setX(random.nextDouble()*w);
            flake.setY(random.nextDouble()*h);
            //设置速度
            flake.setVerSpeed(random.nextDouble()*2.5+0.5);
            flake.setHorSpeed((random.nextDouble()-0.5)*10);
            //设置尺寸
            flake.setScaleX(random.nextDouble()*0.5+0.6);
            flake.setScaleY(flake.getScaleX());
            //设置不透明度，即消融效果
            flake.setOpacity(1-flake.getY()/h);
            //设置旋转
            flake.setRotate(random.nextDouble()*360);
            flakes.add(flake);
        }
        rootPane.getChildren().addAll(flakes);

    }


    @FXML
    void initialize() throws IOException {
        initSnow();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Flake flake : flakes) {
                    flake.setRotate((flake.getRotate()+3)%360);
                    flake.setY(flake.getY()+flake.getVerSpeed());
                    if(flake.getY() > h){
                        flake.setX(random.nextDouble()*w);
                        flake.setY(0);
                        flake.setVerSpeed(random.nextDouble()*2.5+0.5);
                        flake.setScaleX(random.nextDouble()*0.5+0.6);
                        flake.setScaleY(flake.getScaleX());

                    }
                    flake.setOpacity(1-flake.getY()/h);
                }
            }
        };
        animationTimer.start();
    }

}
