package projects.mp3Player;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FXMLApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(
                getClass().getResource("/projects/mp3Player/Scene.fxml")
        );
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource("/projects/mp3Player/Scene.fxml"));

        Image icon = new Image("file:///C:/Users/justi/OneDrive/Desktop/lil Projects/demo/src/music_player_icon.png");
        Scene scene = new Scene(fxmlLoader.load());

        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setTitle("Simple MP3 Player");
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}
