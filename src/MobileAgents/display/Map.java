package MobileAgents.display;

import MobileAgents.config.Configuration;
import MobileAgents.node.Node;
import MobileAgents.node.RoutingTable;

import java.awt.*;
import java.util.ArrayList;

/**
 * Map used by the display object. Stores a list of nodePoints for the display
 */
public class Map {

    private DisplayController dc;
    private Configuration config;
    private ArrayList<Node> nodes = new ArrayList<>(); // Nodes in the map

    public Map(Configuration config, DisplayController dc) {

        this.dc = dc;
        this.config = config;
        // Initialize the Nodes used in the simulation (This assumes there is only one station and one starting fire)
        buildMap();
        // Initialize the display
        dc.displayMap(nodes, config.getEdgeStarts(), config.getEdgeEnds());

        checkNodeStates();
    }

    /**
     * Construct the Node objects
     */
    private void buildMap() {

        // Construct the nodes and add them to the nodes list
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

        // Assign routing tables to nodes
        for(Node n : nodes) {
            RoutingTable rt = new RoutingTable();
            for(Node node : getNodeNeighbors(n)) {
                rt.add(node);
            }
            n.setRoutingTable(rt);
        }

        // Print the nodes to console
        for (Node n : nodes) {
            System.out.println("Node " + n.getNodeId() + "(" + n.getNodeState() + "): = " + n.getXPos() + " " + n.getYPos());
            System.out.println("Node's Neighbors:");
            for(Node node : n.getRoutingTable().getNeighbors()) {
                System.out.println("Node Neighbor " + node.getNodeId() + "(" + node.getNodeState() + "): = " + node.getXPos() + " " + node.getYPos());
            }
        }
    }

    /**
     * Builds a list of nodes that neighbor a given node
     * @param n The node
     * @return A list of nodes that neighbor the node given
     */
    private ArrayList<Node> getNodeNeighbors(Node n) {

        ArrayList<Node> neighbors = new ArrayList<>();

        // Loop through the edgeStarts and edgeEnds lists
        for(int i = 0; i < config.getEdgeStarts().size() && i < config.getEdgeEnds().size(); i++) {
            // If the edgeStart position is at the node passed in add its connected neighbor to the list
            if(config.getEdgeStarts().get(i).getX() == n.getXPos() && config.getEdgeStarts().get(i).getY() == n.getYPos()) {
                // Find the neighbor node
                for(Node node : nodes) {
                   if(node.getXPos() == config.getEdgeEnds().get(i).getX() && node.getYPos() == config.getEdgeEnds().get(i).getY()) {
                       neighbors.add(node);
                       break;
                   }
                }
            }
            // If the edgeEnd position is at the node passed in add its connected neighbor to the list
            else if(config.getEdgeEnds().get(i).getX() == n.getXPos() && config.getEdgeEnds().get(i).getY() == n.getYPos()) {
                for(Node node : nodes) {
                    // Find the neighbor node
                    if(node.getXPos() == config.getEdgeStarts().get(i).getX() && node.getYPos() == config.getEdgeStarts().get(i).getY()) {
                        neighbors.add(node);
                        break;
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Loop over the list of nodes and check if their state has changed. If it has, tell the display to paint the node
     */
    public void checkNodeStates() {

        // For testing! The nodes have to set their own state
        // ---------------------------------------------------------------
        for(Node n : nodes) {
            if(n.getNodeState().equals("fire")) {
                for(Node neighbors : n.getRoutingTable().getNeighbors()) {
                    neighbors.setNodeState("near-fire");
                    dc.paintNode(neighbors);
                }
            }
        }
        // ---------------------------------------------------------------
    }
}
