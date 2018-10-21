package MobileAgents.display;

import MobileAgents.agents.Agent;
import MobileAgents.node.Node;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.*;
import java.util.ArrayList;

public class DisplayController {
    /** Graphics Components **/
    @FXML Circle refCircle;
    @FXML GridPane mapGrid;

    public DisplayController() {
    }

    /**
     *  Display the initial map with edges and nodes
     */
    public void displayMap(ArrayList<Point> nodes, ArrayList<Point> edges) {

        //line1 = new Line(150,300,270,400);
        //node = new Circle(x,y,10);
    }

    /**
     * Takes a node and displays it
     * @param node
     */
    public void paintNode(Node node) {

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
