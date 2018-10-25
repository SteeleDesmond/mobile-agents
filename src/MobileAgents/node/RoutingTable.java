package MobileAgents.node;

import java.util.ArrayList;

/**
 * Used by Sensors to store its neighbors, used by Station to store a list of all agents
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
