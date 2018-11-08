package MobileAgents.main;

import MobileAgents.config.Configuration;
import MobileAgents.display.Map;

import java.util.TimerTask;
import java.util.Timer;

import MobileAgents.agents.Agent;


/**
 * Coordinator manages the simulation. Instantiates and manage all threads. It creates a map based on the given config
 * file, manages the start button press and fire spreading countdown, and tells the map to check for changes to display.
 */
public class Coordinator extends Thread {

    private Map map;
    private Agent agent; // The initial agent created at the station
    private int fireSpreadTimer = 10000; // ms
    private boolean startFire;
    private boolean done = false; //controls the fire spreading  thread
    private boolean finished = false; // signals that all threads have been killed

    /**
     * On creation, generate the map and start the node threads.
     * @param config the config file given. The config file path is assigned in MainApp.
     */
    public Coordinator(Configuration config) {
        map = new Map(config, MainApp.getDisplayController());
        map.startNodes();
    }

    /**
     * Called continuously by MainApp. Updates the display and checks if the simulation is started or over.
     */
    public void update() {

        map.paintMap();

        // checks if the start button has been pressed
        if (map.isStarted() && !startFire) {
            startMap();
        }

        // all the nodes are on fire so kill all the threads
        if (map.isFinished()) {
            map.paintMap(); // paint the remaining fire node
            finished = true;
        }
    }

    /**
     * If the start button is clicked:
     * 1. Countdown for the fire spread will start
     * 2. Initialize 1 agent at the base station and start its thread
     */
    private void startMap() {

        //start the fire thread
        this.start();
        startFire = true;

        // initialize the agent at the base station and start its thread
        agent = new Agent(0, map.getStationNode());
        map.getStationNode().addInitialAgent();
        agent.start();
        System.out.println("Initial agent created at the base station");

        // help method that counts down the fire spreading
        countDownFireStart();
    }

    /**
     * Coordinators Thread, spreads fire every 10 seconds
     */
    @Override
    public void run() {
        try {
            //execute threads functionality
            while (!done) {
                //spread fire every 10 seconds
                Thread.sleep(fireSpreadTimer);
                map.spreadFire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simple function that prints out when the fire will start after the start button is pressed
     */
    private void countDownFireStart() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = Integer.parseInt(Integer.toString(fireSpreadTimer / 1000));

            public void run() {
                System.out.println("fire will start spreading in " + i--);
                if (i < 0)
                    timer.cancel();
            }
        }, 0, 1000);
    }

    /**
     * Terminate all running threads
     */
    public void killAll() {

        //kill the main agent thread
        agent.setDone(true);

        //kill all the clone agents threads
        agent.killAllCloneAgentsThreads();

        //kill any remaining node threads (safety check -- they should already be finished)
        for (int i = 0; i < map.getAllNodes().size(); i++) {
            map.getAllNodes().get(i).terminate();
        }

        done = true; // kill fire spreading thread

        finished = true;

        System.out.println("All threads have ended, program is finished");
    }

    /**
     * @return if the Coordinator is done with its job
     */
    public boolean isFinished() {
        return finished;
    }
}
