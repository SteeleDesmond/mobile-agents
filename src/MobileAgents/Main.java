package MobileAgents;


import MobileAgents.display.Display;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import MobileAgents.agents.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Main extends Application
{


    public Agent agent1;

    public Display sceneDisplay;




    public Main()
    {

        agent1 = new Agent("Agent1",0,1);

        sceneDisplay = new Display(1000,1000, agent1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        primaryStage.setTitle("Mobile Agents");

        primaryStage.setScene(new Scene(new Group(sceneDisplay.root)));

        agent1.start();

        sceneDisplay.start();

        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
