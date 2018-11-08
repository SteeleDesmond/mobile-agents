package MobileAgents.node;

import java.util.ArrayList;

/**
 * Used by Nodes and the Station to store a list of its neighbors. Allows for additional functionality that we could
 * decide to use later on.
 */
public class RoutingTable {

    private ArrayList<Node> neighbors = new ArrayList<>();

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public void add(Node n) {
        neighbors.add(n);
    }
}
