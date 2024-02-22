import core.data.*;

public class Welcome01 {
   public static void main(String[] args) {
     // set id (specifies (row))
      String id = "KBUR";
     // data pulled from website
      DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + id + ".xml"); 
      ds.setCacheTimeout(15 * 60);  
      ds.load(); // it loads!
      //ds.printUsageString();
     // temperature and location are fetched
      float temp = ds.fetchFloat("temp_f");
      String loc = ds.fetchString("location");
     // temperature of the location with the id
      System.out.println("The temperature at " + loc + " is " + temp + "F");
   }
}
