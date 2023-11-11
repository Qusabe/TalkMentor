package com.example.test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
public class Mail {
    public static final String PATH_TO_PROPERTIES = "src/main/resources/com/example/speechsimulator3/mail.properties";

    /** Отправка сообщения с одной почты на другую
     *
     * @param mail
     * @param code
     * @throws IOException
     * @throws MessagingException
     */
    public void sendingLetter(String mail, String code) throws IOException, MessagingException {
        final String username = "ENTER YOUR EMAIL";  // введи свою почту
        final String password = "ENTER YOUR APP PASSWORD";  // введи пароль приложения почты (гугл в помощь)

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ENTER YOUR EMAIL"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail)
            );
            message.setSubject("Speech Simulator");
            message.setText("Ваш код"
                    + "\n\n" + code);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    /** Генерирация 6-ти значного пароля из цифр
     *
     * @return
     */
    public String generateRandomCode() {
        //final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final String chars = "123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
//
        return sb.toString();
    }

    /** Возвращает true, если пользователь с указанной почтой есть в БД
     *
     * @param loginMail
     * @return
     */
    public Boolean mailCheck(String loginMail){
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getMail(loginMail);

        int counter = 0;

        while(true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        //Цикл while перебирает пользователей из БД. Фактически counter всегда будет = 1 т.к.
        //у всех пользователей разная почта (getMail по почте и искал)
        if(counter >=1){
            System.out.println("Пользователь с такой почтой уже зарегестрирован");
            return true;
        }
        else {
            System.out.println("Пользователь с такой почтой не зарегестрирован");
            return false;
        }

    }

}
