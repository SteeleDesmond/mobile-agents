package MobileAgents.display;

import MobileAgents.agents.Agent;
import MobileAgents.node.Node;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Display extends Thread
{
    /** Graphics Components **/
    private Canvas canvas;
    private GraphicsContext gc;
    public Group root;
    public Group map;

    private Map ma_map; // reference to the Map class

    public Agent agent1;

    public Display(int width,int height,Agent agent) {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        root = new Group();
        map = new Group();

        ma_map = new Map();

        agent1 = agent;

        displayMap();
    }

    /**
     * Takes a node and displays it
     * @param node
     */
    public void paintNode(Node node) {

    }

    /**
     *  displays a node specified in the Map class
     */
    public void displayMap() {
        // tell the canvas about the lines
        for(int i = 0; i < ma_map.lines.length;i++)
        {
            map.getChildren().add(ma_map.lines[i]);
        }

        // tell the canvas about the nodes
        for(int i = 0; i < ma_map.lines.length;i++)
        {
            map.getChildren().add(ma_map.nodes[i].node);
        }

        //add the node to the root view
        root.getChildren().add(map);
    }

    /**
     * This function was built to test the transveral of a node, by an mobile agents
     * A node will turn red if an agent is currently on it
     */
    public void updateMap() {
        int value = agent1.currentNode;

        //if agent is at node 1
        if (value == 1) {
            for(int i = 0; i < ma_map.nodes.length; i++) {
                //make the fist node red
                if(i == 0) {
                    ma_map.nodes[i].node.setFill(Color.RED);
                }
                //make all other node black
                else {
                    ma_map.nodes[i].node.setFill(Color.BLACK);
                }
            }
        }

        //node 2
        else if (value == 2) {
            for(int i = 0; i < ma_map.nodes.length; i++) {
                //make the second node red
                if(i == 1) {
                    ma_map.nodes[i].node.setFill(Color.RED);
                }
                //make all other node black
                else {
                    ma_map.nodes[i].node.setFill(Color.BLACK);
                }
            }
        }

        //node 3
        else if (value == 3) {
            for(int i = 0; i < ma_map.nodes.length; i++) {
                //make the third node red
                if(i == 2) {
                    ma_map.nodes[i].node.setFill(Color.RED);
                }
                //make all other node black
                else {
                    ma_map.nodes[i].node.setFill(Color.BLACK);
                }
            }
        }

        //node 4
        else if (value == 4) {
            for(int i = 0; i < ma_map.nodes.length; i++) {
                //make the fourth node red
                if(i == 3) {
                    ma_map.nodes[i].node.setFill(Color.RED);
                }
                //make all other node black
                else {
                    ma_map.nodes[i].node.setFill(Color.BLACK);
                }
            }
        }
        System.out.println("Now updating node, and moving agent to Node " + value);
    }

    //The display class thread
    @Override
    public void run() {
        while (true) {
            updateMap();
        }
    }
}
