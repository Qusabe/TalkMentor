package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignIn {
    @FXML
    Button EnterB;

    @FXML
    Button registerB;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    private Stage stage;
    private static String previousWindow = "main_window.fxml";
    public static String exportMail;
    private Scene scene;
    private static int counter = 0;
    private Parent root;
    private static Boolean isOnline;
    @FXML
    private Button codeReb;

    /** Переход к восстановлению пароля
     *
     * @param event
     * @throws IOException
     */
    public void gotoRebut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("куиге.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** Переход к регистрации
     *
     * @param event
     * @throws IOException
     */
    public void gotoRegister(ActionEvent event) throws  IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** Вход в аккаунт и переход в главное меню
     *
     * @param event
     * @throws IOException
     */
    public void gotoMain(ActionEvent event) throws IOException {
        String loginMail = loginField.getText();
        String loginPassword = passwordField.getText();
        processLoginUser(loginMail, loginPassword);
        if (counter >= 1) {
            exportMail = loginMail;
            Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        // добавить появление текста, что неправильно заполнены поля
    }

    public static String getPreviousWindow() {
        return previousWindow;
    }

    public static void setPreviousWindow(String name) {
        previousWindow = name;
    }
    /** Проверка, есть ли пользователь в БД
     *
     * @param loginMail
     * @param loginPassword
     */
    private static void processLoginUser(String loginMail, String loginPassword){
        if(!loginMail.equals("") && !loginPassword.equals(""))
            loginUser(loginMail, loginPassword);
//        else
//            System.out.println("Заполните все поля!");
    }

    /** Обращается к БД и проверяет, есть ли в ней строка с данными логином и паролем
     *
     * @param loginMail
     * @param loginPassword
     */
    private   static void loginUser(String loginMail, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getUser(loginMail, loginPassword);//обращаемся к функции getUser из DatabaseHandler


        while(true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        exportMail = loginMail;
        //Цикл while перебирает пользователей из БД. Фактически counter всегда будет = 1 т.к.
        //у всех пользователей разная почта (getUser по почте и искал)
//        if(counter >=1){
//            gotoMain();
//        }
//        else
//            System.out.println("Такого пользователя нет");
    }

    public static Boolean getStatus() {
        return isOnline;
    }
    public static String getCurrentMail() {
        return exportMail;
    }

    public static void setStatus(Boolean status) {
        isOnline = status;
    }
}
