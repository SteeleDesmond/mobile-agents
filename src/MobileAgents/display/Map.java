package MobileAgents.display;

import MobileAgents.agents.Agent;
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
    private ArrayList<Node> nodes = new ArrayList<>(); // Nodes in the map

    private Configuration config;

    public Map(Configuration config, DisplayController dc)
    {

        this.dc = dc;
        this.config = config;

        initializeMap();
        buildMap();
        updateMap();

        testFireSimulation();


    }

    /**
     *  Initialize the map and the display
     */
    public void initializeMap()
    {
        // Initialize the Nodes used in the simulation (This assumes there is only one station and one starting fire)
        for (Point p : config.getNodes()) {
            // If it is the base station
            if (p.getX() == config.getStation().get(0).getX() && p.getY() == config.getStation().get(0).getY()) {
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

        //initialize the display
        dc.displayMap(nodes, config.getEdges());
    }


    private void buildMap()
    {
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

       printNodes();

    }

    /**
     *  Currently updateMap's only job is check the states of the nodes
     */
    public void updateMap()
    {
        checkNodeStates();
        updateAgent();
    }

    /**
     *  Test the agents ability to randomly walk across the map
     */
    public void updateAgent()
    {

        double x = config.getStation().get(0).getX();
        double y = config.getStation().get(0).getY();

        //reference to the base station
        Node station = new Node((int)x,(int)y,"station");

        //Initialize the agent at the base station
        Agent agent1 = new Agent("Agent1", 1,station);
        agent1.start();

        System.out.print("Before Random Walk Agents Position = ");
        agent1.printAgentsPosition();

        agent1.randonWalk(getNodeNeighbors(station));

        System.out.print("After Random Walk Agents Position = ");
        agent1.printAgentsPosition();

        //change the node states that the agent is sitting at
        agent1.currentNode.setNodeState("agent");


        //paint the node's color black for testing
        int newX = (int)agent1.currentNode.getNodePos().getXCoord();
        int newY = (int)agent1.currentNode.getNodePos().getYCoord();


        Node tempNode = new Node(newX,newY,"agent");

        dc.paintNode(tempNode);

    }

    /**
     * Builds a list of nodes that neighbor a given node
     * @param n The node
     * @return A list of nodes that neighbor the node given
     */
    private ArrayList<Node> getNodeNeighbors(Node n)
    {

        ArrayList<Node> neighbors = new ArrayList<>();

        //loop through the edges
        for(MultiPoint p : config.getEdges())
        {
            double x1 = p.getCoords()[0]; // edge start x
            double y1 = p.getCoords()[1]; // edge start y
            double x2 = p.getCoords()[2];// edge end x
            double y2 = p.getCoords()[3]; //edge end y

            //if the edge start positions are equal to the nodes postions
            if(x1 == n.getXPos() && y1 == n.getYPos())
            {
                // Find its neighbor node
                for(Node node : nodes)
                {
                    if(node.getXPos() == x2 && node.getYPos() == y2)
                    {
                        neighbors.add(node);
                    }
                }
            }

            // if the edge end postions are equal to the nodes positions
            if(x2 == n.getXPos()  && y2 == n.getYPos())
            {
                // Find its neighbor node
                for(Node node : nodes)
                {
                    if(node.getXPos() == x1 && node.getYPos() == y1)
                    {
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
    public void checkNodeStates()
    {
        for(Node n : nodes) {
            dc.paintNode(n);
        }
    }

    public boolean start() {
        return dc.isStarted();
    }

    /**
     * Used by Coordinator to spread the fire
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     *  Used to test the spreading of fire
     */
    public void testFireSimulation()
    {
        // For testing! The nodes have to set their own state
        // ---------------------------------------------------------------
        for (Node n : nodes) {
            if (n.getNodeState().equals("fire")) {
                for (Node neighbors : n.getRoutingTable().getNeighbors()) {
                    neighbors.setNodeState("near-fire");
                    dc.paintNode(neighbors);
                }
            }

        }
    }

    /**
     *  Print the nodes to the console
     */
    public void printNodes()
    {
        for (Node n : nodes) {
            System.out.println("Node " + n.getNodeId() + "(" + n.getNodeState() + "): = " + n.getXPos() + " " + n.getYPos());
            System.out.println("Node's Neighbors:");
            for(Node node : n.getRoutingTable().getNeighbors()) {
                System.out.println("Node Neighbor " + node.getNodeId() + "(" + node.getNodeState() + "): = " + node.getXPos() + " " + node.getYPos());
            }
        }

    }



}
