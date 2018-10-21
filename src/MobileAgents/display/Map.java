package MobileAgents.display;

import MobileAgents.config.Configuration;
import MobileAgents.node.Node;

import java.awt.*;
import java.util.ArrayList;

/**
 * Map used by the display object. Stores a list of nodePoints for the display
 */
public class Map {

    private DisplayController dc;
    private ArrayList<Node> nodes = new ArrayList<>(); // Nodes in the map

    public Map(Configuration config, DisplayController dc) {

        this.dc = dc;

        // Initialize the Nodes used in the simulation
        for (Point p : config.getNodes()) {
            nodes.add(new Node((int) p.getX(), (int) p.getY()));
        }
        buildMap();
        dc.displayMap(config.getNodes(), config.getEdgeStarts(), config.getEdgeEnds());
    }

    private void buildMap() {

        // Print the node IDs
        for (Node n : nodes) {
            System.out.println("Node " + n.getNodeId() + " = " + n.getxPos() + " " + n.getyPos());
        }
    }

    /**
     * Loop over the list of nodes and check if their state has changed. If it has, tell the display to paint the node
     */
    public void checkNodeStates() {

    }
}
