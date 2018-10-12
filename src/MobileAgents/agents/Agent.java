package MobileAgents.agents;

import java.util.concurrent.BlockingQueue;

public class Agent implements Runnable
{
    private String agentName; // the agents name

    private final BlockingQueue <Integer > queue; // this que will hold contain the current node the agent is at

    public int currentNode; // the current node the agent is at

    public Agent(String name, int node,BlockingQueue<Integer> queue)
    {
        currentNode = node;
        agentName = name;
        this.queue = queue;
    }


    /**
     *  Currently this methods job is to update the node, the current agent is on
     *
     *  I wanted to simulate an agent transversing a nodes on a map
     */
    private void updatePosition()
    {
        currentNode++;

        if(currentNode > 4)
        {
            currentNode = 0;
        }


    }

    /**
     *
     */
    @Override
    public void run()
    {
            try
            {
                //lets move the agent from nodes 1 - 4
                for(int i = 1; i < 5; i++)
                {
                    queue.put(i);
                    //create an infinite loop
                    if (i == 4 )
                    {
                        i = 0;
                    }
                }
                System.out.println("Current Agent's Node Position is " + currentNode);

                //slow down the simulation so we can see the agent move to a new position
                Thread.sleep(1000);// will remove only used for testing

            }

            catch (InterruptedException e)
            {
                System.out.println(agentName + " was interrupted");
            }

    }

    @Override
    public synchronized String toString() {
        return agentName + " Node " + currentNode;
    }

}
