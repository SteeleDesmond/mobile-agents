package MobileAgents.config;

/**
 *  This class acts as a helper class to Configuration, when handling multiple dimensional points for
 *  edges since they are in the form of:
 *  edge 1 2 3 4
 */
public class MultiPoint
{
    private double[] coords;

    //construct an an array of n points
    public MultiPoint(double... coordinates){
        this.coords = coordinates;
    }

    /**
     * Prints array containing the multipoints
     */
    public void printCoords() {
        for(int i=0; i<coords.length; i++){
            System.out.println("Coordinate # "+i+" : "+ coords[i]);
        }
    }

    public double[] getCoordinates() {
        return coords;
    }

    public double getX() {
        return  coords[0];
    }

    public double getY() {
        return  coords[1];
    }

    @Override
    public  String toString() {
        return coords.toString();
    }
}
