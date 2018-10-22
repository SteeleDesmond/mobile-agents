package MobileAgents.display;

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
    private boolean started;
    private ArrayList<Circle> nodeCircles = new ArrayList<>();

    /**
     * Display the initial map of nodes and edges. The FXML file contains a StackPane with the nodePane on
     * top of the edgePane.
     * @param nodes A list of node objects containing locations and states
     * @param edgeStarts A list of starting locations of edges
     * @param edgeEnds A list of ending locations of edges
     */
    public void displayMap(ArrayList<Node> nodes, ArrayList<Point> edgeStarts, ArrayList<Point> edgeEnds) {

        // Display the nodes
        for(Node n : nodes) {
            Circle node = new Circle(n.getXPos()*75, n.getYPos()*75, 10);
            nodeCircles.add(node);
            nodePane.getChildren().add(node);
            paintNode(n);
        }

        // Display the edges between nodes
        for(int i = 0; i < edgeStarts.size() && i < edgeEnds.size(); i++) {
            Line line = new Line(edgeStarts.get(i).getX()*75, edgeStarts.get(i).getY()*75,
                                    edgeEnds.get(i).getX()*75, edgeEnds.get(i).getY()*75);
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

    @FXML
    public void startButtonPressed() {
        started = true;
    }

    public boolean isStarted() {
        return started;
    }
}
