package com.example.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sign_in.fxml"));

        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        // Установка масштабирования Scene в соответствие с размерами экрана
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true); // Установка полноэкранного режима
        primaryStage.show();
    }
}
