import java.util.LinkedList;
import java.util.Scanner;
import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.nio.file.*;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Oluwakorede Lasekan
 */
public class Track {

  private LinkedList<Point> point3 = new LinkedList<>();

  public Track(){
    
  }


  // TODO: Create a stub for the constructor
  public Track(String filename) throws IOException {
    
    point3.clear();
    readFile(filename);

  }

  // TODO: Create a stub for readFile()
  public void readFile(String filename) throws IOException {
    
    point3.clear();
    //new scanner to read from file
    Scanner input = new Scanner(Paths.get(filename));
    String line = input.nextLine();

    //checking to make sure not at the end of the file
    while (input.hasNextLine()) {

      line = input.nextLine();
      //split the values where there is a comma and add in array
      String[] temp = line.split(",");
      int templen = temp.length;

      if (templen != 4){

        throw new GPSException("error occured with data in csv");
      }

      ZonedDateTime timezone = ZonedDateTime.parse(temp[0]);
      Double lonDouble = Double.parseDouble(temp[1]);
      Double latDouble = Double.parseDouble(temp[2]);
      Double eleDouble = Double.parseDouble(temp[3]);
      //creating new point object with values from csv
      Point pointline = new Point(timezone, lonDouble, latDouble, eleDouble);
      point3.add(pointline);

    }

    input.close();

  }

  

  // TODO: Create a stub for add()
  public void add(Point point) {
    point3.add(point);
  }

  // TODO: Create a stub for get()
  public Point get(int index) {
    if(index >= point3.size() || index < 0){
        
      throw new GPSException("error occured index given bigger than the size of the sequence");
      
    }
    return point3.get(index);
  }

  // TODO: Create a stub for size()
  public int size() {
    return point3.size();
  }

  // TODO: Create a stub for lowestPoint()
  public Point lowestPoint() {

    if(point3.isEmpty()){
        
      throw new GPSException("error occured track empty");
      
    }

    int counter = 0;
    Point pointlowest = point3.get(0);
    Double lowestpoint = pointlowest.getElevation();

    for(int i = 0; i < point3.size(); i++){

      Point pointlow = point3.get(i);
      Double lowcompare = pointlow.getElevation();

      if(lowcompare < lowestpoint)
      {
        counter = i;
        lowestpoint = lowcompare;
      }

      

    }

    Point lowest = point3.get(counter);

    return lowest;
  }

  // TODO: Create a stub for highestPoint()
  public Point highestPoint() {

    if(point3.isEmpty()){
        
      throw new GPSException("error occured track empty");
      
    }

    int counter2 = 0;
    Point compare = point3.get(0);
    Double elecompare = compare.getElevation();

    for(int i = 1; i< point3.size(); i++)
    {
      Point compare2 = point3.get(i);
      Double elecompare2 = compare2.getElevation();
      if(elecompare < elecompare2)
      {
        counter2 = i;
        elecompare = elecompare2;
      }
    }

    Point highest = point3.get(counter2);

    return highest;
  }

  // TODO: Create a stub for totalDistance()
  public Double totalDistance() {

    if(point3.size() < 2){
        
      throw new GPSException("error occured track empty");
      
    }

    Double distance = 0.0;

    for(int i = 1; i < point3.size(); i++){

      Point pointabove = point3.get(i);
      Point pointbelow = point3.get(i-1);

      Double distancetoadd = Point.greatCircleDistance(pointabove, pointbelow);

      distance += distancetoadd;

    }

    return distance;      
  }

  // TODO: Create a stub for averageSpeed()
  public Double averageSpeed() {

    int numpoints = point3.size();


    double distance = totalDistance();

    Point firstpoint = point3.get(0);
    Point lastpoint = point3.getLast();

    ZonedDateTime zdt1 =  firstpoint.getTime();
    ZonedDateTime zdt2 = lastpoint.getTime();

    Duration duration = Duration.between(zdt1,zdt2) ;

    Long time = duration.toSeconds();

    Double speed = distance/time;

    if(numpoints < 2){
      throw new GPSException("error occured number of tracks");
    }

    return speed;

  }

  
}