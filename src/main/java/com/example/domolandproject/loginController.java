package com.example.domolandproject;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class loginController {

    @FXML
    private Button closeBlack;
    @FXML
    private Button closeWhite;
    @FXML
    private Button closeRed;
    @FXML
    private Button create_account_btn;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_username;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button moving_login_btn;

    @FXML
    private Button signup_btn;

    @FXML
    private TextField signup_email;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private PasswordField signup_password;

    @FXML
    private TextField signup_username;
    @FXML
    private AnchorPane sub_form;
    // DATABASE Tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;
    public void login(){

        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";

        connect = DataBase.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1,login_username.getText());
            prepare.setString(2,login_password.getText());

            result = prepare.executeQuery();

            Alert alert;
            if (login_username.getText().isEmpty() || login_password.getText().isEmpty()){
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Please fill in all the blank fields");
//                alert.show();
                PopupUtil.showCustomPopup("Please fill in all the blank fields", "OK","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/angelPom.png");

                if (login_btn.getStyleClass().contains("selected")) {
                    login_btn.getStyleClass().remove("selected");
                }
            }else{
                if(result.next()){

                    data.username = login_username.getText();
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Login!");
//                    alert.showAndWait();
                    loadDashboardPage();

                }else{
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Wrong username or password");
//                    alert.show();

                    PopupUtil.showCustomPopup("Wrong username or password", "Try again","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/hmm.png");


                    if (login_btn.getStyleClass().contains("selected")) {
                        login_btn.getStyleClass().remove("selected");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void signUp(){

        String sql = "INSERT INTO admin (email, username, password) VALUES(?,?,?)";

        connect = DataBase.connectDb();

        try{
            Alert alert;

            if (signup_email.getText().isEmpty() || signup_username.getText().isEmpty() || signup_password.getText().isEmpty()){
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Please fill in all blank fields");
//                alert.showAndWait();
                PopupUtil.showCustomPopup("Please fill in all the blank fields", "OK","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/angelPom.png");

            }else{
                if (signup_password.getText().length() < 8){
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Invalid password (must be more than 7 characters)");
//                    alert.showAndWait();
                    PopupUtil.showCustomPopup("Invalid password (must be more than 7 characters)", "Try Again","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/angelPom.png");

                }else{
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, signup_email.getText());
                    prepare.setString(2, signup_username.getText());
                    prepare.setString(3, signup_password.getText());

//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully created new account!");
//                    alert.showAndWait();

                    PopupUtil.showCustomPopup("Successfully created new account!", "Great!","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/shoppingPom.png");


                    prepare.executeUpdate();
                    signup_email.setText("");
                    signup_username.setText("");
                    signup_password.setText("");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void signUpSlider(){

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sub_form);
        slider1.setToX(300);
        slider1.setDuration(Duration.seconds(0.2));
        slider1.play();

        slider1.setOnFinished((ActionEvent event) -> {
            create_account_btn.setVisible(false);
            moving_login_btn.setVisible(true);
            closeBlack.setVisible(false);
        });
        signup_email.requestFocus();
    }
    public void loginSlider(){
        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sub_form);
        slider1.setToX(0);
        slider1.setDuration(Duration.seconds(0.2));
        slider1.play();

        slider1.setOnFinished((ActionEvent event) -> {
            create_account_btn.setVisible(true);
            moving_login_btn.setVisible(false);
            closeBlack.setVisible(true);
        });
        login_username.requestFocus();
    }
    public void close(){
        javafx.application.Platform.exit();
    }

    @FXML
    public void initialize(){

        // Add a key event listener to the root pane to listen for the Enter key
        login_username.setOnKeyPressed(this::handleEnterKeyPressForLogin);
        login_password.setOnKeyPressed(this::handleEnterKeyPressForLogin);

        signup_email.setOnKeyPressed(this::handleEnterKeyPressForSignUp);
        signup_username.setOnKeyPressed(this::handleEnterKeyPressForSignUp);
        signup_password.setOnKeyPressed(this::handleEnterKeyPressForSignUp);

        closeRed.setVisible(false);
        closeBlack.setVisible(true);
        closeWhite.setVisible(true);

        closeRed.setOnMouseEntered((MouseEvent event) -> {
            closeRed.setVisible(true);
        });
        closeRed.setOnMouseExited((MouseEvent event) -> {
            closeRed.setVisible(false);
        });
        closeBlack.setOnMouseEntered((MouseEvent event) -> {
            closeRed.setVisible(true);
        });
        closeBlack.setOnMouseExited((MouseEvent event) -> {
            closeRed.setVisible(false);
        });
        closeWhite.setOnMouseEntered((MouseEvent event) -> {
            closeRed.setVisible(true);
        });
        closeWhite.setOnMouseExited((MouseEvent event) -> {
            closeRed.setVisible(false);
        });

    }
    private void handleEnterKeyPressForLogin(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            event.consume();
            // Add the "selected" style class
            // this effect not shown cuz the dashboard loads too quickly, there's not enof time for javvafx to render the changes
            login_btn.getStyleClass().add("selected");
            PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust duration as needed
            pause.setOnFinished(e -> login());
            pause.play();

        }
    }
    private void handleEnterKeyPressForSignUp(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            event.consume();
            signUp();
        }
    }
    private void loadDashboardPage(){

        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

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
            Image icon = new Image("file:C:/Users/eugen/Documents/Java/DomolandProject/Images/ice-cream-pom.png");

            stage.getIcons().add(icon);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

            login_btn.getScene().getWindow().hide();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}