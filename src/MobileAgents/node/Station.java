package MobileAgents.node;

import MobileAgents.agents.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The base station of the simulation map. It is initialized in the Map class and contained in the nodes array with
 * other nodes. Follows the NodeInterface which implements Runnable.
 * The Station is used to store a list of all agents currently on the map.
 */
public class Station extends Node {

    private RoutingTable neighbors = super.getRoutingTable();
    private ObservableList<Message> agentsList = FXCollections.observableArrayList();

    public Station(int x, int y, String state) {
        super(x, y, state);
    }

    /**
     * The ObservableList is used to store received unique messages. It is used by the display for additional
     * functionality capabilities later on.
     * @return A list of agent information received at the base station
     */
    public ObservableList<Message> getAgentsList() {
        return agentsList;
    }

    /**
     * Add the initial agent to the agents list
     */
    public void addInitialAgent() {
        agentsList.add(new Message(0, getNodeId()));
    }

    /**
     * The Station needs to handle messages differently compared to a regular Node. It will add non-duplicate messages
     * to the agents list.
     * @param msg the message given to the Station
     */
    @Override
    public void handleMessage(Message msg) {

        for(Message m : agentsList) {
            if(m.getMessageId() == (msg.getMessageId())) {
                return;
            }
        }
        System.out.println("Message Received at Station -- " + msg.toString());
        agentsList.add(msg);
    }
}
