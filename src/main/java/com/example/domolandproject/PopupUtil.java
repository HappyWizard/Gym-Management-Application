package com.example.domolandproject;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class PopupUtil {

    public static void showCustomPopup(String message, String buttonCaption, String path) {
        // Create a new stage for the pop-up
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.TRANSPARENT);  // No title bar or borders


        VBox content = new VBox(40);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 30;");
        content.setPrefSize(600, 200); // Set preferred width and height

        // Add a colored border
        content.setBorder(new Border(new BorderStroke(
                Color.rgb(79, 45, 0),  // Border color
                BorderStrokeStyle.SOLID, // Border style
                new CornerRadii(25),      // Border radius
                new BorderWidths(5)       // Border width
        )));
        // Add an image to the pop-up
        ImageView icon = new ImageView(new Image(path));  // Replace with your image path
        icon.setPreserveRatio(true);  // Maintain the aspect ratio of the image
        icon.setFitWidth(300);
        icon.setFitHeight(250);

        // Add a label for the message
        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);  // Enable text wrapping
        messageLabel.setAlignment(Pos.CENTER);  // Center text horizontally
        messageLabel.setStyle("-fx-text-fill: black");

        HBox closeButtonBox = new HBox();
//        closeButtonBox.setAlignment(Pos.TOP_RIGHT);
        closeButtonBox.setStyle("-fx-padding: 10;"); // Add padding around the button

        // Add a close button
        Button closeButton = new Button(buttonCaption);
        closeButton.setOnAction(e -> popupStage.close());
        closeButton.setStyle(
                "-fx-background-color: #fcba03; " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 20px; " +
                        "-fx-font-family: 'Verdana'; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 16px; "
        );

        // Add hover effect
        closeButton.setOnMouseEntered(e -> closeButton.setStyle(
                "-fx-background-color: #f5a623; " +  // Lighter color for hover
                        "-fx-text-fill: white; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 20px; " +
                        "-fx-font-family: 'Verdana'; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 16px; "
        ));
        closeButton.setOnMouseExited(e -> closeButton.setStyle(
                "-fx-background-color: #fcba03; " +  // Original color
                        "-fx-text-fill: white; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 20px; " +
                        "-fx-font-family: 'Verdana'; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 16px; "
        ));


        closeButtonBox.getChildren().add(closeButton);
        messageLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        closeButton.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        // Add all elements to the content box
        content.getChildren().addAll(icon, messageLabel, closeButton);

        // Create a scene and set it on the stage
        Scene scene = new Scene(content, 650, 1000);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);

        Image appIcon = new Image("C:/Users/eugen/Documents/Java/DomolandProject/Images/ice-cream-pom.png");
        popupStage.getIcons().add(appIcon);
        popupStage.setTitle("Message for you!");
        popupStage.setScene(scene);
        popupStage.setX(1920/2 - 525);  // Adjust as needed
        popupStage.setY(1080/2 - 420);  // Adjust to position off-screen at the bottom

        popupStage.setWidth(650);
        popupStage.setHeight(500);

        // Show the pop-up
        popupStage.show();

        // Create a TranslateTransition for the slide-up effect
        TranslateTransition transition = new TranslateTransition(Duration.millis(1000), content);
        transition.setFromY(1080);  // Start from off-screen position
        transition.setToY(0);    // Slide up to this position
        transition.play();
    }
}