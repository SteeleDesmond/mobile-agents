package MobileAgents.node;

import MobileAgents.agents.Message;

/**
 * Node interface used by Node and Station objects. The interface allows Stations and Node objects to be treated the
 * same. In this case, our Station extends Node which uses this interface.
 */
public interface NodeInterface extends Runnable {

    String getNodeState();
    void setNodeState(String state);
    RoutingTable getRoutingTable();
    void handleMessage(Message msg) throws InterruptedException;
}
