package MobileAgents.config;

/**
 *  This class acts as an helper class to Configuration, when handling multiple dimensional points for
 *  edges since they are in the form of:
 *  edge 1 2 3 4
 */
public class MultiPoint
{

    private double[] coords;


    //construct an anrray of n points
    public MultiPoint(double... coords){
        this.coords = coords;
    }

    public void printCoords()
    {
        for(int i=0; i<coords.length; i++){
            System.out.println("Coordinate #"+i+": "+coords[i]);
        }
        System.out.println("");
    }

    public double[] getCoords()
    {
        return coords;
    }

    @Override
    public  String toString() {
        return coords.toString();
    }
}
