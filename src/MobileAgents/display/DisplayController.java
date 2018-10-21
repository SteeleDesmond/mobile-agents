package MobileAgents.display;

import MobileAgents.node.Node;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.*;
import java.util.ArrayList;

public class DisplayController {
    /** Graphics Components **/
    @FXML GridPane nodeGrid;
    @FXML StackPane mapOverlay;

    public DisplayController() {
    }

    /**
     *  Display the initial map with edges and nodes
     */
    public void displayMap(ArrayList<Point> nodes, ArrayList<Point> edgeStarts, ArrayList<Point> edgeEnds) {

//        Line line1 = new Line(150,300,270,400);
//        Circle node = new Circle(100,100,10);
//        nodeGrid.getChildren().add(node);
//        nodeGrid.getChildren().add(line1);

        for(Point p : nodes) {
            Circle node = new Circle(0, 0, 10);
            //nodeGrid.getChildren().add(node);
            nodeGrid.add(node, (int) p.getX(), (int) p.getY());
        }
        for(int i = 0; i < edgeStarts.size() && i < edgeEnds.size(); i++) {
            Line line = new Line(edgeStarts.get(i).getX(), edgeStarts.get(i).getY(), edgeEnds.get(i).getX(), edgeEnds.get(i).getY());
            mapOverlay.getChildren().add(line);
        }
    }

    /**
     * Takes a node and displays it
     * @param x location
     * @param y location
     */
    public void paintNode(int x, int y) {

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
