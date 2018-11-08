package MobileAgents.node;

import MobileAgents.agents.Message;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Holds different states --> Represented via State string. States are: "standard", "station", "fire", "near-fire"
 * State strings must be exact.
 *
 * Each node is a Runnable. Nodes contain useful information like their location, a unique id, a hasAgent boolean, a
 * state, and a BlockingQueue for message passing.
 */
public class Node implements NodeInterface {

    private int xPos;
    private int yPos;
    private String state;
    private Boolean hasAgent;
    private RoutingTable rt; // Contains list of neighboring Nodes
    private LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>(10000); // Queue of messages
    private Thread thread;
    private boolean running;
    // Give each Node a unique ID
    private static int nextId;
    private int nodeId;

    /**
     * Initialize the thread's values and start the Node
     */
    public Node(int x, int y, String state) {
        setNodeState(state);
        xPos = x;
        yPos = y;
        hasAgent = false;
        this.nodeId = nextId;
        nextId++;
        thread = new Thread(this);
    }

    /**
     * Get the Node ID
     * @return node ID
     */
    public int getNodeId() {
        return nodeId;
    }

    /**
     * Get the x Pos
     * @return x Pos of the Node
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * get the y Pos
     * @return y Pos of the Node
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Get the RoutingTable of the Node. the RoutingTable contains this Node's Node neighbors.
     * @return The RoutingTable object
     */
    public RoutingTable getRoutingTable() {
        return rt;
    }

    /**
     * Set the RoutingTable of the Node. This is only used by the Map.
     * @param rt the RoutingTable to assign the Node
     */
    public void setRoutingTable(RoutingTable rt) {
        this.rt = rt;
    }

    /**
     * Check if an Agent is currently visiting this Node
     * @return A boolean representing whether there is an Agent or not
     */
    public Boolean hasAgent() {
        return hasAgent;
    }

    /**
     * Tell the Node whether or not there is an Agent on the Node
     * @param hasAgent the bool representing whether there is an agent or not
     */
    public void setHasAgent(Boolean hasAgent) {
        this.hasAgent = hasAgent;
    }

    /**
     * Get the current state of the node. Possible States are: "standard", "station", "fire", "near-fire"
     * @return a possible state
     */
    public String getNodeState() {
        return state;
    }

    /**
     * Set the current state of the node. Possible States are: "standard", "station", "fire", "near-fire"
     * @param state the state to assign the Node
     */
    public void setNodeState(String state) {
        if(!(state.equals("standard") || state.equals("station") || state.equals("fire") || state.equals("near-fire"))) {
            System.out.println("Error setting Node state for Node " + nodeId + " Please check spelling");
            return;
        }
        this.state = state;
    }

    /**
     * toString override will print the Node's position
     * @return a String representing the Node and its position
     */
    @Override
    public String toString() {
        return "Node " + nodeId + ": " + xPos + " " + yPos;
    }

    /**
     * Start the Node's thread
     */
    public void startNode() {
        running = true;
        thread.start();
    }

    /**
     * Stop the Node's actions
     */
    public void terminate() {
        running = false;
    }

    public void sendMsg(Message msg) {
        queue.add(msg);
        //System.out.println(msg.getMsg());
    }

    /**
     * Implementation of the Runnable interface from the NodeInterface class.
     * The thread waits to receive a message from the queue. When a message is received it is sent to handleMessage().
     */
    @Override
    public void run() {
        while(running) {
            try {
                Message msg = queue.poll(1, TimeUnit.MILLISECONDS);
                // If this Node received a message handle it
                if(msg != null) {
                    handleMessage(msg);
                }
                // If this Node has been set on fire kill this Node
                if(getNodeState().equals("fire")) {
                    running = false;
                    //System.out.println("node terminated");
                    msg = queue.poll(100, TimeUnit.MILLISECONDS);
                    if(msg != null) {
                        System.out.println("Message lost at node " + nodeId);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read a message's contents and determine how to handle it (where to send it).
     * @param msg the message given to the node
     * @throws InterruptedException
     */
    public void handleMessage(Message msg) throws InterruptedException {
        //setHasAgent(true); // For testing the display only
        //sleep(1000); // for testing

        int randomNeighbor = ThreadLocalRandom.current().nextInt(rt.getNeighbors().size());
        Node n = rt.getNeighbors().get(randomNeighbor);

        // If the randomly chosen neighbor is on fire then select a different neighbor
        while(n.getNodeState().equals("fire")) {
            randomNeighbor = ThreadLocalRandom.current().nextInt(rt.getNeighbors().size());
            n = rt.getNeighbors().get(randomNeighbor);
        }
        n.sendMsg(msg);
    }
}
