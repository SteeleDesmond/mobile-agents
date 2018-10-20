package MobileAgents.display;

import MobileAgents.config.Configuration;
import MobileAgents.node.Node;
import javafx.scene.shape.Line;

import java.util.List;

/**
 * Map used by the display object. Stores a list of nodes for the display
 */
public class Map
{

    /** Node used to build node **/
    private Node node1;
    private Node node2;
    private Node node3;
    private Node node4;

    private Node[] nodes;

    /** Lines used to connect the nodes **/
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;

    private Line[] lines;

    private Configuration config;
    private DisplayController dc;

    public Map(Configuration config, DisplayController dc) {
        this.config = config;
        this.dc = dc;
        drawMiniMap();
    }

    public void drawMiniMap() {

        //initialize the nodes(node)
        node1 = new Node(0,295,1);
        node2 = new Node(270,400,2);
        node3 = new Node(405,500,3);
        node4 = new Node(450,265,4);

        nodes = new Node[4];
        nodes[0] = node1;
        nodes[1] = node2;
        nodes[2] = node3;
        nodes[3] = node4;

        /**Initialize the lines **/

        //connect node 1 to node 2
        line1 = new Line(150,300,270,400);

        //connect node 2 to node 3
        line2 = new Line(270,400,410,500);

        //connect  node 3 to node 4
        line3 = new Line(410,500,450,270);

        //connect node 4 to node 1
        line4 = new Line(450,270,150,300);

        lines = new Line[4];

        lines[0] = line1;
        lines[1]= line2;
        lines[2] = line3;
        lines[3]= line4;
    }

    public void mobileAgentMap() {

    }
}
