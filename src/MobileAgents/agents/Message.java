package MobileAgents.agents;

/**
 * Used to deliver messages to and from the base station
 */
public class Message {

    private int agentId;
    private int nodeId;

    public Message(int agentId, int nodeId) {
        this.agentId = agentId;
        this.nodeId = nodeId;
    }

    public int getAgentId() {
        return agentId;
    }

    public int getNodeId() {
        return nodeId;
    }
}
