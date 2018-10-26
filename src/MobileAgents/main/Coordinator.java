package MobileAgents.main;

import MobileAgents.config.Configuration;
import MobileAgents.display.Map;

/**
 * Instantiates and manage all threads
 */
public class Coordinator {

    private Map map;

    public Coordinator(Configuration config) {
       map = new Map(config, MainApp.getDisplayController());
       map.startNodes();
    }

    /**
     * Called continuously by MainApp. Spreads the nodes' fires to its neighbors and updates display.
     */
    public void update() {
        map.paintNodes(); // testing message passing

        // If the start button is clicked start the fire
        if(map.isStarted()) {
//            map.spreadFire();
        }
    }
}
