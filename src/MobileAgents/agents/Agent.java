package MobileAgents.agents;

import MobileAgents.node.Node;
import java.util.Collections;
import java.util.ArrayList;

/**
 *  Controls the Agents Functionality
 */
public class Agent extends Thread {

    private Node currentNode; // the node the agent is currently on
    private int xpos; // agents x position
    private int ypos; // agents y position
    private int agentID;

    private boolean isSearching = true; // true when the agent should perform random walk

    public Agent(int id, Node node) {
        currentNode = node;
        agentID = id;
        xpos = node.getXPos();
        ypos = node.getYPos();
    }

    /**
     * Agents Thread
     */
    @Override
    public void run() {
        try {

            while (getIsSearching())
            {
                if (currentNode.getRoutingTable() != null)
                {
                    // check if there are any nearby fires
                   checkForFire();
                   //randomly search for more fires
                   randomWalk();

                }
                // slow down the random walk / cloning
                Thread.sleep(1000);
            }
            //after the agent walks to a node, check if its set on fire, if so, terminate it
            if (checkIfDie()) {
                currentNode.terminate();
                currentNode.setHasAgent(false);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Picks a random node from a list of neighbors, and updates the agents current node to the random node
     */
    private synchronized void randomWalk() {

        ArrayList<Node> neighbors = currentNode.getRoutingTable().getNeighbors();
        boolean foundSpace = false;
        boolean noSpace = false;
        int counter = 0;

        //lets shuffle our list of nodes so we choose from random nodes
        Collections.shuffle(neighbors);


        // this will continue until 1 of 2 cases are met
        // 1. The agent has found an unoccupied node
        // 2. All the nodes in the given in the list are occupied
        while (!foundSpace && !noSpace && counter < neighbors.size()) {
            //grab a random node from the list
            Node random_node = neighbors.get(counter);
            //check if the node is occupied and is not on fire
            if (!random_node.hasAgent() && !random_node.getNodeState().equals("fire")) {
                //since the agent is moving nodes the previous node should no longer have an agent
                currentNode.setHasAgent(false);

                //set the agents current node to the new random
                setCurrentNode(random_node);

                //updates agent current node
                currentNode.setHasAgent(true);

                //case 1 has been bet, exit the loop
                foundSpace = true;

            }
            // the node is occupied check the next neighbor
            else if (random_node.hasAgent()) {
                //continue checking for an empty space
                counter++;
                // case 2  has been been met all the nodes are occupied
                if (counter > neighbors.size()) {
                    noSpace = true;
                }
            }
        }
    }

    /**
     * @return true if a node is near a fire
     */
    private void checkForFire() {
        ArrayList<Node> neighbors = currentNode.getRoutingTable().getNeighbors();

        for (Node n : neighbors) {
            if (n.getNodeState().equals("near-fire"))
            {

                // place the agent on the yellow node
                setAgentXPos(n.getXPos());
                setAgentYPos(n.getYPos());
                n.setHasAgent(true);

                //clone the agent
                cloneAgent();

            }
        }

    }

    /**
     * Clones an agent on its neighbors nodes,which includes 'near-fire' nodes and 'standard' nodes
     */
    private void cloneAgent() {
        ArrayList<Node> neighbors = currentNode.getRoutingTable().getNeighbors();
        int id;

        for (Node n : neighbors) {

            if (!n.hasAgent() && !n.getNodeState().equals("fire")) {
                if (n.getNodeState().equals("near-fire") || n.getNodeState().equals("standard")) {
                    id = this.agentID + 1;

                    Agent agent_clone = new Agent(id, n);
                    n.setHasAgent(true);
                    agent_clone.start();

                    // the starting agents job is done, pause its thread
                    try {
                        this.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }
        }
    }

    /**
     * @return The node the agent is on
     */
    public Node getCurrentNode() {
        return currentNode;
    }

    /**
     * Set the node the agent is on
     *
     * @param node
     */
    private void setCurrentNode(Node node) {
        currentNode = node;
    }

    /**
     * @return if the agent is on a fire Node
     */
    private boolean checkIfDie() {
        if (currentNode.getNodeState().equals("fire")) {
            return true;
        }
        return false;
    }

    /**
     * @return true if the agent is performing an random walk
     */
    private boolean getIsSearching() {
        return isSearching;
    }

    /**
     * @return agents x positions
     */
    public int getAgentXPos() {
        return xpos;
    }

    /**
     * @return agents y position
     */
    public int getAgentYPos() {
        return ypos;
    }

    /**
     * Set the agents x position
     *
     * @param x
     */
    public void setAgentXPos(int x) {
        xpos = x;
    }

    /**
     * @param y
     */
    public void setAgentYPos(int y) {
        xpos = y;
    }


    @Override
    public String toString() {
        return "Agent: " + agentID + " " + " Node " + currentNode;
    }


}
