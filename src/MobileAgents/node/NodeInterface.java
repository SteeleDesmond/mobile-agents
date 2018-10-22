package MobileAgents.node;

public interface NodeInterface extends Runnable {

    int getXPos();
    int getYPos();
    String getNodeState();
    void setNodeState(String state);
    RoutingTable getRoutingTable();
}
