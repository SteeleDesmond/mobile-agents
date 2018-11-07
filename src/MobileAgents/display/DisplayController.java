package MobileAgents.display;

import MobileAgents.agents.Message;
import MobileAgents.config.MultiPoint;
import MobileAgents.node.Node;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

import javafx.event.*;

import java.util.ArrayList;

public class DisplayController {
    /** Graphics Components **/
    @FXML private Pane nodePane;
    @FXML private Pane edgePane;
    @FXML private TableView table;
    private TableColumn agentId = new TableColumn("Agent Id");
    private TableColumn agentCreatedAt = new TableColumn("Created At");
    private boolean started;

    private ArrayList<Circle> nodeCircles = new ArrayList<>();



    /**
     * Display the initial map of nodes and edges. The FXML file contains a StackPane with the nodePane on
     * top of the edgePane.
     * @param nodes A list of node objects containing locations and states
     * @param edges A list of of the locations of edges
     *
     */
    public void displayMap(ArrayList<Node> nodes, ArrayList<MultiPoint> edges) {

        int startX = 75;
        int startY = 75;
        // Display the nodes
        for(Node n : nodes) {
            Circle node = new Circle(n.getXPos()*startX, n.getYPos()*startY, 10);
            nodeCircles.add(node);
            nodePane.getChildren().add(node);

            // For testing purposes
            String nodeId = String.valueOf(n.getNodeId());
            nodePane.getChildren().add(new Text(n.getXPos()*startX, n.getYPos()*startY, nodeId));

            paintNode(n);
        }

        // Display the edges between nodes
        for (MultiPoint p : edges) {
            double x1 = p.getCoordinates()[0];
            double y1 = p.getCoordinates()[1];
            double x2 = p.getCoordinates()[2];
            double y2 = p.getCoordinates()[3];

            Line line = new Line( startX * x1, startY * y1, startX * x2, startY * y2);
            edgePane.getChildren().add(line);
        }
        displayTable();


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

        // If the node has an agent on it paint a ring on the node to indicate
        if(node.hasAgent()) {
            // If the agent is watching a fire paint it light green
            if(node.getNodeState().equals("near-fire")) {
                nodeToChange.setStroke(Color.LIGHTGREEN);
                nodeToChange.setStrokeWidth(5);
            }
            else if(node.getNodeState().equals("fire"))
            {
                nodeToChange.setStroke(Color.BLACK);
                nodeToChange.setStrokeWidth(1);
            }
            // Else paint the node with an agent light blue
            else {
                nodeToChange.setStroke(Color.LIGHTBLUE);
                nodeToChange.setStrokeWidth(5);
            }
        }
        else if(!node.hasAgent()) {
            nodeToChange.setStroke(Color.BLACK);
            nodeToChange.setStrokeWidth(1);
        }

        switch(node.getNodeState()) {
            // Paint standard node color
            case "standard":
                nodeToChange.setFill(Color.DODGERBLUE);
                break;
            // Paint station color
            case "station":
                nodeToChange.setFill(Color.GREEN);
                break;
            // Paint near-fire color
            case "near-fire":
                nodeToChange.setFill(Color.ORANGE);
                break;
            // Paint fire color
            case "fire":
                nodeToChange.setFill(Color.ORANGERED);
                break;
        }
    }

    /**
     * Add cell properties to the table columns and add the columns to the table. The TableView table object is created
     * in the fxml file.
     */
    public void displayTable() {
        table.getColumns().addAll(agentId, agentCreatedAt);
        agentId.setCellValueFactory(new PropertyValueFactory<Message, String>("id"));
        agentCreatedAt.setCellValueFactory(new PropertyValueFactory<Message,String>("location"));
    }

    /**
     * Update the base station's table of agents
     */
    public void updateTable(ObservableList<Message> agentsList) {
        table.setItems(agentsList);
    }


    @FXML
    public void startButtonPressed() {
        started = true;

    }

    public boolean isStarted() {
        return started;
    }


}
