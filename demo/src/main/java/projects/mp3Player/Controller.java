package projects.mp3Player;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Arrays;
import java.util.Comparator;

public class Controller implements Initializable {

    // FXML Injections: Define components

    @FXML
    private Pane pane;
    @FXML
    private Label song_label;
    @FXML
    private Button play_button, pause_button, reset_button, previous_button, next_button;
    @FXML
    private ComboBox<String> speed_combBox;
    @FXML
    private Slider volume_Slider;
    @FXML
    private ProgressBar song_progressbar;
    @FXML
    private ImageView coverImg;

    // Media Player
    private Media media;
    private MediaPlayer mediaPlayer;

    // Directory to music and an array which holds the filed
    private File directory;
    private File[] files;

    private ArrayList<File> songs; // basically the playlist
    private ArrayList<File> covers;

    private int songNumber; // Which song currently plays
    private int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200}; // Amount of choosable speeds

    private Timer timer;
    private TimerTask task;
    private boolean running;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songs = new ArrayList<File>();
        covers = new ArrayList<File>();

        directory = new File("music");
        files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));

        if(files != null){
            Arrays.sort(files, Comparator.comparing(File::getName));
            for(File file : files){
                songs.add(file);
                System.out.println(file);
            }
        }

        File pictureDir = new File("pictures");
        File[] imageFiles = pictureDir.listFiles((dir, name) ->
                name.toLowerCase().matches(".*\\.(jpg|jpeg|png)")
        );

        if(imageFiles != null){
            Arrays.sort(imageFiles, Comparator.comparing(File::getName));
            covers.addAll(Arrays.asList(imageFiles));
        }

        setMedia();

        for(int i = 0; i < speeds.length; i++){
            speed_combBox.getItems().add(Integer.toString(speeds[i])+"%");
        }

        speed_combBox.setOnAction(this::change_speed);

        volume_Slider.valueProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volume_Slider.getValue() * 0.01);
            }
        });

        song_progressbar.setStyle("-fx-accent: #5f5baf");
    }

    public void setMedia() {

        File song = songs.get(songNumber);

        media = new Media(song.toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        song_label.setText(song.getName());

        if(songNumber < covers.size()){
            coverImg.setImage(new Image(covers.get(songNumber).toURI().toString()));
        } else {
            coverImg.setImage(null);
        }
    }

    public void play_media(){
        begin_timer();
        mediaPlayer.play();
        mediaPlayer.setVolume(volume_Slider.getValue() * 0.01);
    }

    public void pause_media(){
        cancel_timer();
        change_speed(null);
        mediaPlayer.pause();
    }

    public void reset_media(){
        song_progressbar.setProgress(0);
        mediaPlayer.seek(Duration.seconds(0.0));
    }

    public void previous_media(){

        if(songNumber > 0){
            songNumber--;
        } else {
            songNumber = songs.size()-1;
        }

        mediaPlayer.stop();
        if(running) cancel_timer();
        setMedia();
    }

    public void next_media(){
        if(songNumber < songs.size() - 1){
            songNumber++;
        } else {
            songNumber = 0;
        }

        mediaPlayer.stop();
        if(running) cancel_timer();
        setMedia();
    }

    public void change_speed(ActionEvent event){
        //mediaPlayer.setRate(Integer.parseInt(speed_combBox.getValue()) * 0.01); //<- Ohne %

        if(speed_combBox.getValue() == null){
            mediaPlayer.setRate(1);
        } else {
            mediaPlayer.setRate(Integer.parseInt(
                    speed_combBox.getValue().substring(0,speed_combBox.getValue().length() - 1)) * 0.01); // <- mit %
        }
    }

    public void begin_timer(){
        timer = new Timer();

        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                song_progressbar.setProgress(end > 0 ? current/end : 0);

                if(current/end == 1){
                    cancel_timer();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancel_timer(){
        running = false;
        timer.cancel();
    }
}
