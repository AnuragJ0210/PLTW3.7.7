/*
 Represents information about a NWS weather station
*/

// WeatherStation class
// each object is row
public class WeatherStation {
  // each instance variable is column
   private String name;
   private String id;
   private String state;
   private double lat;
   private double lng;

  // constructor
   WeatherStation(String name, String id, String state, double lat, double lng) {
      this.name = name;
      this.id = id;
      this.lat = lat;
      this.lng = lng;
      this.state = state;   
   }

  // getter methods
   /* Produce the id of this station */
   public String getId() { 
      return id;
   }
   
   /* Produce the name of this station */
   public String getName() { 
      return name;
   }
   
   /* Determine if this weather station is located in the given state */
   public boolean isLocatedInState(String st) {
      return this.state.equals(st);
   }

  public double getLat(){
    return this.lat;
  }
   
}