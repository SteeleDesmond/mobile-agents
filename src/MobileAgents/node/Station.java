package MobileAgents.node;

import MobileAgents.agents.Agent;
import MobileAgents.agents.Message;

import java.util.ArrayList;

/**
 * The base station of the simulation map. It is initialized in the Map class and contained in the nodes array with
 * other nodes. Follows the NodeInterface which implements Runnable.
 * The Station is used to store a list of all agents currently on the map.
 */
public class Station extends Node {

    private RoutingTable neighbors = super.getRoutingTable();
    private ArrayList<Message> agentsList = new ArrayList<>();

    public Station(int x, int y, String state) {
        super(x, y, state);
    }

    /**
     * The Station needs to handle messages differently compared to a regular Node.
     * @param msg the message given to the Station
     *
     */
    @Override
    public void handleMessage(Message msg) {
        //System.out.println("Base station received a message");
        //setHasAgent(true);
        //agents.add(msg.getMsg()); // Store the message given in the agents list for now
        agentsList.add(msg);
        System.out.println("--- Printing all messages received at base station ---");
        for(Message m : agentsList) {
            System.out.println("'" + m.toString() + "'");
        }
    }
}
