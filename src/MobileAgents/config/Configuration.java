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

    private List nodes = new ArrayList();
    private List edges = new ArrayList();
    private List station = new ArrayList();
    private List fire = new ArrayList();

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

            // grab the first element in the configuration list
            String currElement = configList.get(i).toString();

            //check to see if its a node
            if (currElement.toLowerCase().startsWith("node")) {
                //create a 2D point with the x and y positions after "node"
                int x = Character.getNumericValue(currElement.charAt(5));
                int y = Character.getNumericValue(currElement.charAt(7));
                nodes.add(new Point(x, y));
            }
            //check to see if its an edge
            if (currElement.toLowerCase().startsWith("edge")) {
                //create a 2D point with the x and y positions after "node"
                int x = Character.getNumericValue(currElement.charAt(5));
                int y = Character.getNumericValue(currElement.charAt(7));
                edges.add(new Point(x, y));
            }
            //check to see if its an station
            if (currElement.toLowerCase().startsWith("station")) {
                //create a 2D point with the x and y positions after "node"
                int x = Character.getNumericValue(currElement.charAt(5));
                int y = Character.getNumericValue(currElement.charAt(7));
                station.add(new Point(x, y));
            }
            //check to see if its an fire
            if (currElement.toLowerCase().startsWith("fire")) {
                //create a 2D point with the x and y positions after "node"
                int x = Character.getNumericValue(currElement.charAt(5));
                int y = Character.getNumericValue(currElement.charAt(7));
                fire.add(new Point(x, y));
            }
        }
    }
}
