package MobileAgents.agents;

import MobileAgents.config.MultiPoint;
import MobileAgents.node.Node;
import java.util.Random;
import java.util.Collections;
import java.awt.*;
import java.util.ArrayList;

public class Agent extends Thread {
    private String agentName; // the agents name
    public Node currentNode; // agents current position
    private int xpos;
    private int ypos;
    private int agentID;

    public boolean isCloneable = false;

    public Agent(String name, int id, Node p) {
        currentNode = p;
        agentName = name;
        agentID = id;

        xpos = p.getXPos();
        ypos = p.getYPos();
    }

    /**
     *   Agents Thread
     */
    @Override
    public void run() {
        try {
            while(true)
            {

                Thread.sleep(100);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     *  Picks a random node from list past in, and updates the agents current node to the random node
     *  Ideally the only list that should be passed is the nodes that neighbor the agent
     * @param nodes
     */
    public void randonWalk(ArrayList<Node> nodes)
    {

        MultiPoint random_point;

        boolean foundSpace = false;
        boolean noSpace = false;

        int counter = 0;

        //lets shuffle our list of nodes so we choose from random nodes
        Collections.shuffle(nodes);

        // this will continue until 1 of 2 cases are met
        // 1. The agent has found an unoccupied node
        // 2. All the nodes in the given in the list are occupied
        while(!foundSpace && !noSpace)
        {
            //grab a random node from the list
            Node random_node = nodes.get(counter);

            //check if the node is occupied
            if(!random_node.getIsOccupied())
            {
                //store the random nodes point
                random_point = new MultiPoint(random_node.getXPos(),random_node.getYPos());
                //set the agents current position to the new random point
                currentNode.setNodePos(random_point);
                //case 1 has been met, exit the loop
                foundSpace = true;

            }
            // the node is occupied check the next neighbor
            else if(random_node.getIsOccupied())
            {
                //continue checking for an empty space
                counter++;

                // case 2  has been been met all the nodes are occupied
                if(counter > nodes.size())
                {
                    noSpace = true;
                }
            }

        }

    }

    /**
     *
     * print the current x and y position of the agent
     */
    public void printAgentsPosition()
    {
      currentNode.getNodePos().printCoords();
    }

    public int getXpos()
    {
        return  xpos;
    }
    public int getYpos()
    {
        return  ypos;
    }

    @Override
    public String toString()
    {
        return currentNode.getNodePos().toString();
    }
}
