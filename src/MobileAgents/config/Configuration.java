package MobileAgents.config;

import java.awt.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
import java.util.List;

/**
 *  Configuration reads a text file, parses the information read in from it, and supplys the info
 *  to the Map class which will be used to generate the Mobile Agent Map.
 */
public class Configuration {

    public List config_as_list;
    public List nodes;
    public List edges;
    public List stations;
    public List fires;

    public Configuration(String file) {
        nodes = new ArrayList();
        edges = new ArrayList();
        stations = new ArrayList();
        fires = new ArrayList();

        config_as_list = readFileInList(file);
    }

    public static List<String> readFileInList(String fileName) {

        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }

        catch (IOException e) {
            // do something
            e.printStackTrace();
        }
        return lines;
    }


    public void parseConfigList(List configList) {
      for(int i = 0; i < configList.size(); i++)
      {
          // grab the first element in the configuration list
          String temp = configList.get(i).toString();

          //check to see if its a node
          if(temp.startsWith("node")) {
             //create a 2D point with the x and y positions after "node"
              int x = Character.getNumericValue(temp.charAt(5));
              int y = Character.getNumericValue(temp.charAt(7));

              Point coordinates = new Point(x,y);
              nodes.add(coordinates);

          }

          //check to see if its an edge
          if(temp.startsWith("edge")) {
              //create a 2D point with the x and y positions after "node"
              int x = Character.getNumericValue(temp.charAt(5));
              int y = Character.getNumericValue(temp.charAt(7));

              Point coordinates = new Point(x,y);
              edges.add(coordinates);

          }

          //check to see if its an station
          if(temp.startsWith("stations")) {
              //create a 2D point with the x and y positions after "node"
              int x = Character.getNumericValue(temp.charAt(5));
              int y = Character.getNumericValue(temp.charAt(7));

              Point coordinates = new Point(x,y);
              edges.add(coordinates);
          }

          //check to see if its an fire
          if(temp.startsWith("fire")) {
              //create a 2D point with the x and y positions after "node"
              int x = Character.getNumericValue(temp.charAt(5));
              int y = Character.getNumericValue(temp.charAt(7));

              Point coordinates = new Point(x,y);
              fires.add(coordinates);
          }
      }
    }

}
