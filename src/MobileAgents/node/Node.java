package MobileAgents.node;

import javafx.scene.shape.*;

/**
 * Contains fire properties --
 * Holds different states: isDead, isOkay, nearFire, hasAgent
 */
public class Node {
    private int node_xpos;
    private int node_ypos;

    public Circle node;

    public Node() {
    }

    public Node(int x, int y, int n) {

        node_xpos = x;
        node_ypos = y;

        node = new Circle(x,y,10);
    }

    public int getNode_xpos()
    {
        return node_xpos;
    }

    public int getNode_ypos()
    {
        return node_ypos;
    }

}
