import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Oluwakorede
 */
public class TrackInfo {
  public static void main(String[] args) {
    try {
      Track pointtrackinfo = new Track(args[0]);

      DecimalFormat threedecimal = new DecimalFormat("#.000");

      System.out.println(pointtrackinfo.size()+" points in track");

      Point lowpoint = pointtrackinfo.lowestPoint();
      String lowpointString = lowpoint.toString();
      System.out.println("Lowest point is "+lowpointString);

      Point highPoint = pointtrackinfo.highestPoint();
      String highpointString = highPoint.toString();
      System.out.println("Highest point is "+highpointString);
      
      System.out.println("Total distance = "+threedecimal.format(pointtrackinfo.totalDistance())+" km");

      System.out.println("Average speed = "+threedecimal.format(pointtrackinfo.averageSpeed())+" m/s");
      
    } catch (IOException e) {
      System.out.println("No command line file was supplied");
      System.exit(0);
    }
  }
}
