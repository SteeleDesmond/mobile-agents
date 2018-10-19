package MobileAgents.display;

import MobileAgents.agents.Agent;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import MobileAgents.maps.Map;

public class Display extends Thread
{
    /** Graphics Components **/
    private Canvas canvas;
    private GraphicsContext gc;
    public Group root;
    public Group map;

    private Map ma_map; // reference to the Map class

    public Agent agent1;

    public Display(int width,int height,Agent agent)
    {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        root = new Group();
        map = new Group();

        ma_map = new Map();

        agent1 = agent;

        displayMap();
    }

    /**
     *  displays a map specified in the Map class
     */
    public void displayMap()
    {
        // tell the canvas about the lines
        for(int i = 0; i < ma_map.lines.length;i++)
        {
            map.getChildren().add(ma_map.lines[i]);
        }

        // tell the canvas about the sensors
        for(int i = 0; i < ma_map.lines.length;i++)
        {
            map.getChildren().add(ma_map.sensors[i].node);
        }

        //add the map to the root view
        root.getChildren().add(map);

    }

    /**
     * This function was built to test the transveral of a map, by an mobile agents
     * A node will turn red if an agent is currently on it
     */
    public void updateMap()
    {
        int value = agent1.currentNode;

        //if agent is at node 1
        if (value == 1)
        {
            for(int i = 0; i < ma_map.sensors.length;i++)
            {
                //make the fist node red
                if(i == 0)
                {
                    ma_map.sensors[i].node.setFill(Color.RED);
                }

                //make all other nodes black
                else
                {
                    ma_map.sensors[i].node.setFill(Color.BLACK);
                }
            }
        }

        //node 2
        else if (value == 2)
        {
            for(int i = 0; i < ma_map.sensors.length;i++)
            {
                //make the second node red
                if(i == 1)
                {
                    ma_map.sensors[i].node.setFill(Color.RED);
                }
                //make all other nodes black
                else
                {
                    ma_map.sensors[i].node.setFill(Color.BLACK);
                }
            }
        }

        //node 3
        else if (value == 3)
        {
            for(int i = 0; i < ma_map.sensors.length;i++)
            {
                //make the third node red
                if(i == 2)
                {
                    ma_map.sensors[i].node.setFill(Color.RED);
                }
                //make all other nodes black
                else
                {
                    ma_map.sensors[i].node.setFill(Color.BLACK);
                }
            }
        }

        //node 4
        else if (value == 4)
        {
            for(int i = 0; i < ma_map.sensors.length;i++)
            {
                //make the fourth node red
                if(i == 3)
                {
                    ma_map.sensors[i].node.setFill(Color.RED);
                }
                //make all other nodes black
                else
                {
                    ma_map.sensors[i].node.setFill(Color.BLACK);
                }
            }

        }
        System.out.println("Now updating map, and moving agent to Node " + value);

    }

    //The display class thread
    @Override
    public void run()
    {
        while (true)
        {
            updateMap();
        }
    }


}
