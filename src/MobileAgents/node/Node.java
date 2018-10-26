package MobileAgents.node;

import MobileAgents.agents.Message;
import MobileAgents.config.MultiPoint;
import javafx.scene.shape.*;

import java.awt.*;
import java.util.LinkedList;

/**
 * Holds different states --> Represented via State string. States are: "standard", "station", "fire", "near-fire"
 * State strings must be exact
 */
public class Node implements NodeInterface {

    private int xPos;
    private int yPos;
    private String state;
    private Boolean hasAgent;
    private RoutingTable rt; // Contains list of neighbors
    private LinkedList<Message> queue = new LinkedList<>(); // Queue of messages given to the Node

    // Give each Node a unique ID
    private static int nextId;
    private int nodeId;

    /**
     * Initialize routing table
     */
    public Node(int x, int y, String state) {
        this.state = state;
        xPos = x;
        yPos = y;
        hasAgent = false;
        this.nodeId = nextId;
        nextId++;
    }

    /**
     * Get the Node ID
     * @return node ID
     */
    public int getNodeId() {
        return nodeId;
    }

    /**
     * Get the x Pos
     * @return x Pos of the Node
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * get the y Pos
     * @return y Pos of the Node
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Get the RoutingTable of the Node. the RoutingTable contains this Node's Node neighbors.
     * @return The RoutingTable object
     */
    public RoutingTable getRoutingTable() {
        return rt;
    }

    /**
     * Set the RoutingTable of the Node. This is only used by the Map.
     * @param rt the RoutingTable to assign the Node
     */
    public void setRoutingTable(RoutingTable rt) {
        this.rt = rt;
    }

    /**
     * Check if an Agent is currently visiting this Node
     * @return A boolean representing whether there is an Agent or not
     */
    public Boolean hasAgent() {
        return hasAgent;
    }

    /**
     * Tell the Node whether or not there is an Agent on the Node
     * @param hasAgent the bool representing whether there is an agent or not
     */
    public void setHasAgent(Boolean hasAgent) {
        this.hasAgent = hasAgent;
    }


    public String getNodeState() {
        return state;
    }

    public void setNodeState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "This nodes position is " + xPos + " " + yPos;
    }

    @Override
    public void run() {
        System.out.println("test");
    }
}
