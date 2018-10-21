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

        // Initialize the Nodes used in the simulation (This assumes there is only one station and one starting fire)
        for (Point p : config.getNodes()) {
            // If it is the base station
            if(p.getX() == config.getStation().get(0).getX() && p.getY() == config.getStation().get(0).getY()) {
                nodes.add(new Node((int) p.getX(), (int) p.getY(), "station"));
            }
            // If it is the fire starting node
            else if (p.getX() == config.getFireStart().get(0).getX() && p.getY() == config.getFireStart().get(0).getY()) {
                nodes.add(new Node((int) p.getX(), (int) p.getY(), "fire"));
            }
            // Else it is a standard node
            else {
                nodes.add(new Node((int) p.getX(), (int) p.getY(), "standard"));
            }
        }
        // Initialize the display
        dc.displayMap(nodes, config.getEdgeStarts(), config.getEdgeEnds());
        buildMap();
    }

    private void buildMap() {

        // Print the node IDs
        for (Node n : nodes) {
            System.out.println("Node " + n.getNodeId() + "(" + n.getState() + "): = " + n.getxPos() + " " + n.getyPos());
            if(n.getState().equals("standard")) {
                dc.paintNode(n);
            }
        }
    }

    /**
     * Loop over the list of nodes and check if their state has changed. If it has, tell the display to paint the node
     */
    public void checkNodeStates() {

    }
}
