/*
 * Arrays of objects
 */

import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Welcome03_List {
  public static void main(String[] args) {
    // load all rows
    DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml").load();
    ArrayList<WeatherStation> allstns = ds.fetchList("WeatherStation", "station/station_name",
        "station/station_id", "station/state",
        "station/latitude", "station/longitude");
    System.out.println("Total stations: " + allstns.size());

    // finding location w/ least latitude
    // algorithm to find least lat
    WeatherStation leastLat = allstns.get(0);
    for (WeatherStation ws : allstns) {
      if (ws.getLat() < leastLat.getLat()) {
        leastLat = ws;
      }
    }
    System.out.println("Least Latitude: " + leastLat.getName() + " at " + leastLat.getLat());


    // find all stations in a state
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a state abbreviation: ");
    String state = sc.next();
    System.out.println("Stations in " + state);
    int count = 0;
    for (WeatherStation ws : allstns) {
      if (ws.isLocatedInState(state)) {
        System.out.println("  " + ws.getId() + ": " + ws.getName());
        count++;
        
      }
    }
    System.out.println("There are " + count + " station objects in the state.");
  }
}