package MobileAgents.node;

import MobileAgents.agents.Agent;

public class Station extends Node {

    private RoutingTable neighbors = super.getRoutingTable();
    private RoutingTable agents = new RoutingTable();

    public Station(int x, int y, String state) {
        super(x, y, state);
        initializeAgents();
    }

    private void initializeAgents() {
        Agent agent = new Agent(1,this);
        //agents.add(agent);
    }
}
