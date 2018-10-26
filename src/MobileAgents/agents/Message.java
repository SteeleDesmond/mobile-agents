package MobileAgents.agents;

/**
 * Used to deliver messages to and from the base station
 */
public class Message {

    String msg;

    public Message(String message) {
        msg = message;
    }

    public String getMsg() {
        return msg;
    }
}
