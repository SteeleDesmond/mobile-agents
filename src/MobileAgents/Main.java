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

    BlockingQueue <Integer > agentsPosition;

    public Agent agent1;

    public Display sceneDisplay;

    public Thread displayThread;

    public Thread agentThread;




    public Main()
    {
        agentsPosition = new LinkedBlockingQueue <>();

        sceneDisplay = new Display(1000,1000, agentsPosition);

        agent1 = new Agent("Agent1",0,agentsPosition);

        displayThread = new Thread(sceneDisplay);

        agentThread = new Thread(agent1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        primaryStage.setTitle("Mobile Agents");

        primaryStage.setScene(new Scene(new Group(sceneDisplay.root)));


         agentThread.start();
         displayThread.start();

        primaryStage.show();





    }


    public static void main(String[] args)
    {

        launch(args);



    }
}
