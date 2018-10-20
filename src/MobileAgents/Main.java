package MobileAgents;


import MobileAgents.display.Display;
import MobileAgents.maps.Configuration;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import MobileAgents.agents.*;

public class Main extends Application
{

    public Agent agent1;

    public Display sceneDisplay;

    public Configuration map_config;

    public Main()
    {

        agent1 = new Agent("Agent1",0,1);

        sceneDisplay = new Display(1000,1000, agent1);

        map_config = new Configuration("/Users/brandonwade/Downloads/sample.txt");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        primaryStage.setTitle("Mobile Agents");

        primaryStage.setScene(new Scene(new Group(sceneDisplay.root)));

        //agent1.start();

        //sceneDisplay.start();

        map_config.parseConfigList(map_config.config_as_list);

        System.out.println(map_config.fires);

        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
