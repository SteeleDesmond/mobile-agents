package MobileAgents.display;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.*;

import java.util.concurrent.BlockingQueue;


import MobileAgents.nodes.*;




public class Display extends Thread
{
    /** Graphics Components **/
    private Canvas canvas;
    private GraphicsContext gc;
    public Group root;
    public Group map;

    /** Sensors used to build mini map **/
    private Sensors sensor1;
    private Sensors sensor2;
    private Sensors sensor3;
    private Sensors sensor4;

    /** Lines used to connect the sensors **/
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;

    // holds the positions that the agent is suppose to vist
    private final BlockingQueue <Integer > nodeQue;

    public String error = "error";

    public Display(int width,int height,BlockingQueue<Integer> queue)
    {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        root = new Group();

        this.nodeQue = queue;

        // draw test map, with only 4 nodes
        drawMiniMap();

    }


    public void drawMiniMap()
    {
        //initialize the sensors(nodes)
        sensor1 = new Sensors(145,295,1);

        sensor2 = new Sensors(270,400,2);

        sensor3 = new Sensors(405,500,3);

        sensor4 = new Sensors(450,265,4);

        //initialize the lines that will connect the sensors
        line1 = new Line();
        line2 = new Line();
        line3 = new Line();
        line4 = new Line();

        //draw the lines connecting the nodes
        //connect node 1 to node 2
        line1.setStartX(150);
        line1.setStartY(300);
        line1.setEndX(270);
        line1.setEndY(400);

        //connect node 2 to node 3
        line2.setStartX(270);
        line2.setStartY(400);
        line2.setEndX(410);
        line2.setEndY(500);

        //connect node 3 to node 4
        line3.setStartX(410);
        line3.setStartY(500);
        line3.setEndX(450);
        line3.setEndY(270);

        //connect node 4 to node 1
        line4.setStartX(450);
        line4.setStartY(270);
        line4.setEndX(150);
        line4.setEndY(300);

        // tell the canvas about the lines
        root.getChildren().add(line1);
        root.getChildren().add(line2);
        root.getChildren().add(line3);
        root.getChildren().add(line4);

        // tell the canvas about the sensors
        root.getChildren().add(sensor1.node);
        root.getChildren().add(sensor2.node);
        root.getChildren().add(sensor3.node);
        root.getChildren().add(sensor4.node);
    }


    /**
     * This function was built to test the transveral of a map, by an mobile agents
     * A node will turn red if an agent is currently on it
     */
    private void updateMap(int value)
    {
        //if agent is at node 1
                if (value == 1) {
                    //make node 1 red
                    sensor1.node.setFill(Color.RED);

                    //make all other nodes black
                    sensor2.node.setFill(Color.BLACK);
                    sensor3.node.setFill(Color.BLACK);
                    sensor4.node.setFill(Color.BLACK);
                }

                //node 2
                else if (value == 2) {
                    //make node 2 red
                    sensor2.node.setFill(Color.RED);

                    //make all other nodes black
                    sensor1.node.setFill(Color.BLACK);
                    sensor3.node.setFill(Color.BLACK);
                    sensor4.node.setFill(Color.BLACK);
                }

                //node 3
                else if (value == 3) {
                    //make node 3 red
                    sensor3.node.setFill(Color.RED);

                    //make all other nodes black
                    sensor1.node.setFill(Color.BLACK);
                    sensor2.node.setFill(Color.BLACK);
                    sensor4.node.setFill(Color.BLACK);
                } else if (value == 4) {
                    //make node 4 red
                    sensor4.node.setFill(Color.RED);

                    //make all other nodes black
                    sensor1.node.setFill(Color.BLACK);
                    sensor2.node.setFill(Color.BLACK);
                    sensor3.node.setFill(Color.BLACK);

                }
                System.out.println("Now updating map, and moving agent to Node " + value);

    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                // remove the first node we want our agent to vist
                int value = nodeQue.take();

                //update the map with the new agents position
                updateMap(value);
                Thread.sleep(1000);
            }

        }

        catch (InterruptedException e) {
            System.out.print(error + " was interrupted"); }

    }


}
