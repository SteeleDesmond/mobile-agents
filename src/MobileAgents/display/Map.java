package MobileAgents.display;

import MobileAgents.config.Configuration;
import MobileAgents.config.MultiPoint;
import MobileAgents.node.Node;
import MobileAgents.node.*;

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
        // Initialize the map of node objects
        buildMap();
        // Initialize the display
        dc.displayMap(nodes, config.getEdges());
    }

    /**
     *
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
            for(Node node : assignNodeNeighbors(n)) {
                rt.add(node);
            }
            n.setRoutingTable(rt);
        }
        // Set state of nodes that are near initial fires
        for (Node n : nodes) {
            if (n.getNodeState().equals("fire")) {
                for (Node neighbors : n.getRoutingTable().getNeighbors()) {
                    neighbors.setNodeState("near-fire");
                    //dc.paintNode(neighbors);
                }
            }
        }
        printNodes();
    }

    /**
     * Builds a list of nodes that neighbor a given node
     * @param n The node
     * @return A list of nodes that neighbor the node given
     */
    private ArrayList<Node> assignNodeNeighbors(Node n) {

        ArrayList<Node> neighbors = new ArrayList<>();

        //loop through the edges
        for(MultiPoint p : config.getEdges()) {
            double x1 = p.getCoordinates()[0]; // edge start x
            double y1 = p.getCoordinates()[1]; // edge start y
            double x2 = p.getCoordinates()[2]; // edge end x
            double y2 = p.getCoordinates()[3]; // edge end y

            //if the edge start positions are equal to the nodes positions
            if(x1 == n.getXPos() && y1 == n.getYPos()) {
                // Find its neighbor node
                for(Node node : nodes) {
                    if(node.getXPos() == x2 && node.getYPos() == y2) {
                        neighbors.add(node);
                    }
                }
            }
            // if the edge end positions are equal to the nodes positions
            if(x2 == n.getXPos()  && y2 == n.getYPos()) {
                // Find its neighbor node
                for(Node node : nodes) {
                    if(node.getXPos() == x1 && node.getYPos() == y1) {
                        neighbors.add(node);
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Loop over the list of nodes and check if their state has changed. If it has, tell the display to paint the node
     */
    public void paintNodes()
    {
        for(Node n : nodes) {
            dc.paintNode(n);
        }
    }

    public boolean isStarted() {
        return dc.isStarted();
    }

    /**
     * Used by Coordinator to spread the fire
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     *  Print the nodes to the console
     */
    public void printNodes() {
        for (Node n : nodes) {
            System.out.println("Node " + n.getNodeId() + "(" + n.getNodeState() + "): = " + n.getXPos() + " " + n.getYPos());
            System.out.println("Node's Neighbors:");
            for (Node node : n.getRoutingTable().getNeighbors()) {
                System.out.println("Node Neighbor " + node.getNodeId() + "(" + node.getNodeState() + "): = " + node.getXPos() + " " + node.getYPos());
            }
        }
    }
}
