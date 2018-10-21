package MobileAgents.config;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Configuration reads a text file, parses the information read in from it, and supplies the info
 * to the Map class which will be used to generate the Mobile Agent Map.
 */
public class Configuration {

    private ArrayList<Point> nodes = new ArrayList<>();
    private ArrayList<Point> edgeStarts = new ArrayList<>();
    private ArrayList<Point> edgeEnds = new ArrayList<>();
    private ArrayList<Point> station = new ArrayList<>();
    private ArrayList<Point> fireStart = new ArrayList<>();

    public Configuration(String filePath) {
        parseConfigList(readFileInList(filePath));
    }

    /**
     * Read in the text file and convert it to a List object. Helper function for parseConfigList
     * @param filePath The location of the file
     * @return A list object containing the text file's fields
     */
    private List<String> readFileInList(String filePath) {

        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            // do something
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Parse in the given text file and store the data points in separate lists
     * @param configList The given text file in list format
     */
    private void parseConfigList(List configList) {
        for (int i = 0; i < configList.size(); i++) {

            int x, y;
            // grab the next element in the configuration list
            String currElement = configList.get(i).toString();

            if (currElement.toLowerCase().startsWith("node")) {
                //create a 2D point with the x and y positions after "node"
                x = Character.getNumericValue(currElement.charAt(5));
                y = Character.getNumericValue(currElement.charAt(7));
                nodes.add(new Point(x, y));
            }
            //check for an edge
            if (currElement.toLowerCase().startsWith("edge")) {
                // Add the starting edgeStarts location
                x = Character.getNumericValue(currElement.charAt(5));
                y = Character.getNumericValue(currElement.charAt(7));
                edgeStarts.add(new Point(x, y));
                // Add the ending edge location
                x = Character.getNumericValue(currElement.charAt(9));
                y = Character.getNumericValue(currElement.charAt(11));
                edgeEnds.add(new Point(x, y));
            }
            //check to see if its an station
            if (currElement.toLowerCase().startsWith("station")) {
                //create a 2D point with the x and y positions after "node"
                x = Character.getNumericValue(currElement.charAt(8));
                y = Character.getNumericValue(currElement.charAt(10));
                station.add(new Point(x, y));
            }
            //check to see if its an fireStart
            if (currElement.toLowerCase().startsWith("fire")) {
                //create a 2D point with the x and y positions after "node"
                x = Character.getNumericValue(currElement.charAt(5));
                y = Character.getNumericValue(currElement.charAt(7));
                fireStart.add(new Point(x, y));
            }
        }
    }

    public ArrayList<Point> getNodes() {
        return nodes;
    }

    public ArrayList<Point> getEdgeStarts() {
        return edgeStarts;
    }

    public ArrayList<Point> getEdgeEnds() { return edgeEnds; }

    public ArrayList<Point> getStation() {
        return station;
    }

    public ArrayList<Point> getFireStart() {
        return fireStart;
    }
}
