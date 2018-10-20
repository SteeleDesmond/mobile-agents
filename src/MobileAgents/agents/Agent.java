package MobileAgents.agents;

public class Agent extends Thread {
    private String agentName; // the agents name
    public int currentNode; // the current node the agent is at
    public int agentID;

    public Agent(String name, int node, int id) {
        currentNode = node;
        agentName = name;
        agentID = id;
    }

    /**
     *   Agents Thread
     */
    @Override
    public void run() {
        try {
            while(true) {
                updateCurrentNode();
                Thread.sleep(100);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateCurrentNode() {
        if(currentNode > 4) {
            currentNode = 0;
        }
        else {
            currentNode++;
        }
    }

    @Override
    public synchronized String toString() {
        return agentName + " Node " + currentNode;
    }
}
