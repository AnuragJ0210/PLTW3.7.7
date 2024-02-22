// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

public class PokemonAnalysis {
// main method for running analysis
  public static void main(String[] args) {
    // calls the getPokeList method
    ArrayList<Pokemon> pokemonList = PokemonList.getPokeList();


    // calculating means
    calculateMeans(pokemonList, true);
    System.out.println();
    calculateMeans(pokemonList, false);
    System.out.println();

    
    calculateTop3ByLegendary(pokemonList, true);
    System.out.println();
    calculateTop3ByLegendary(pokemonList, false);
    System.out.println();
    calculateTop3ByType(pokemonList, "Electric");
    System.out.println();
    calculateTop3ByType(pokemonList, "Water");
  }

  // method for calculating means
  // takes two parameters: one is the list, and other is whether we are calculating for legendary = true or false
  public static void calculateMeans(ArrayList<Pokemon> pokemonList, boolean isLegendary) {
    // running totals
        int hp = 0, attack = 0, defense = 0, spAtk = 0, spDef = 0, speed = 0;
    // number of pokemon
    int count = 0;

    // iterates through every pokemon in list
    for (Pokemon pokemon : pokemonList) {
      // legendary condition is checked
      if (pokemon.isLegendary() == isLegendary) {
        // the totals are added
        hp += pokemon.getHp();
        attack += pokemon.getAttack();
        defense += pokemon.getDefense();
        spAtk += pokemon.getSpAtk();
        spDef += pokemon.getSpDef();
        speed += pokemon.getSpeed();
        // for every pokemon counted, count is incremented
        count++;
      }
    }

    // if (count == 0) {
    //   System.out.println("No Pokemon found with Legendary = " + isLegendary);
    //   return;
    // }

    // calculates the means
    double meanHP = (double) hp / count;
    double meanAttack = (double) attack / count;
    double meanDefense = (double) defense / count;
    double meanSpAtk = (double) spAtk / count;
    double meanSpDef = (double) spDef / count;
    double meanSpeed = (double) speed / count;

    // results are printed
    System.out.println("Mean values for Legendary = " + isLegendary);
    System.out.println("Mean HP: " + meanHP);
    System.out.println("Mean Attack: " + meanAttack);
    System.out.println("Mean Defense: " + meanDefense);
    System.out.println("Mean Sp. Atk: " + meanSpAtk);
    System.out.println("Mean Sp. Def: " + meanSpDef);
    System.out.println("Mean Speed: " + meanSpeed);
  }

  // calculates the top 3 pokemon by legendary
  public static void calculateTop3ByLegendary(ArrayList<Pokemon> pokemonList, boolean isLegendary) {
    // initialize lists for each metric
    ArrayList<Integer> HP = new ArrayList<Integer>();
    ArrayList<Integer> Attack = new ArrayList<Integer>();
    ArrayList<Integer> Defense = new ArrayList<Integer>();
    ArrayList<Integer> SpAtk = new ArrayList<Integer>();
    ArrayList<Integer> SpDef = new ArrayList<Integer>();
    ArrayList<Integer> Speed = new ArrayList<Integer>();
    ArrayList<Integer> Score = new ArrayList<Integer>();

    // inititalized strings for the strongets pokemon names
    String firstStrongest = "";
    String secondStrongest = "";
    String thirdStrongest = "";

    // iterate through every pokemon in list
    for (Pokemon pokemon : pokemonList) {

      // check legendary condition
      if (pokemon.isLegendary() == isLegendary) {
        // add metrics to each corresponding list
        // and each list is sorted ascending
        HP.add(pokemon.getHp());
        Collections.sort(HP);
        Attack.add(pokemon.getAttack());
        Collections.sort(Attack);
        Defense.add(pokemon.getDefense());
        Collections.sort(Defense);
        SpAtk.add(pokemon.getSpAtk());
        Collections.sort(SpAtk);
        SpDef.add(pokemon.getSpDef());
        Collections.sort(SpDef);
        Speed.add(pokemon.getSpeed());
        Collections.sort(Speed);
      }
    }
    // iterate through pokemon again
    for (Pokemon pokemon : pokemonList) {
      // check condition again
      if (pokemon.isLegendary() == isLegendary) {
        pokemon.score = 0;
        // adding the indexes of each metric in sorted list to score
        // this normalizes the scale to 0 to 800, which makes it a better comparison than total
        pokemon.score += HP.indexOf(pokemon.getHp());
        pokemon.score += Attack.indexOf(pokemon.getAttack());
        pokemon.score += Defense.indexOf(pokemon.getDefense());
        pokemon.score += SpAtk.indexOf(pokemon.getSpAtk());
        pokemon.score += SpDef.indexOf(pokemon.getSpDef());
        pokemon.score += Speed.indexOf(pokemon.getSpeed());
        // for every pokemon, we add the score to the Score arraylist
        Score.add(pokemon.score);
      }
    }
    // find the top 3 pokemon

    // sort the Score arraylist
    Collections.sort(Score);
    for (Pokemon pokemon : pokemonList) {
      // through conditionals we check if a pokemon's scores are in the highest three indices of the sorted score list
      if (pokemon.isLegendary() == isLegendary) {
        if (pokemon.score == Score.get(Score.size() - 1)) {
          firstStrongest = pokemon.getName() + " is strongest for Legendary = " + isLegendary;
        }
        if (pokemon.score == Score.get(Score.size() - 2)) {

          secondStrongest = pokemon.getName() + " is the second strongest for Legendary = " + isLegendary;
        }
        if (pokemon.score == Score.get(Score.size() - 3)) {
          thirdStrongest = pokemon.getName() + " is the third strongest for Legendary = " + isLegendary;
        }

      }
    }

    // print results
    System.out.println(firstStrongest);
    System.out.println(secondStrongest);
    System.out.println(thirdStrongest);

  }

