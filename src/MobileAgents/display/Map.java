package MobileAgents.display;

import MobileAgents.config.Configuration;
import MobileAgents.config.MultiPoint;
import MobileAgents.node.Node;
import MobileAgents.node.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Map used for the simulation. Provides a reference to all nodes in the simulation to easily update the display and
 * to easily spread the fire.
 */
public class Map {

    private DisplayController dc;
    private Configuration config;
    private ArrayList<Node> nodes = new ArrayList<>(); // Nodes in the map
    private Station station;

    /**
     * On creation, build the initial map used by this class and pass the map construction to the display.
     * @param config The config file given for the simulation
     * @param dc The display controller to use
     */
    public Map(Configuration config, DisplayController dc) {
        this.dc = dc;
        this.config = config;
        buildMap(); // Initialize the map of node objects
        dc.displayMap(nodes, config.getEdges()); // Initialize the display
    }

    /**
     * Initialize the map of node objects. Assign routing tables to nodes and set initial states
     */
    private void buildMap() {
        // Construct the nodes and add them to the nodes list
        for (Point p : config.getNodes()) {
            // If it is the base station
            if(p.getX() == config.getStation().get(0).getX() && p.getY() == config.getStation().get(0).getY()) {
                station = new Station((int) p.getX(), (int) p.getY(), "station");
                nodes.add(station);
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
                }
            }
        }
        //printNodes();
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
     * Update the display
     */
    public void paintMap() {
        paintNodes();
        paintAgentTable();
    }

    /**
     * Loop over the list of nodes and check if their state has changed. If it has, tell the display to paint the node
     */
    private void paintNodes() {
        for(Node n : nodes) {
            dc.paintNode(n);
        }
    }

    /**
     * Give the display a new list of agent information to display
     */
    private void paintAgentTable() {
        dc.updateTable(station.getAgentsList());
    }

    /**
     *  Spreads Fire to nodes
     */
    public void spreadFire() {
        startFire();
        createNearFires();
    }

    /**
     *  Sets yellow-nodes to the on 'fire' state
     */
    private void startFire() {

        // If a node is near fire set it on fire
        for (Node n : nodes) {
            // set near fires to on fire
            if (n.getNodeState().equals("near-fire")) {
                n.setNodeState("fire");

            }
        }
    }

    /**
     *   Sets nodes closest to the fires to the 'near-fire' state
     */
    private void createNearFires() {

        // If a node is near a new fire set its state to near-fire
        for (Node n : nodes) {
            // If the node is on fire look at its neighbors
            if (n.getNodeState().equals("fire")) {

                for (Node neighbor : n.getRoutingTable().getNeighbors()) {
                    // If the node's neighbor isn't on fire already set it to near fire
                    if (!neighbor.getNodeState().equals("fire")) {
                        neighbor.setNodeState("near-fire");
                    }
                }
            }
        }
    }

    /**
     * Start the nodes' threads
     */
    public void startNodes() {
        for(Node n : nodes) {
            n.startNode();
        }
    }

    /**
     *  Print the nodes to the console for testing.
     */
    public void printNodes() {
        for (Node n : nodes) {
            System.out.println("Node " + n.getNodeId() + "(" + n.getNodeState() + "): = "
                                       + n.getXPos() + " " + n.getYPos());
            System.out.println("Node's Neighbors:");
            for (Node node : n.getRoutingTable().getNeighbors()) {
                System.out.println("Node Neighbor " + node.getNodeId() + "(" + node.getNodeState()
                                                    + "): = " + node.getXPos() + " " + node.getYPos());
            }
        }
    }

    /**
     *  Print the agents on the map to the console for testing.
     */
    public void printAgents() {
        for(Node n : nodes) {
            if(n.hasAgent()) {
              System.out.println(n);
            }
        }
    }

    /**
     * @return The node with a state of "station"
     */
    public Station getStationNode() {
        return station;
    }

    /**
     * @return All the nodes in the map
     */
    public ArrayList<Node> getAllNodes() {
        return nodes;
    }

    /**
     * @return true if the start button is clicked
     */
    public boolean isStarted() {
        return dc.isStarted();
    }

    /**
     * Check if all the nodes in the map are on fire or not.
     * @return True if all the nodes in the map are on fire
     */
    public boolean isFinished() {
        for (Node n : nodes) {
            if (n.getNodeState().equals("fire")) {
                return false;
            }
        }
        return true;
    }
}
