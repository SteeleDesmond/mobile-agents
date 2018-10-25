package MobileAgents.node;

import MobileAgents.config.MultiPoint;
import javafx.scene.shape.*;
import java.awt.*;
/**
 * Holds different states --> Represented via State string. States are: "standard", "station", "fire", "near-fire"
 * State strings must be exact
 */
public class Node implements NodeInterface {

    private RoutingTable rt;
    private int xPos;
    private int yPos;
    private String state;
    private Boolean hasAgent;

    // We may need to implement a different way of tagging the nodes later. This method is from Roman's slides
    private static int nextId;
    private int nodeId;

    // Used by the Station
    public Node() {
    }

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

    public int getNodeId() {
        return nodeId;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public RoutingTable getRoutingTable() {
        return rt;
    }

    public void setRoutingTable(RoutingTable rt) {
        this.rt = rt;
    }

    public Boolean hasAgent() {
        return hasAgent;
    }

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
    }
}
