package MobileAgents.node;

import MobileAgents.agents.Agent;
import MobileAgents.agents.Message;

/**
 * The base station of the simulation map. It is initialized in the Map class and contained in the nodes array with
 * other nodes. Follows the NodeInterface which implements Runnable.
 * The Station is used to store a list of all agents currently on the map.
 */
public class Station extends Node {

    private RoutingTable neighbors = super.getRoutingTable();
    private RoutingTable agents = new RoutingTable();

    public Station(int x, int y, String state) {
        super(x, y, state);
        initializeFirstAgent();
    }

    /**
     * Example
     */
    private void initializeFirstAgent() {
        Agent agent = new Agent(1,this);
        //agents.add(agent);
    }

    @Override
    public void handleMessage(Message msg) {
        System.out.println("Base station received message");
    }
}
