package com.example.domolandproject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DomolandApp extends Application {

    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();

        // Set a background color for the root node (optional, for visibility of glow)
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // Semi-transparent black background

        // Create a DropShadow effect to simulate a glow
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(javafx.scene.paint.Color.CYAN); // Change to any color you prefer
        dropShadow.setRadius(20); // Adjust the glow radius
        dropShadow.setSpread(0.5); // Adjust how much the glow spreads

        // Apply the DropShadow effect to the root node
        root.setEffect(dropShadow);

        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT); // Ensure scene is transparent

        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(1);
        });

        root.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1);
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Welcome to Domoland!");

        Image icon = new Image("file:C:/Users/eugen/Documents/Java/DomolandProject/Images/app-icon.png");

        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}