package MobileAgents.node;

import MobileAgents.agents.Message;

public interface NodeInterface extends Runnable {

    String getNodeState();
    void setNodeState(String state);
    RoutingTable getRoutingTable();
    void handleMessage(Message msg) throws InterruptedException;
}
