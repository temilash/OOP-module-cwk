import java.time.ZonedDateTime;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import java.text.DecimalFormat;



/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & Oluwakorede Lasekan
 */
public class Point {
  // Constants useful for bounds checking, etc

  private static final double MIN_LONGITUDE = -180.0;
  private static final double MAX_LONGITUDE = 180.0;
  private static final double MIN_LATITUDE = -90.0;
  private static final double MAX_LATITUDE = 90.0;
  private static final double MEAN_EARTH_RADIUS = 6.371009e+6;
  private ZonedDateTime time;
  private double longitude;
  private double latitude;
  private double elevation;
  



  // TODO: Create a stub for the constructor
  public Point(ZonedDateTime t, double lon, double lat, double elev) {

    time = t;
    longitude = lon;
    latitude = lat;
    elevation = elev;

    double lowlon = longitude - MIN_LONGITUDE;
    double highlon = MAX_LONGITUDE - longitude;

    if( lowlon < 0) {

      throw new GPSException("longitude error occured");
      
    }

    if( highlon < 0){

      throw new GPSException("longitude error occured");
      
    }

    double lowlat = latitude - MIN_LATITUDE;
    double highlat = MAX_LATITUDE - latitude;

    if( lowlat < 0) {

      throw new GPSException("latitude error occured");
      
    }

    if( highlat < 0){

      throw new GPSException("latitude error occured");
      
    }



  }
  // TODO: Create a stub for getTime()
  public ZonedDateTime getTime() {
    return time;
  }

  // TODO: Create a stub for getLatitude()
  public double getLatitude() {

    return latitude;
  }

  // TODO: Create a stub for getLongitude()
  public double getLongitude() {

    return longitude;
  }

  // TODO: Create a stub for getElevation()
  public double getElevation() {
    return elevation;
  }

  // TODO: Create a stub for toString()
  public String toString() {

    //changing them to 1dp and 5dp
    DecimalFormat df2 = new DecimalFormat("#.0");
    DecimalFormat df5 = new DecimalFormat("#.00000");
    String elevation2dp = df2.format(elevation);
    String lon5dp = df5.format(longitude);
    String lat5dp = df5.format(latitude);
    //outputing in the format specified
    String output = "("+lon5dp+", "+lat5dp+"), "+elevation2dp+" m" ;
    return output ;


  }

  // IMPORTANT: Do not alter anything beneath this comment!

  /**
   * Computes the great-circle distance or orthodromic distance between
   * two points on a spherical surface, using Vincenty's formula.
   *
   * @param p First point
   * @param q Second point
   * @return Distance between the points, in metres
   */
  public static double greatCircleDistance(Point p, Point q) {
    double phi1 = toRadians(p.getLatitude());
    double phi2 = toRadians(q.getLatitude());

    double lambda1 = toRadians(p.getLongitude());
    double lambda2 = toRadians(q.getLongitude());
    double delta = abs(lambda1 - lambda2);

    double firstTerm = cos(phi2)*sin(delta);
    double secondTerm = cos(phi1)*sin(phi2) - sin(phi1)*cos(phi2)*cos(delta);
    double top = sqrt(firstTerm*firstTerm + secondTerm*secondTerm);

    double bottom = sin(phi1)*sin(phi2) + cos(phi1)*cos(phi2)*cos(delta);

    return MEAN_EARTH_RADIUS * atan2(top, bottom);
  }
}
