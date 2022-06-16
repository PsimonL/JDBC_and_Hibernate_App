package com.example.jdbc_desktop_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String tilte, String username){
        Parent root = null;

        if(username != null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation((username));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try{
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(tilte);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public static void signUpUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultset = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Szef246");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM app_users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultset = psCheckUserExists.executeQuery();
            if (resultset.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cant use this name.");
                alert.show();
            } else{
                psInsert = connection.prepareStatement("INSERT INTO app_users (username, password) VALUES (?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                changeScene(event, "logged-in.fxml", "Logged into account!", username);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(resultset != null){
                try{
                    resultset.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Szef246");
            preparedStatement = connection.prepareStatement("SELECT password FROM app_users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultset = preparedStatement.executeQuery();
            if(!resultset.isBeforeFirst()){
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else{
                while(resultset.next()){
                    String retrivePassword = resultset.getString("password");
                    if(retrivePassword.equals(password)){
                        changeScene(event, "logged-in.fxml", "You have been successfully logged in!", username);
                    } else{
                        System.out.println("Password didnt match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(resultset != null){
                try{
                    resultset.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
