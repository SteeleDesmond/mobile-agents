package MobileAgents.node;

import MobileAgents.config.MultiPoint;
import javafx.scene.shape.*;
import java.awt.*;
/**
 * Contains fire properties --
 * Holds different states --> Represented via State string. States are: "standard", "station", "fire", "near-fire"
 * State strings must be exact
 */
public class Node implements NodeInterface {

    private RoutingTable rt;
    private int xPos;
    private int yPos;
    private MultiPoint nodePos;
    private String state;

    private Boolean isOccupied = false;

    // We may need to implement a different way of tagging the nodes later. This method is from Roman's slides
    private static int nextId;
    private int nodeId;

    public Node() {
    }

    /**
     * Initialize routing table
     */
    public Node(int x, int y, String state) {
        this.state = state;
        xPos = x;
        yPos = y;
        nodePos = new MultiPoint(xPos,yPos);
        this.nodeId = nextId;
        nextId++;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getNodeState() {
        return state;
    }

    public MultiPoint getNodePos(){return nodePos; }

    public Boolean getIsOccupied(){return isOccupied;}

    public void setNodeState(String state) {
        this.state = state;
    }

    public void setNodePos(MultiPoint p){nodePos = p;}

    public RoutingTable getRoutingTable() {
        return rt;
    }

    public void setRoutingTable(RoutingTable rt) {
        this.rt = rt;
    }


    @Override
    public void run() {

    }
    @Override
    public String toString()
    {
        return "This nodes current postion is " + nodePos.toString();
    }


}
