package MobileAgents.main;

import MobileAgents.config.Configuration;
import MobileAgents.display.Map;


/**
 * Instantiates and manage all threads
 */
public class Coordinator extends Thread  {

    private Map map;
    private boolean startFire;

    public Coordinator(Configuration config) {
       map = new Map(config, MainApp.getDisplayController());
       map.startNodes();


    }

    /**
     * Called continuously by MainApp. Updates display
     */
    public void update()
    {
        map.paintNodes();

        if(map.isStarted() && startFire == false)
        {
            this.start();
            startFire = true;
            System.out.println("Fire will start to spread in 5 seconds");
        }
    }

    /**
     *  Coordinators Thread, spreads fire every 10 seconds
     */
    @Override
    public void run() {
        try
        {
            //give the agent some time to wander before fire starts
            Thread.sleep(5000);
            //execute threads functionailty
            while(true)
            {
                //spread fire every 10 seconds
                 Thread.sleep(10000);
                 map.spreadFire();

            }


        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
