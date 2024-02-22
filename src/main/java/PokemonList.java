import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

// Class for accessing the csv file
public class PokemonList {
  // initialize 1d arraylist
  private ArrayList<Pokemon> allPokemon;

  // method to load csv into arraylist
  public static ArrayList<Pokemon> getPokeList() {
    // loads from the csv into datasource
    DataSource ds = DataSource.connect("Pokemon.csv").load();

    // loads into Arraylist
    ArrayList<Pokemon> allPokemon = ds.fetchList("Pokemon", "#", "Name", "Type 1", "Type 2", "Total", "HP", "Attack",
        "Defense", "Sp. Atk", "Sp. Def", "Speed", "Generation", "Legendary");

    // returns arraylist
    return allPokemon;
  }
}