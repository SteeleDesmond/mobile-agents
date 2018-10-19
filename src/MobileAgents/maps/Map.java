package MobileAgents.maps;

import MobileAgents.nodes.Sensors;
import javafx.scene.shape.Line;

public class Map
{

    /** Sensors used to build mini map **/
    private Sensors sensor1;
    private Sensors sensor2;
    private Sensors sensor3;
    private Sensors sensor4;

    public Sensors[] sensors;

    /** Lines used to connect the sensors **/
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;

    public Line[] lines;

    public Map()
    {
       drawMiniMap();
    }

    public void drawMiniMap()
    {
        //initialize the sensors(nodes)
        sensor1 = new Sensors(145,295,1);

        sensor2 = new Sensors(270,400,2);

        sensor3 = new Sensors(405,500,3);

        sensor4 = new Sensors(450,265,4);

        sensors = new Sensors[4];
        sensors[0] = sensor1;
        sensors[1] = sensor2;
        sensors[2] = sensor3;
        sensors[3] = sensor4;



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


}
