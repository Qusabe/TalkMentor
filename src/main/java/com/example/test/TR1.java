package com.example.test;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.media.MediaPlayer;


import javax.sound.sampled.*;
import javax.swing.*;

import java.io.File;

import java.io.IOException;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;




public class TR1 {
    @FXML
    private Label audioLabel;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar audioProgressBar;
    @FXML
    private Button playB;
    @FXML
    private RadioButton rec_button;
    @FXML
    public Label theoryText;
    private boolean isRecording = false;
    private Media media;
    private MediaPlayer mediaPlayer;
    private File directory;
    private File[] files;
    private boolean isPlaying = false;
    private ArrayList<File> audio;

    private int audioNumber = 0;
    private Timer timer;

    private TimerTask task;
    private boolean running;
    private File file;
    private boolean isRunning = false;
    private String soundFileName;
    private String filename = "//record_";
    private int suffix = 0;
    private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    private int MONO = 1;
    private AudioFormat format = new AudioFormat(
            AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
    private TargetDataLine mike;

    private String text = "Той ночью мне приснилось, будто я вернулся на Кладбище забытых книг. Мне вновь было десять лет.\nВскоре в детскую заглянул отец; он всполошился, услышав мои горестные рыдания.\n" +
            "Я видел его во сне таким, каким он был прежде, — молодым и знавшим ответы на все экзистенциальные вопросы.\n" +
            "Отец крепко обнял меня, пытаясь успокоить. Позднее, когда первые лучи солнца осветили окутанную дымкой Барселону,\n" +
            "мы вышли из дома. Отец, неизвестно почему, проводил меня только до крыльца. Там выпустил мою руку и дал понять,\n" +
            "что дальнейший путь я обязан пройти самостоятельно.\n";


    public void initialize(URL url, ResourceBundle resourceBundle) {


//   <------------------------------------Инициализация функционала аудиозаписи и аудиоплеера-------------------------->
        audio = new ArrayList<File>(); // создание списка аудио
        directory = new File("AudioRecordings" + MainMenu.getButtonName()); // получаем директорию, откуда будем считывать файлы
        files = directory.listFiles(); //получаем список файлов в директории
        if (files != null) { // если список файлов не пустой
            for (File file : files) {
                audio.add(file); //то добавляем файлы в список аудио
            }
        }


        media = new Media(audio.get(audioNumber).toURI().toString());  //спросить у Михаила
        mediaPlayer = new MediaPlayer(media);

        audioLabel.setText(audio.get(audioNumber).getName()); //выводим на экран название играющей аудиозаписи

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {  // настройка громкости (спросить подробнее)
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);

            }
        });

        audioProgressBar.setStyle("-fx-accent: #00FF00;");  //настройка стиля прогресс бара


    }

    public void toMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*
    *
    Методы записи звука ________________________________________________________________________________
    *
     */

    public void recordAudio() {

        if (!isRecording) {
            System.out.println("Запись началась");
            isRecording = true;
            try {
                do {
                    // новое название файла
                    File filePath = new File("AudioRecordings\\" + MainMenu.getButtonName());
                    filePath.mkdir();
                    soundFileName = (filePath + filename + (suffix++) + "." + fileType.getExtension());
                    file = new File(soundFileName);
                } while (!file.createNewFile());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            new Thread(() -> {
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                if (!AudioSystem.isLineSupported(info)) {
                    JOptionPane.showMessageDialog(null, "Line not supported"
                                    + info, "Line not supported",
                            JOptionPane.ERROR_MESSAGE);
                }
                try {
                    mike = (TargetDataLine) AudioSystem.getLine(info);
                    mike.open(format, mike.getBufferSize());
                    AudioInputStream sound = new AudioInputStream(mike);
                    mike.start();
                    AudioSystem.write(sound, fileType, file);
                } catch (LineUnavailableException ex) {
                    JOptionPane.showMessageDialog(null, "Line not available"
                                    + ex, "Line not available",
                            JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "I/O Error " + ex,
                            "I/O Error", JOptionPane.ERROR_MESSAGE);
                }
            }).start();

        } else {
            System.out.println("Запись прекращена");
            isRecording = false;
            mike.stop();
            mike.close();
        }

    }







        /*
    *
    --------------------------------------Методы воспроизведения звука__________________________________________________________________________
    *
     */
//

    public void togglePlay(ActionEvent event) {
        if (isPlaying) {
            pauseAudio();
        } else {
            playAudio();
        }
    }

    // плей
    private void playAudio() {


        beginTimer();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        mediaPlayer.play();
        playB.setText("Pause");
        isPlaying = true;
    }

    // пауза
    private void pauseAudio() {


        cancelTimer();
        mediaPlayer.pause();
        playB.setText("Play");
        isPlaying = false;
    }

    //переключает на предыдущее аудио
    public void previousAudio() {

        if (audioNumber > 0) {

            audioNumber--;

            mediaPlayer.stop();

            if (running) {

                cancelTimer();
            }

            media = new Media(audio.get(audioNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            audioLabel.setText(audio.get(audioNumber).getName());

            playAudio();
        } else {

            audioNumber = audio.size() - 1;

            mediaPlayer.stop();

            if (running) {

                cancelTimer();
            }

            media = new Media(audio.get(audioNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            audioLabel.setText(audio.get(audioNumber).getName());

            playAudio();
        }

    }

    //переключает на следующее аудио
    public void nextAudio() {

        if (audioNumber < audio.size() - 1) {

            audioNumber++;

            mediaPlayer.stop();

            if (running) {

                cancelTimer();
            }

            media = new Media(audio.get(audioNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            audioLabel.setText(audio.get(audioNumber).getName());

            playAudio();
        } else {

            audioNumber = 0;

            mediaPlayer.stop();

            media = new Media(audio.get(audioNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            audioLabel.setText(audio.get(audioNumber).getName());

            playAudio();
        }
    }

    //начинает работу таймера для работы прогрессбара
    private void beginTimer() {

        timer = new Timer();

        task = new TimerTask() {

            public void run() {

                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                audioProgressBar.setProgress(current / end);

                if (current / end == 1) {

                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    //останавливает работу таймера для работы прогрессбара
    private void cancelTimer() {

        running = false;
        timer.cancel();
    }


}