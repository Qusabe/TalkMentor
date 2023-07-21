package com.example.test;
//import jdk.jshell.spi.ExecutionControl;


import java.sql.*;

public class DatabaseHandler extends configs {
    Connection dbConnection;


    /**
     * Подключение к Базе Данных
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection getDbConnection()
            throws  SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }


    /** Регистрация пользователя (добавление его в БД)
     *
     * @param userName
     * @param mail
     * @param password
     */
    public void signUpUser( String userName, String mail, String password){
        String insert = "INSERT INTO users ( username , mail , password ) VALUES (?,?,?)";
        //Кодом далее описываем, что подразумеваем вместо знаков вопросов и выполняем наше действие
        try{
            try (PreparedStatement prSt = getDbConnection().prepareStatement(insert)) {
                prSt.setString(1, userName);
                prSt.setString(2, mail);
                prSt.setString(3, password);

                prSt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Создаём вторую таблицу БЗ (прогресс пользователя)
    public void creatingUserProgress(String id){
        String insert = "INSERT INTO user_information ( idusers, exercises, simulators ) VALUES (?,'-0-','-0-')";
        //Кодом далее описываем, что подразумеваем вместо знаков вопросов и выполняем наше действие
        try{
            try (PreparedStatement prSt = getDbConnection().prepareStatement(insert)) {
                prSt.setString(1, id);


                prSt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
//        } catch (ExecutionControl.ClassInstallException e) {
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //Изменяем информацию во второй таблице БЗ во второй колонке
    public void progressUserExercises(String mail, String progress){
        try{
            String url = "jdbc:mysql://" + dbHost + ":"
                    + dbPort + "/" + dbName;
            String username = dbUser;
            String password = dbPass;
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String sql = "UPDATE user_information SET exercises = ? WHERE idusers = ?";
                //Кодом далее описываем, что подразумеваем вместо знаков вопросов и выполняем наше действие
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, getProgressExercises(mail)+progress+"-");
                preparedStatement.setString(2, getIdUsers(mail));

                preparedStatement.executeUpdate();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    //Получаем информацию о второй колонке (упражнения) пользователя через почту пользователя
    public String getProgressExercises(String mail) throws SQLException {
        ResultSet resSet = null;

        String select = "SELECT * FROM user_information WHERE idusers =?";
        //Кодом далее описываем, что подразумеваем вместо знаков вопросов и выполняем наше действие
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, getIdUsers(mail));

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
//        } catch (ExecutionControl.ClassInstallException e) {
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ResultSet result = resSet;

        String exercises = null;
        while (result.next()) {
            exercises = result.getString(2);
        }

        return exercises;
    }
    //Изменяем информацию во второй таблице БЗ в третьей колонке
    public void progressUserSimulators(String mail, String progress){
        try{
            String url = "jdbc:mysql://" + dbHost + ":"
                    + dbPort + "/" + dbName;
            String username = dbUser;
            String password = dbPass;
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String sql = "UPDATE user_information SET simulators = ? WHERE idusers = ?";
                //Кодом далее описываем, что подразумеваем вместо знаков вопросов и выполняем наше действие
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, getProgressSimulators(mail)+progress);
                preparedStatement.setString(2, getIdUsers(mail));

                preparedStatement.executeUpdate();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
    //Получаем информацию о третьей колонке (упражнения) пользователя через почту пользователя
    public String getProgressSimulators(String mail) throws SQLException {
        ResultSet resSet = null;

        String select = "SELECT * FROM user_information WHERE idusers =?";
        //Кодом далее описываем, что подразумеваем вместо знаков вопросов и выполняем наше действие
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, getIdUsers(mail));

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
//        } catch (ExecutionControl.ClassInstallException e) {
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ResultSet result = resSet;

        String simulators = null;
        while (result.next()) {
            simulators = result.getString(3);
        }

        return simulators;
    }

    /** Получение данных о пользователе по mail(почте) и password
     *
     * @param mail
     * @param password
     * @return
     */
    public ResultSet getUser(String mail, String password){
        ResultSet resSet = null;

        String select = "SELECT * FROM users WHERE mail =? AND password=?";
        //Кодом далее описываем, что подразумеваем вместо знаков вопросов и выполняем наше действие
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, mail);
            prSt.setString(2, password);


            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }


    /** Получение данных о пользователе по mail(почте)
     *
     * @param mail
     * @return
     */
    public ResultSet getMail(String mail){
        ResultSet resSet = null;

        String select = "SELECT * FROM users WHERE mail =?";
        //Кодом далее описываем, что подразумеваем вместо знаков вопросов и выполняем наше действие
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, mail);

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }

//     public String getUsername(String Id) throws SQLException {
//
//     }

    /**
     * Получение id пользователя по почте
     * @param loginMail
     * @return
     * @throws SQLException
     */
    public String getIdUsers(String loginMail) throws SQLException {
        ResultSet result = getMail(loginMail);

        String id = null;
        while (result.next()) {
            id = result.getString(1);
        }

        return id;
    }

    /**
     * Смена пароля пользователя в БД
     * @param mail
     * @param newPassword
     */
    public void changePassword(String mail, String newPassword) {
        //Не удалось воспользоваться функцией getdbConnection для подключения к БД (надеюсь, позже исправлю)\
        //Поэтому первая часть кода и есть подключение к БД
        try {
            String url = "jdbc:mysql://" + dbHost + ":"
                    + dbPort + "/" + dbName;
            String username = dbUser;
            String password = dbPass;
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "UPDATE users SET password = ? WHERE idusers = ?";
                //Кодом далее описываем, что подразумеваем вместо знаков вопросов и выполняем наше действие
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, getIdUsers(mail));

                preparedStatement.executeUpdate();
                System.out.println("Password changed");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }

    }

    /**
     * Удаление пользователя из Базы Данных
     * @param mail
     */
    public void deleteUser(String mail){
        try{
            String url = "jdbc:mysql://" + dbHost + ":"
                    + dbPort + "/" + dbName;
            String username = "root";
            String password = "12345";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                String sql = "DELETE FROM users WHERE mail = ?";
                //Кодом далее описываем, что подразумеваем вместо знаков вопросов и выполняем наше действие
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, mail);

                preparedStatement.executeUpdate();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
}
