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

    public boolean done = false;

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
            while (!done) {
                if (currentNode.getRoutingTable() != null) {

                    if (getIsSearching()) {
                        //randomly walk node to node searching for fire
                        randomWalk();
                        //check for nearby fires after walking onto a node
                        if (checkForFire()) {
                            //the agent has found a nearby fire, end random walk
                            setIsSearching(false);
                            cloneAgent();
                        }

                        //after the agent walks to a node, check if its set on fire, if so, terminate it
                        if (checkIfDie()) {
                            currentNode.terminate();
                            currentNode.setHasAgent(false);
                        }
                    }
                    // we should still clone agents even though the searching for a fire process is done
                    else {
                        if (checkForFire()) {
                            cloneAgent();
                        }
                    }

                }
                // slow down the random walk / cloning
                Thread.sleep(750);
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
        while (!foundSpace || !noSpace) {
            //grab a random node from the list
            Node random_node = neighbors.get(counter);
            //check if the node is
            if (!random_node.hasAgent()) {
                //since the agent is moving nodes, the previous node should no longer have an agent
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
                //exit the while loop
                if (counter >= neighbors.size()) {
                    noSpace = true;
                }
            }
        }

    }

    /**
     * @return true if a node is near a fire
     */
    private boolean checkForFire() {
        ArrayList<Node> neighbors = currentNode.getRoutingTable().getNeighbors();

        boolean foundFire = false;

        for (Node n : neighbors) {
            if (n.getNodeState().equals("near-fire")) {
                foundFire = true;

                //place the agent on the yellow node
                setAgentXPos(n.getXPos());
                setAgentYPos(n.getYPos());
                currentNode = n;

                n.setHasAgent(true);
            }
        }

        return foundFire;
    }

    /**
     * Clones an agent on its neighbors nodes,which includes 'near-fire' nodes and 'standard' nodes
     */
    private void cloneAgent() {
        ArrayList<Node> neighbors = currentNode.getRoutingTable().getNeighbors();
        int id;

        for (Node n : neighbors) {
            // if the neighbor node doesnt have an agent and isnt on fire, clone it
            if (!n.hasAgent() && !n.getNodeState().equals("fire")) {
                //clone agents onto blue and yellow nodes
                if (n.getNodeState().equals("near-fire") || n.getNodeState().equals("standard")) {
                    id = getAgentID() + 1;

                    Agent clone_agent = new Agent(id, n);

                    // Send new clone's information to the base station
                    Message msg = new Message(id, n.getNodeId());
                    currentNode.sendMsg(msg);
                    System.out.println("Message sent: " + msg.toString());

                    //let the node know if it has an agent on it
                    n.setHasAgent(true);
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
     * pass in true if we want to the agen tto continue to randomly walk
     */
    private void setIsSearching(boolean search) {
        isSearching = search;
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

    /**
     * @return the Agents ID
     */
    public int getAgentID() {
        return agentID;
    }


    @Override
    public String toString() {
        return "Agent: " + agentID + " " + " Node " + currentNode;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
