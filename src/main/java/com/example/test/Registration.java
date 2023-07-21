package com.example.test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration  {
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;
    @FXML
    private TextField codeField;

    private Stage stage;
    private static String code;
    private static String myCode;
    private Scene scene;
    private Parent root;
    private static String login;
    private static String mail;
    private static boolean flag;
    private static String password;
    @FXML
    private Button backButton;

    /** Переход в меню авторизации
     *
     * @param event
     * @throws IOException
     */
    public void backToLog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sign_in.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static String getLogn() {
        return login;
    }

    public static String getMal() {
        return mail;
    }

    public static  String getPasswrd() {
        return password;
    }

    public static String getCod(){
        return code;
    }
    /** Отправка кода подтверждения после ввода логина, почты и пароля
     *
     * @param event
     * @throws IOException
     * @throws MessagingException
     */
    public void pushCode(ActionEvent event) throws IOException, MessagingException {
        login = loginField.getText();
        mail = emailField.getText();
        password = passwordField.getText();

        String password = passwordField.getText();
        if (!login.equals("") && !password.equals("") && !mail.equals("")) {
            processUserRegistration(mail);
            try {
                // ошибка location is required в 86 строке
                Parent root = FXMLLoader.load(getClass().getResource("register2.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }





    /** Отправка сообщения на почту, если её нет в базе данных
     *
     * @param mail
     * @throws MessagingException
     * @throws IOException
     */
    private static void processUserRegistration(String mail)
            throws MessagingException, IOException {
        Mail dbMail = new Mail();
        if (dbMail.mailCheck(mail) == false) {
            //условие выполняеется если пользователь с указанной почтой не зарегестрирован

            //Генерируем код и скидываем его на указанную почту
            code = dbMail.generateRandomCode();
            dbMail.sendingLetter(mail, code);
//            System.out.println("Проверьте почту"); //добавить надпись на экран

        }


    }
}