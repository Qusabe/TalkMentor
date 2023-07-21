package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class Profile {
    @FXML
    private Button profileB; //кнопка возвращения в профиль

    @FXML
    private Button changePassB; //кнопка смены пароля

    @FXML
    private Button quitB; //кнопка выхода из акка

    @FXML
    private Button delAccB; //кнопка удаления акка

    public Stage stage;
    private boolean isOnline;

    public void setStage(Stage stage) {
        this.stage = stage;
    }



    //переход в профиль
    public void toMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //переход на смену пароля
    public void handleChangePass(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("rebut.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);;
//            MWcontroller.setPreviousWindow("account_management.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //переход на меню входа (вышли из аккаунта)
    public void handleQuit(ActionEvent event) {
        try {
            SignIn.setStatus(false);
            Parent root = FXMLLoader.load(getClass().getResource("sign_in.fxml"));
            // как-то выйти из акка
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();


            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //переходим на удаление акка
    public void handleDeleteAcc(ActionEvent event) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.deleteUser(SignIn.getCurrentMail());
    }


}