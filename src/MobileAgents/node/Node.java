package MobileAgents.node;

import javafx.scene.shape.*;

/**
 * Contains fire properties --
 * Holds different states --> Represented via State string. States are: "standard", "station", "fire", "near-fire"
 * State strings must be exact
 */
public class Node {
    private int xPos;
    private int yPos;
    private String state;

    // We may need to implement a different way of tagging the nodes later. This method is from Roman's slides
    private static int nextId;
    private int nodeId;

    public Node() {
    }

    public Node(int x, int y, String state) {
        this.state = state;
        xPos = x;
        yPos = y;
        this.nodeId = nextId;
        nextId++;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
