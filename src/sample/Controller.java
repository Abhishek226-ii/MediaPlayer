package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;

public class Controller {
    MediaPlayer player;
    @FXML
    private MediaView mediaView;



    @FXML
    private Button playBtn;

    @FXML
    private Slider timeSlider;

    @FXML
    public void openSongMenu(javafx.event.ActionEvent actionEvent) {
        try {
            System.out.println("open song clicked");
            FileChooser chooser = new FileChooser();

            File file = chooser.showOpenDialog(null);

            Media m = new Media(file.toURI().toURL().toString());

            player = new MediaPlayer(m);

            mediaView.setMediaPlayer(player);

            player.setOnReady(()->{
                timeSlider.setMin(0);
                timeSlider.setMax(player.getMedia().getDuration().toSeconds());
            });

            // listener on player
            player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    /// coding
                    Duration d=player.getCurrentTime();
                    timeSlider.setValue(d.toSeconds());
                }
            });

            timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    double val = timeSlider.getValue();
                    


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
        @FXML
    public void play(ActionEvent actionEvent) {
        MediaPlayer.Status status=player.getStatus();
        if(status== MediaPlayer.Status.PLAYING)
        {
            player.pause();

        } else{
            player.play();

        }
    }
}
