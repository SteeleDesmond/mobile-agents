package MobileAgents.display;

import MobileAgents.config.MultiPoint;
import MobileAgents.node.Node;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.*;
import java.util.ArrayList;

public class DisplayController {
    /** Graphics Components **/
    @FXML private Pane nodePane;
    @FXML private Pane edgePane;
    private ArrayList<Circle> nodeCircles = new ArrayList<>();

    /**
     * Display the initial map of nodes and edges. The FXML file contains a StackPane with the nodePane on
     * top of the edgePane.
     * @param nodes A list of node objects containing locations and states
     * @param edges A list of of the locations of edges
     *
     */
    public void displayMap(ArrayList<Node> nodes, ArrayList<MultiPoint> edges) {

        int startX = 75; //
        int startY = 75;
        // Display the nodes
        for(Node n : nodes) {
            Circle node = new Circle(n.getXPos()*startX, n.getYPos()*startY, 10);
            nodeCircles.add(node);
            nodePane.getChildren().add(node);
            paintNode(n);
        }

        // Display the edges between nodes
        for (MultiPoint p : edges)
        {
            double x1 = p.getCoords()[0];
            double y1 = p.getCoords()[1];
            double x2 = p.getCoords()[2];
            double y2 = p.getCoords()[3];

            Line line = new Line( startX * x1, startY * y1, startX * x2, startY * y2);
            edgePane.getChildren().add(line);

        }
    }

    /**
     * Reads a given node's state and paints it an appropriate color
     * @param node Node to paint
     */
    public void paintNode(Node node) {
        Circle nodeToChange = new Circle();
        for(Circle c : nodeCircles) {
            if(c.getCenterX() == node.getXPos()*75 && c.getCenterY() == node.getYPos() * 75) {
                nodeToChange = c;
            }
        }
        // Paint standard node color
        if(node.getNodeState().equals("standard")) {
            nodeToChange.setFill(Color.DODGERBLUE);
        }
        else if(node.getNodeState().equals("near-fire")) {
            nodeToChange.setFill(Color.ORANGE);
        }
        // Paint fire color
        else if(node.getNodeState().equals("fire")) {
            nodeToChange.setFill(Color.ORANGERED);
        }
        // Paint station color
        else {
            nodeToChange.setFill(Color.GREEN);
        }
    }

    /**
     * Display the base station's routing table
     */
    public void displayTable() {
    }

    /**
     * Update the base station's routing table
     */
    public void updateTable() {

    }
}
