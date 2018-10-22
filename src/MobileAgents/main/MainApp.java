package MobileAgents.main;


import MobileAgents.config.Configuration;
import MobileAgents.display.DisplayController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;

/**
 * Initiates display and starts coordinator
 */
public class MainApp extends Application {

    private static DisplayController dc = new DisplayController();
    private Configuration map_config = new Configuration("./docs/sample.txt");
    private Coordinator coordinator;

    @Override
    public void start(Stage primaryStage) throws IOException {

        /*Load the FXML file and attach the display controller*/
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Mobile Agents");
        FXMLLoader loader = new FXMLLoader();
        loader.setController(dc);
        BorderPane rootPane = loader.load(getClass().getResourceAsStream("../display/ma-display.fxml"));
        primaryStage.setScene(new Scene(rootPane));
        primaryStage.show();

        /*Initialize the coordinator with the given configuration*/
        coordinator = new Coordinator(map_config);

        /*Start an animation timer and call Update in Coordinator*/
        AnimationTimer a = new AnimationTimer() {
            private long nextTime = 0;

            @Override
            public void handle(long now) {

                if(now > nextTime) {
                    coordinator.update();
                    nextTime = now + Duration.ofSeconds(2).toNanos();
                }
            }
        };
        a.start();
    }

    /** A single display controller is used to interact with the display FXML file. Necessary to avoid any parallel
     * instantiation issues with JavaFX and for using multiple fxml files */
    public static DisplayController getDisplayController() {
        return dc;
    }

    public static void main(String[] args) { launch(args); }
}
