package com.example.demo;

import javafx.application.Application;

import static javafx.application.Application.launch;

public class Launch {
    public static void main(String[] args) {
        //Application.launch(HelloApplication.class, args);
        //Application.launch(SceneApplication.class, args);
        Application.launch(FXMLApplication.class, args);
    }
}
