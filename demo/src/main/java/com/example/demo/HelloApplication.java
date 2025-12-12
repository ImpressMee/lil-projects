package com.example.demo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        // Root
        Group root = new Group();

        // Scene
        Scene scene = new Scene(root, Color.BLACK);

        Image icon = new Image("file:///C:/Users/justi/OneDrive/Desktop/lil Projects/demo/src/SHAW.jpg");

        // set the Stage
        stage.getIcons().add(icon);
        stage.setWidth((420));
        stage.setHeight(420);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("You cant escape unless you press q");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));

        // Add the Scene to the stage
        stage.setScene(scene);

        // Show the Stage
        stage.setTitle("Hello!");
        stage.show();
    }
}
