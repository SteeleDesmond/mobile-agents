package MobileAgents.node;

import java.util.ArrayList;

public class RoutingTable {

    private ArrayList<Node> neighbors = new ArrayList<>();

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public void add(Node n) {
        neighbors.add(n);
    }

    public void addAll() {

    }
}
