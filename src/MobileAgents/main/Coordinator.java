package MobileAgents.main;

import MobileAgents.agents.Agent;
import MobileAgents.config.Configuration;
import MobileAgents.display.DisplayController;
import MobileAgents.display.Map;

/**
 * Instantiates and manage all threads
 */
public class Coordinator {

    private Map map;

    public Coordinator(Configuration config) {
        map = new Map(config, MainApp.getDisplayController());
    }

    public void update() {
//        agent1.start();
//        sceneDisplayController.start();
    }
}
