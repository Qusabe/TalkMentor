package com.example.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Timer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.*;
import javax.swing.*;

public class ExHandler implements Initializable {


    @FXML
    private Label audioLabel;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider audioProgressBar;

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
    @FXML
    private ScrollPane scrollPane;

    private static final double SCROLL_AMOUNT = 0.1;
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
            AudioFormat.Encoding.PCM_SIGNED,44100, 16, 2, 4, 44100, false);
    private TargetDataLine mike;

    private ArrayList<String> spisok;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        audio = new ArrayList<>();
        directory = new File("C:\\Users\\79675\\IdeaProjects\\TalkMentor\\TEST\\.idea\\AudioRecordings");
        refreshAudioList();

        media = new Media(audio.get(audioNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        audioLabel.setText(audio.get(audioNumber).getName());

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends javafx.util.Duration> observable, javafx.util.Duration oldValue, javafx.util.Duration newValue) {
                audioProgressBar.setValue(newValue.toSeconds());
            }
        });

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });

        audioProgressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.seek(Duration.seconds(audioProgressBar.getValue()));
            }
        });

        audioProgressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.seek(Duration.seconds(audioProgressBar.getValue()));
            }
        });

        mediaPlayer.setOnReady(() -> {
            Duration total = media.getDuration();
            audioProgressBar.setMax(total.toSeconds());
        });

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                // РћР±РЅРѕРІР»СЏРµРј РїСЂРѕРіСЂРµСЃСЃ Р±Р°СЂ С‚РѕР»СЊРєРѕ РµСЃР»Рё РїСЂРѕРёРіСЂС‹РІР°РЅРёРµ Р°РєС‚РёРІРЅРѕ
                if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    javafx.util.Duration currentTime = mediaPlayer.getCurrentTime();
                    audioProgressBar.setValue(currentTime.toSeconds());
                }
            }
        };

        timer.schedule(task, 0, 10);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshAudioList();
            }
        }).start();
    }
    public void toMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void refreshAudioList() {
        directory = new File("C:\\Users\\79675\\IdeaProjects\\TalkMentor\\TEST\\.idea\\AudioRecordings");
        File[] files = directory.listFiles();
        if (files != null) {
            audio.clear();
            for (File file : files) {
                audio.add(file);
            }
        }
    }

    public void playAudio() {
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        mediaPlayer.play();
        running = true;
        playButton.setText("Play");
    }

    public void pauseAudio() {
        mediaPlayer.pause();
        running = false;
        playButton.setText("Pause");
    }

    public void togglePlay(ActionEvent event) throws IOException {
        if (running) {
            pauseAudio();
        }
        else {
            playAudio();
        }
    }
    public void previousAudio() {
        if (audioNumber > 0) {
            audioNumber--;
        } else {
            audioNumber = audio.size() - 1;
        }

        mediaPlayer.stop();
        media = new Media(audio.get(audioNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(() -> {
            Duration total = media.getDuration();
            audioProgressBar.setMax(total.toSeconds());
        });

        audioLabel.setText(audio.get(audioNumber).getName());
        playAudio();
    }

    public void nextAudio() {
        if (audioNumber < audio.size() - 1) {
            audioNumber++;
        } else {
            audioNumber = 0;
        }

        mediaPlayer.stop();
        media = new Media(audio.get(audioNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(() -> {
            Duration total = media.getDuration();
            audioProgressBar.setMax(total.toSeconds());
        });

        audioLabel.setText(audio.get(audioNumber).getName());
        playAudio();
    }

    public void recordAudio(){

        if (!isRecording) {
            System.out.println("Запись началась");
            isRecording = true;
            try {
                do {
                    // новое название файла
                    File filePath = new File("AudioRecordings");
                    filePath.mkdir();
                    soundFileName = (filePath + filename + (suffix++) + "."+ fileType.getExtension());
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

        }
        else {
            System.out.println("Запись прекращена");
            isRecording = false;
            mike.stop();
            mike.close();
        }

    }

}