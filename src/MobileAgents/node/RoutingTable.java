package MobileAgents.node;

import java.util.ArrayList;

/**
 * Used by Sensors to store its neighbors, used by Station to store a list of all agents
 */
public class RoutingTable {

    private ArrayList<Node> nodes = new ArrayList<>();

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void add(Node n) {
        nodes.add(n);
    }

    public void addAll() {

    }
}
