package MobileAgents.main;

import MobileAgents.config.Configuration;
import MobileAgents.display.Map;
import MobileAgents.node.Node;

/**
 * Instantiates and manage all threads. Spreads the fire?
 */
public class Coordinator {

    private Map map;

    public Coordinator(Configuration config) {
       map = new Map(config, MainApp.getDisplayController());
    }

    /**
     * Called continuously by MainApp
     */
    public void update() {

        // Spread the fire
        if(map.start()) {
            // If it's near fire set it on fire
            for(Node n : map.getNodes()) {
                if(n.getNodeState().equals("near-fire")) {
                    n.setNodeState("fire");
                }
            }
            // If it's near a new fire set its state to near-fire
            for(Node n : map.getNodes()) {
                // If the node is fire look at its neighbors
                if(n.getNodeState().equals("fire")) {
                    // If the node's neighbor isn't on fire already set it to near fire
                    for(Node neighbor : n.getRoutingTable().getNeighbors()) {
                        if(!neighbor.getNodeState().equals("fire")) {
                            neighbor.setNodeState("near-fire");
                        }
                    }
                }
            }
            // repaint the nodes
            map.checkNodeStates();
        }
    }
}
