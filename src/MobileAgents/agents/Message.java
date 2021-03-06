package MobileAgents.agents;

import javafx.beans.property.SimpleStringProperty;

/**
 * Used to deliver information to and from the base station. The class contains information about new agents being
 * created on the map. Internally, it provides display properties about the agents so that the information can
 * eventually be given to the display.
 */
public class Message {

    private int agentId;
    private int nodeId;
    private static int nextId;
    private int messageId;
    // For displaying the message data
    private SimpleStringProperty id;
    private SimpleStringProperty location;

    public Message(int agentId, int nodeId) {
        this.agentId = agentId;
        this.nodeId = nodeId;
        id = new SimpleStringProperty(String.valueOf(agentId));
        location = new SimpleStringProperty(String.valueOf(nodeId));
        this.messageId = nextId;
        nextId++;
    }

    public int getAgentId() {
        return agentId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getMessageId() {
        return messageId;
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    @Override
    public String toString() {
        return "Message " + messageId + ": " +
                "New agent Id = " + agentId +
                ", Agent created at node " + nodeId;
    }
}
