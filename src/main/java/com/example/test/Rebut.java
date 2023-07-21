package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;


public class Rebut {
    @FXML
    private  TextField codeField;
    private String cod;
    private String Mail;
    @FXML
    private TextField newPasswordField;

    @FXML
    private Button backB;
    @FXML
    private Button rebutB;
    @FXML
    private Label incorrectCode;

    private Scene scene;
    private Parent root;
    @FXML
    public Label codSav;

    private static Stage stage;
    public static String expCode;
    public static String expMail;
    @FXML
    private TextField mailField;
    @FXML
    private Button pushCodeB;
    @FXML
    private Label incorrectMail;


    /** Если почта для восстановления пароля введена, то вызывает processChangePassword
     *
     * @param event
     * @throws IOException
     */
    public void rebut(ActionEvent event) throws  IOException{

        String mail = mailField.getText();

        if (mail!="") {
            try {
                processChangePassword(mail, event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /** Возвращает сгенерированный код
     *
     * @return expCode
     */


    /** Возвращает введённую почту
     *
     * @return expMail
     */


    /**Если введеная почта есть в БД, то генерируется случайный код и отправляется на почту, после чего
     * перходим к окну password_rebut_part2
     *
     * @param mail
     * @param event
     * @throws MessagingException
     * @throws IOException
     */
    private void processChangePassword(String mail, ActionEvent event)
            throws MessagingException, IOException {
        Mail dbMail = new Mail();
        if (dbMail.mailCheck(mail) == true) { //обращаемся к функции mailCheck из Mail
            //условие выполняеется если пользователь с указанной почтой зарегестрирован

            //Генерируем код и скидываем его на указанную почту
            String code = dbMail.generateRandomCode();
            dbMail.sendingLetter(mail, code);
            System.out.println("Проверьте почту");

            try {
                Parent root = FXMLLoader.load(getClass().getResource("password_rebut_part2.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Пользователь с такой почтой не зарегестрирован");
            incorrectMail.setVisible(true);
        }
    }

    public void toMain(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void processChangePassword2(String code, String myCode, String myPassword, String mail, ActionEvent event)  {
        DatabaseHandler dbHandler = new DatabaseHandler();

        System.out.println(code);
        System.out.println(myCode);
        System.out.println(myPassword);
        System.out.println(mail);
        if (Integer.parseInt(code) == Integer.parseInt(myCode)) {
            //Условие выполняется, если сгенерированный код и код, написанный пользователем, совпадает
            //В таком случае пользователь вводит новый пароль и функция changePassword меняет его
//            System.out.println("Введите новый пароль");

            dbHandler.changePassword(mail, myPassword);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Неверный код");
            incorrectCode.setVisible(true);
        }
    }

    /** Возвращение к предыдущему меню генерации кода
     *
     * @param event
     * @throws IOException
     */
}





