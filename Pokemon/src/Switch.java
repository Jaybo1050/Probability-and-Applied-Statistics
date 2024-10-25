import java.util.ArrayList;
import java.util.Random;

public class Switch extends Trainer {

    public Switch() {
        super("Switch");
    }

    // the switch card is a trainer card that swaps the active pokemon with another in the hand
    public boolean useSwitch(ArrayList<Card> hand) {
        Pokemon activePokemon = null;
        ArrayList<Pokemon> pokemonList = new ArrayList<>();

        // finds active pokemon
        for (Card card : hand) {
            if (card instanceof Pokemon) {
                Pokemon pokemon = (Pokemon) card;
                pokemonList.add(pokemon);
                if (pokemon.isActive()) {
                    activePokemon = pokemon;  // Set the active Pokémon
                }
            }
        }
        // if theres less then 2 pokemon, then you cant swap
        if (activePokemon == null || pokemonList.size() < 2) return false;

        // selects a random pokemon to switch with
        Random rng = new Random();
        Pokemon switchWith;
        do {
            switchWith = pokemonList.get(rng.nextInt(pokemonList.size()));
        } while (switchWith == activePokemon);
        // Perform the switch
        activePokemon.setActive(false);
        switchWith.setActive(true);
        System.out.println("Switched " + activePokemon.getClass().getSimpleName() + " with " + switchWith.getClass().getSimpleName() + "!");
        return true;
    }
}