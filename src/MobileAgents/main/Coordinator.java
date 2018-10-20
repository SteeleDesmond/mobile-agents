package MobileAgents.main;

import MobileAgents.agents.Agent;
import MobileAgents.config.Configuration;
import MobileAgents.display.Display;
import MobileAgents.display.Map;
import sun.security.krb5.Config;

/**
 * Instantiates and manage all threads
 */
public class Coordinator {

    private Map map;

    public Coordinator(String configPath) {
        //map = new Map(new Configuration(configPath));
    }

    public Agent agent1;
    public Display sceneDisplay;

    public void start() {
//        agent1.start();
//        sceneDisplay.start();
    }
}
