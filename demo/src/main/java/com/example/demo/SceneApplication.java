package com.example.demo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneApplication extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the root (Scene Graph)
        Group root = new Group();
        // Text is a Node, so it has to be on the root-node
        Text text = new Text();
        text.setText("WHOOOOAHH!!");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Arial", 50));
        text.setFill(Color.LIMEGREEN);

        // Line Node
        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(200);
        line.setStrokeWidth(5);
        line.setStroke(Color.RED);
        line.setOpacity(0.5);
        line.setRotate(45);

        // Rectangle Node
        Rectangle rectangle = new Rectangle();

        rectangle.setY(100);
        rectangle.setX(100);
        rectangle.setWidth(100);
        rectangle.setHeight(100);
        rectangle.setFill(Color.BLUEVIOLET);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.BLACK);
        rectangle.setRotate(45);

        // Polygon Node
        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(200.0,200.0,
                                    300.0,300.0,
                                    200.0,300.0
                                    );
        triangle.setFill(Color.YELLOW);

        // Circle Node
        Circle circle = new Circle();
        circle.setCenterX(350);
        circle.setCenterY(350);
        circle.setRadius(50);
        circle.setFill(Color.PURPLE);

        // Image Node
        Image image = new Image("file:///C:/Users/justi/OneDrive/Desktop/lil Projects/demo/src/shaw.png");
        // Imageview Node
        ImageView imageview = new ImageView(image);
        imageview.setX(400);
        imageview.setY(400);


        // Add The Nodes to the Root
        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(triangle);
        root.getChildren().add(circle);
        root.getChildren().add(imageview);

        //Set the Scene
        Scene scene = new Scene(root,600,600, Color.LIGHTSKYBLUE);

        //Set the Stage
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }
}
