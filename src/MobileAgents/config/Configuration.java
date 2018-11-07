package MobileAgents.config;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Path;
/**
 * Configuration reads a text file, parses the information read in from it, and supplies the info
 * to the Map class which will be used to generate the Mobile Agent Map.
 */
public class Configuration {

    private ArrayList<Point> nodes = new ArrayList<>();
    private ArrayList<MultiPoint> edges = new ArrayList<>();
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
        Path pathToFile = Paths.get(filePath);

        try {
            lines = Files.readAllLines(pathToFile, StandardCharsets.UTF_8);
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

            int x1, y1, x2, y2;

            // grab the next element in the configuration list
            String currElement = configList.get(i).toString();

            if (currElement.toLowerCase().startsWith("node")) {
                //create a 2D point with the x and y positions after "node"
                x1 = Character.getNumericValue(currElement.charAt(5));
                y1 = Character.getNumericValue(currElement.charAt(7));
                nodes.add(new Point(x1, y1));
            }
            //check for an edge
            if (currElement.toLowerCase().startsWith("edge")) {
                // Add the starting edgeStarts location
                x1 = Character.getNumericValue(currElement.charAt(5));
                y1 = Character.getNumericValue(currElement.charAt(7));

                // Add the ending edge location
                x2 = Character.getNumericValue(currElement.charAt(9));
                y2 = Character.getNumericValue(currElement.charAt(11));

                edges.add(new MultiPoint(x1,y1,x2,y2));
            }
            //check to see if its an station
            if (currElement.toLowerCase().startsWith("station")) {
                //create a 2D point with the x and y positions after "node"
                x1 = Character.getNumericValue(currElement.charAt(8));
                y1 = Character.getNumericValue(currElement.charAt(10));
                station.add(new Point(x1, y1));
            }
            //check to see if its an fireStart
            if (currElement.toLowerCase().startsWith("fire")) {
                //create a 2D point with the x and y positions after "node"
                x1 = Character.getNumericValue(currElement.charAt(5));
                y1 = Character.getNumericValue(currElement.charAt(7));
                fireStart.add(new Point(x1, y1));
            }
        }
    }

    public ArrayList<Point> getNodes() {
        return nodes;
    }

    public ArrayList<MultiPoint> getEdges() {
        return edges;
    }

    public ArrayList<Point> getStation() {
        return station;
    }

    public ArrayList<Point> getFireStart() {
        return fireStart;
    }
}
