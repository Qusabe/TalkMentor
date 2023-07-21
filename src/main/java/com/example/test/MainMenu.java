package com.example.test;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @FXML
    private Label TalkMentor;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button soundNwordB;

    @FXML
    private Button HardWordsB;

    @FXML
    private Button artikB;

    @FXML
    private Button b13;
    @FXML
    private  Button soglB;
    @FXML
    private  Button ttB;
    @FXML
    private  Button tcB;
    @FXML
    private  Button mixedB;
    @FXML
    private  Button sameWordB;
    @FXML
    private Button bangB;
    @FXML
    private  Button speedB;
    @FXML
    private Button bigAmB;
    @FXML
    private Button breathB;
    private String flagDB;

    HashMap wasClicked = new HashMap();
    private static String buttonName;



    @FXML
    private AnchorPane ANC1, ANC2, ANC3, ANC4, ANC5, ANC6, ANC7, ANC8, ANC9, ANC10, ANC11, ANC12, ANC13;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setHoverEffect(ANC1);
        setHoverEffect(ANC2);
        setHoverEffect(ANC3);
        setHoverEffect(ANC4);
        setHoverEffect(ANC5);
        setHoverEffect(ANC6);
        setHoverEffect(ANC7);
        setHoverEffect(ANC8);
        setHoverEffect(ANC9);
        setHoverEffect(ANC10);
        setHoverEffect(ANC11);
        setHoverEffect(ANC12);
        setHoverEffect(ANC13);


        artikB.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("TR1.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Button sourceButton = (Button) event.getSource();
            buttonName = sourceButton.getText();
            flagDB = "1-";
            try {
                progressUserExercises(SignIn.getCurrentMail(), flagDB);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
//            if (Profile.getStatus) {
//                progressUserExercises();
//            }
//            progressBar.doSomething;
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("Кнопка b13 нажата.");
        });
    }
        public void toProfile(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

//        HardWordsB.setOnAction(event -> {
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("TR3.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//
////            if (Profile.getStatus) {
////                progressUserExercises();
////            }
////            progressBar.doSomething;
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//
//        soundNwordB.setOnAction(event -> {
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("TR4.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//
////            if (Profile.getStatus) {
////                progressUserExercises();
////            }
////            progressBar.doSomething;
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//
//        soglB.setOnAction(event -> {
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("TR5.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//
////            if (Profile.getStatus) {
////                progressUserExercises();
////            }
////            progressBar.doSomething;
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//
//        ttB.setOnAction(event -> {  // +
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("TR6.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//
////            if (Profile.getStatus) {
////                progressUserExercises();
////            }
////            progressBar.doSomething;
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//
//        bigAmB.setOnAction(event -> {
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("TR7.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//
////            if (Profile.getStatus) {
////                progressUserExercises();
////            }
////            progressBar.doSomething;
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//
//        tcB.setOnAction(event -> {
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("TR8.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//
////            if (Profile.getStatus) {
////                progressUserExercises();
////            }
////            progressBar.doSomething;
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//
//        mixedB.setOnAction(event -> {
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("TR9.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//
////            if (Profile.getStatus) {
////                progressUserExercises();
////            }
////            progressBar.doSomething;
//
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//
//        sameWordB.setOnAction(event -> {
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("TR10.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//            //            if (Profile.getStatus) {
////                progressUserExercises();
////            }
////            progressBar.doSomething;
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//
//        speedB.setOnAction(event -> {
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("TR11.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//            //            if (Profile.getStatus) {
////                progressUserExercises();
////            }
////            progressBar.doSomething;
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//
//        bangB.setOnAction(event -> {
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("TR12.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            String flagForDB = "12-";
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//            if (SignIn.getStatus()) {
//                try {
//                    progressUserExercises(SignIn.getCurrentMail(), flagForDB);
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
////            }
////            progressBar.doSomething;
//
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//
//        b13.setOnAction(event -> {
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("profile.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Button sourceButton = (Button) event.getSource();
//            buttonName = sourceButton.getText();
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
////            if (Profile.getStatus) {
////                progressUserExercises();
////            }
////            progressBar.doSomething;
//            stage.show();
//            System.out.println("Кнопка b13 нажата.");
//        });
//    }

    public void toTT(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TR6.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toTR2(ActionEvent event) throws IOException {
        flagDB = "2-";
        try {
            progressUserExercises(SignIn.getCurrentMail(), flagDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("TR2.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toTR3(ActionEvent event) throws IOException {
        flagDB = "3-";
        try {
            progressUserExercises(SignIn.getCurrentMail(), flagDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("TR3.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toTR4(ActionEvent event) throws IOException {
        flagDB = "4-";
        try {
            progressUserExercises(SignIn.getCurrentMail(), flagDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("TR4.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toTR5(ActionEvent event) throws IOException {
        flagDB = "5-";
        try {
            progressUserExercises(SignIn.getCurrentMail(), flagDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("TR5.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toTR12(ActionEvent event) throws IOException {
        flagDB = "12-";
        try {
            progressUserExercises(SignIn.getCurrentMail(), flagDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("TR12.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toTR7(ActionEvent event) throws IOException {
        flagDB = "7-";
        try {
            progressUserExercises(SignIn.getCurrentMail(), flagDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("TR7.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toTR8(ActionEvent event) throws IOException {
        flagDB = "8";
        try {
            progressUserExercises(SignIn.getCurrentMail(), flagDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("TR8.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toTR9(ActionEvent event) throws IOException {
        flagDB = "9-";
        try {
            progressUserExercises(SignIn.getCurrentMail(), flagDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("TR9.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toTR10(ActionEvent event) throws IOException {
        flagDB = "10-";
        try {
            progressUserExercises(SignIn.getCurrentMail(), flagDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("TR10.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toTR11(ActionEvent event) throws IOException {
        flagDB = "11-";
        try {
            progressUserExercises(SignIn.getCurrentMail(), flagDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("TR11.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static String getButtonName() {
        return buttonName;
    }

    private void setHoverEffect(AnchorPane anchorPane) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setHeight(40.0);
        dropShadow.setRadius(19.5);
        dropShadow.setSpread(0.7);
        dropShadow.setWidth(40.0);
        anchorPane.setEffect(dropShadow);

        anchorPane.setOnMouseEntered(event -> {
            // Изменение прозрачности при наведении курсора мыши
            anchorPane.setOpacity(0.8);
        });

        anchorPane.setOnMouseExited(event -> {
            // Возвращение прозрачности после выхода курсора мыши
            anchorPane.setOpacity(0.6);
        });
    }

    private static void progressUserExercises(String mail, String progress) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String progressExercises = dbHandler.getProgressExercises(mail);
        if (progressExercises.indexOf(progress)==-1){
            System.out.println("Такой подстроки нет");
            dbHandler.progressUserExercises(mail, progress);
        }
        else
            System.out.println("Такая подстрока есть");
    }

}