  public static void calculateTop3ByType(ArrayList<Pokemon> pokemonList, String type) {
    ArrayList<Integer> HP = new ArrayList<Integer>();
    ArrayList<Integer> Attack = new ArrayList<Integer>();
    ArrayList<Integer> Defense = new ArrayList<Integer>();
    ArrayList<Integer> SpAtk = new ArrayList<Integer>();
    ArrayList<Integer> SpDef = new ArrayList<Integer>();
    ArrayList<Integer> Speed = new ArrayList<Integer>();
    ArrayList<Integer> Score = new ArrayList<Integer>();

    String firstStrongest = "";
    String secondStrongest = "";
    String thirdStrongest = "";
    
    for (Pokemon pokemon : pokemonList) {
      // instead of legendary, we use an or condition for Type1 and Type2
      if (pokemon.getType1().equals(type) || pokemon.getType2().equals(type)) {
        HP.add(pokemon.getHp());
        Collections.sort(HP);
        Attack.add(pokemon.getAttack());
        Collections.sort(Attack);
        Defense.add(pokemon.getDefense());
        Collections.sort(Defense);
        SpAtk.add(pokemon.getSpAtk());
        Collections.sort(SpAtk);
        SpDef.add(pokemon.getSpDef());
        Collections.sort(SpDef);
        Speed.add(pokemon.getSpeed());
        Collections.sort(Speed);
      }
    }
    for (Pokemon pokemon : pokemonList) {
      if (pokemon.getType1().equals(type) || pokemon.getType2().equals(type)) {
        pokemon.score = 0;
        pokemon.score += HP.indexOf(pokemon.getHp());
        pokemon.score += Attack.indexOf(pokemon.getAttack());
        pokemon.score += Defense.indexOf(pokemon.getDefense());
        pokemon.score += SpAtk.indexOf(pokemon.getSpAtk());
        pokemon.score += SpDef.indexOf(pokemon.getSpDef());
        pokemon.score += Speed.indexOf(pokemon.getSpeed());
        Score.add(pokemon.score);
      }
    }
    ArrayList<Integer> Score_unsorted = Score;
    Collections.sort(Score);
    for (Pokemon pokemon : pokemonList) {
      if (pokemon.getType1().equals(type) || pokemon.getType2().equals(type)) {
        if (pokemon.score == Score.get(Score.size() - 1)) {
          firstStrongest = pokemon.getName() + " is the strongest for " + "type = " + type;}

          if (pokemon.score == Score.get(Score.size() - 2)) {
            secondStrongest = pokemon.getName() + " is the second strongest for " + "type = " + type;
          }
          if (pokemon.score == Score.get(Score.size() - 3)) {
            thirdStrongest = pokemon.getName() + " is the third strongest for " + "type = " + type;
          }

        
      }

    }
    System.out.println(firstStrongest);
    System.out.println(secondStrongest);
    System.out.println(thirdStrongest);

  }
}

// @Test
// void addition() {
// assertEquals(2, 1 + 1);
// }
