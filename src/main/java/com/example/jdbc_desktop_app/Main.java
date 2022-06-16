package com.example.jdbc_desktop_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
// ORM, Testy,
// https://www.youtube.com/watch?v=ltX5AtW9v30
// https://www.youtube.com/watch?v=e8g9eNnFpHQ
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Szef246");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from people");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("first_name"));
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }


        launch();

    }
}