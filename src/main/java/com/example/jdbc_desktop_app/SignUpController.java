package com.example.jdbc_desktop_app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button b_signup;
    @FXML
    private Button b_goback;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event, tf_username.getText(), tf_password.getText());
                } else{
                    System.out.println("Fill in all informations.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Fill all infos to sign up.");
                    alert.show();
                }
            }
        });
        b_goback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               DBUtils.changeScene(event, "hello-view.fxml", "Logged in!", null);

            }
        });
    }
}
