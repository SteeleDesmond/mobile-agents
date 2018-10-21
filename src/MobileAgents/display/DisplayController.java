package MobileAgents.display;

import MobileAgents.node.Node;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.*;
import java.util.ArrayList;

public class DisplayController {
    /** Graphics Components **/
    @FXML Pane nodePane;
    @FXML Pane edgePane;

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
            Circle node = new Circle((int) p.getX()*75, (int) p.getY()*75, 10);
            node.setFill(Color.DODGERBLUE);
            nodePane.getChildren().add(node);
        }
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
