package MobileAgents.nodes;

import javafx.scene.shape.*;

public class Sensors
{

    private int node_xpos;
    private int node_ypos;

    private int nodeNumber;

    public Circle node;

    public Sensors(int x, int y, int n)
    {

        node_xpos = x;
        node_ypos = y;
        nodeNumber = n;

        node = new Circle(x,y,10);
    }


    public int getNode_xpos()
    {
        return node_xpos;
    }

    public int getNode_ypos()
    {
        return node_ypos;
    }

}
