package MobileAgents.main;

import MobileAgents.config.Configuration;
import MobileAgents.display.Map;

import java.util.TimerTask;
import java.util.Timer;

import MobileAgents.agents.Agent;

/**
 * Instantiates and manage all threads
 */
public class Coordinator extends Thread {

    private Map map;
    private boolean startFire;

    private Agent agent;

    private int fireSpreadTimer = 10000; // ms

    private boolean done = false;


    public Coordinator(Configuration config) {
        map = new Map(config, MainApp.getDisplayController());
        map.startNodes();
    }

    /**
     * Called continuously by MainApp. Updates display
     */
    public void update() {

        map.paintNodes();

        // checks if the start button has been processed
        // startMap is called only one
        if (map.isStarted() && !startFire) {
            startMap();
        }

        //all the nodes are on fire, kill all the threads
        if (map.isFinished()) {
            // paint the remaining fire node
            map.paintNodes();

            //kill all the thread
            killAll();
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
    public void countDownFireStart() {
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
        if(!done) {
            System.out.println("Program is finished, all the nodes have been set on fire");
        }

        // kill agents thread
        agent.setDone(true);
        //kill fire spreading thread
        this.done = true;
        //kill all the nodes threads
        for (int i = 0; i < map.getAllNodes().size(); i++) {
            map.getAllNodes().get(i).terminate();
        }
    }

    public boolean isDone() {
        return done;
    }
}
