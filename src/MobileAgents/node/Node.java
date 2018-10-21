package MobileAgents.node;

import javafx.scene.shape.*;

/**
 * Contains fire properties --
 * Holds different states: isDead, isOkay, nearFire, hasAgent
 */
public class Node {
    private int xPos;
    private int yPos;

    // We may need to implement a different way of tagging the nodes later. This method is from Roman's slides
    private static int nextId;
    private int nodeId;

    public Node() {
    }

    public Node(int x, int y) {
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
}
