package projects.mp3Player;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("FXML Demo");
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
