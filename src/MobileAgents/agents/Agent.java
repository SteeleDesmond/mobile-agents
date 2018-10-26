package MobileAgents.agents;

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

    public Agent(int id, Node node) {
        currentNode = node;
        agentID = id;
        xpos = node.getXPos();
        ypos = node.getYPos();
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
                    Thread.sleep(1000);
                   if(checkForFire() == true)
                   {
                       isSearching = false;
                       isCloneable = true;
                   }

                }

            }
            //clone the agent
            if(isCloneable == true)
            {
                System.out.println("cloning an agent...");
                cloneAgent();

                isCloneable = false;
                isSearching = true;
                //Thread.sleep(100);
            }

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Picks a random node from a list of neighbors, and updates the agents current node to the random node
     */
    public void randonWalk() {

        ArrayList<Node> neighbors = currentNode.getRoutingTable().getNeighbors();
        boolean foundSpace = false;
        boolean noSpace = false;
        int counter = 0;

        //lets shuffle our list of nodes so we choose from random nodes
        Collections.shuffle(neighbors);

        // this will continue until 1 of 2 cases are met
        // 1. The agent has found an unoccupied node
        // 2. All the nodes in the given in the list are occupied
        while(!foundSpace && !noSpace && counter < neighbors.size()) {
            //grab a random node from the list
            Node random_node = neighbors.get(counter);

            //check if the node is occupied
            if(!random_node.hasAgent())
            {
                //since the agent is moving nodes the previous node should no longer have an agent
                currentNode.setHasAgent(false);
                //set the agents current node to the new random
                currentNode = random_node;
                //updates agent current node
                currentNode.setHasAgent(true);
                //case 1 has been met, exit the loop
                foundSpace = true;
            }
            // the node is occupied check the next neighbor
            else if(random_node.hasAgent()) {
                //continue checking for an empty space
                counter++;
                // case 2  has been been met all the nodes are occupied
                if(counter == neighbors.size()) {
                    noSpace = true;
                }
            }
        }
    }

    /**
     *
     * @return true if a node is near a fire
     */
    public boolean checkForFire()
    {
        ArrayList<Node> neighbors = currentNode.getRoutingTable().getNeighbors();
        boolean foundFire = false;

        for(Node n : neighbors)
        {
            if(n.getNodeState() == "near-fire")
            {
               foundFire = true;
               break;
            }
        }

        return foundFire;
    }

    /**
     *  Clones an agent on its neighbors nodes,which includes 'near-fire' nodes and 'standard' nodes
     */
    public void cloneAgent()
    {
        ArrayList<Node> neighbors = currentNode.getRoutingTable().getNeighbors();
        int id;

        for(Node n : neighbors)
        {
            if(n.getNodeState() == "near-fire" || n.getNodeState() == "standard")
            {
                // if the neighbor node doesnt have an agent
                if(n.hasAgent() == false)
                {
                    id = agentID + 1;
                    Agent agent_clone = new Agent(id,n);
                    agent_clone.currentNode.setHasAgent(true);
                    agent_clone.start();
                }

            }

        }
    }

    @Override
    public String toString() {
        return currentNode.toString();
    }


}
