package MobileAgents.agents;

import MobileAgents.config.MultiPoint;
import MobileAgents.node.Node;
import java.util.Collections;
import java.util.ArrayList;

public class Agent extends Thread {

    public Node currentNode; // agents current position
    private int xpos;
    private int ypos;
    private int agentID;

    public boolean isCloneable = false;
    public boolean isSearching = true; // true when the agent should perform random walk

    public Agent(int id, Node p) {
        currentNode = p;
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
            while(isSearching)
            {
                if(currentNode.getRoutingTable() != null)
                {
                    randonWalk();
                }
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     *  Picks a random node from a list of neighbors, and updates the agents current node to the random node
     */
    public void randonWalk()
    {

        MultiPoint random_point;

        ArrayList<Node> neighbors = currentNode.getRoutingTable().getNeighbors();

        boolean foundSpace = false;
        boolean noSpace = false;

        int counter = 0;

        //lets shuffle our list of nodes so we choose from random nodes
        Collections.shuffle(neighbors);

        // this will continue until 1 of 2 cases are met
        // 1. The agent has found an unoccupied node
        // 2. All the nodes in the given in the list are occupied
        while(!foundSpace && !noSpace)
        {
            //grab a random node from the list
            Node random_node = neighbors.get(counter);

            //check if the node is occupied
            if(!random_node.getIsOccupied())
            {
                //store the random nodes point
                random_point = new MultiPoint(random_node.getXPos(),random_node.getYPos());

                currentNode = random_node;
                //case 1 has been met, exit the loop
                foundSpace = true;

            }
            // the node is occupied check the next neighbor
            else if(random_node.getIsOccupied())
            {
                //continue checking for an empty space
                counter++;

                // case 2  has been been met all the nodes are occupied
                if(counter > neighbors.size())
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
