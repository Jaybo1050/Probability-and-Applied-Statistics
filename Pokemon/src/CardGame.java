import java.util.ArrayList;
import java.util.Random;

public class CardGame {
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    public int trials = 1;
    public double success = 0;
    private Opponent opponent;
    private boolean gameOver = false;
    
    //initialized deck
    public CardGame() {
        deck = new ArrayList<>();
        hand = new ArrayList<>();
        opponent = new Opponent();
    }
    //fills both your deck and the opponents deck
    public void fillDecks() {
        fillDeck(deck); 
        fillDeck(opponent.getDeck());
    }

    // Method that fills the deck with Pokemon cards
    private void fillDeck(ArrayList<Card> deck) {
        deck.clear();
        for (int i = 0; i < 25; i++) {
            deck.add(new FireEnergy());
            deck.add(new LightningEnergy());
            deck.add(new WaterEnergy());
            deck.add(new FightingEnergy());
        }
        for (int i = 0; i < 25; i++) {
            deck.add(new Potion());
            deck.add(new RareCandy());
            deck.add(new Switch());
        }
        for (int i = 0; i < 15; i++) {
            deck.add(new Charmander());
            deck.add(new Pikachu());
            deck.add(new Bulbasaur());
            deck.add(new Squirtle());
            deck.add(new Jigglypuff());
        }
    }
    //method that starts the game and handles each of the turns in a loop
    public void startGame() {
        fillDecks();
        drawHands();
        
        //What happens if both me and the opponent have no pokemon
        if (!havePokemonInHand(hand) && !havePokemonInHand(opponent.getHand())) {
            System.out.println("---No Pokemon to Fight With!----");
            return;
        }
        while (true) {
            System.out.println("\n--- Your Turn ---");
            playTurn(hand, opponent.getHand());
            if (checkWinner()) break;

            System.out.println("\n--- Opponent's Turn ---");
            playTurn(opponent.getHand(), hand);
            if (checkWinner()) break;
        }

        System.out.println("Game Over!");
    }
    //plays a turn for whoevers turn is up
    private void playTurn(ArrayList<Card> currentPlayerHand, ArrayList<Card> opponentHand) {
        Pokemon activePokemon = getActivePokemon(currentPlayerHand);
        Pokemon opponentPokemon = getActivePokemon(opponentHand);

        if (activePokemon != null && activePokemon.getHP() > 0 &&
            opponentPokemon != null && opponentPokemon.getHP() > 0) {

            attack(activePokemon, opponentPokemon);

            if (opponentPokemon.getHP() <= 0) {
                opponentPokemon.setHP(0); // Clamp HP to 0
                System.out.println(opponentPokemon.getClass().getSimpleName() + " fainted!");

                if (checkWinner()) {
                    return; // End game if a winner is found
                }
            }

            usePotion(currentPlayerHand);
        }
    }
    //has a pokemon in your hand...
    private boolean havePokemonInHand(ArrayList<Card> hand) {
        for (Card card : hand) {
            if (card instanceof Pokemon && ((Pokemon) card).getHP() > 0) {
                return true;
            }
        }
        return false;
    }
    // this returns the active pokemon from an active hand
    private Pokemon getActivePokemon(ArrayList<Card> hand) {
        for (Card card : hand) {
            if (card instanceof Pokemon) {
                Pokemon pokemon = (Pokemon) card;
                if (pokemon.isActive() && pokemon.getHP() > 0) {
                    return pokemon;
                }
            }
        }
        return null;
    }
    
    // method on the logic of how attacks and damage work. 
    private void attack(Pokemon attacker, Pokemon defender) {
        if (attacker.getHP() <= 0 || defender.getHP() <= 0) {
            return;
        }

        System.out.println(attacker.getClass().getSimpleName() + " attacks " + defender.getClass().getSimpleName() + " for " + attacker.getDmg() + " damage!");
        defender.setHP(defender.getHP() - attacker.getDmg());

        if (defender.getHP() <= 0) {
            defender.setHP(0); 
            System.out.println(defender.getClass().getSimpleName() + " has fainted!");
        } else {
            System.out.println(defender.getClass().getSimpleName() + "'s HP: " + defender.getHP());
        }
    }
    //this method allows potions to be used asa trainer card. potion cards heal 20 HP of a pokemon
    public void usePotion(ArrayList<Card> hand) {
        boolean potionUsed = false;
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            if (card instanceof Potion && !potionUsed) {
                for (Card c : hand) {
                    if (c instanceof Pokemon) {
                        Pokemon pokemon = (Pokemon) c;
                        ((Potion) card).heal(pokemon);
                        System.out.println(pokemon.getClass().getSimpleName() + " healed by 20 HP!");
                        hand.remove(i);
                        potionUsed = true;
                        break;
                    }
                }
            }
        }
    }
    //This method would be used when the switch trainer card is played. The switch card allows a player to redraw 7 cards
    public void useSwitch(ArrayList<Card> hand) {
        boolean switchUsed = false;
        for (Card card : hand) {
            if (card instanceof Switch && !switchUsed) {
                Switch switchCard = (Switch) card;
                if (switchCard.useSwitch(hand)) {
                    System.out.println("Switch executed successfully!");
                    switchUsed = true;
                    break;
                }
            }
        }
    }
    // checks the conditions for a win
    private boolean checkWinner() {
        Pokemon playerPokemon = getActivePokemon(hand);
        Pokemon opponentPokemon = getActivePokemon(opponent.getHand());

        if (playerPokemon == null || playerPokemon.getHP() <= 0) {
            System.out.println("Your active Pokemon has fainted! Opponent wins!");
            return true;
        }
        if (opponentPokemon == null || opponentPokemon.getHP() <= 0) {
            System.out.println("Opponent's active Pokemon has fainted! You win!");
            return true;
        }
        return false;
    }
    //clears hand
    public void clearHand() {
        hand.clear();
    }
    //prints a hand 
    public void printHand() {
        System.out.println("Your Hand:");
        for (Card card : hand) {
            if (card instanceof Pokemon) {
                Pokemon pokemon = (Pokemon) card;
                String status = pokemon.isActive() ? "(active)" : "(benched)";
                System.out.println("- " + pokemon + " " + status);
            } else {
                System.out.println("- " + card.getClass().getSimpleName());
            }
        }
    }
    
    //methos that draws 7 cards from the deck
    private void drawHand(ArrayList<Card> deck, ArrayList<Card> hand, String handTitle) {
        Random rng = new Random();
        hand.clear();

        for (int i = 0; i < 7; i++) {
            int cardToTakeIndex = rng.nextInt(deck.size());
            Card drawnCard = deck.get(cardToTakeIndex);
            hand.add(drawnCard);
            deck.remove(cardToTakeIndex);
        }

        setActiveAndBenchedStatus(hand);
        System.out.println(handTitle);

        for (Card card : hand) {
            if (card instanceof Pokemon) {
                Pokemon pokemon = (Pokemon) card;
                String status = pokemon.isActive() ? "(active)" : "(benched)";
                System.out.println("- " + pokemon.getClass().getSimpleName() + " " + status);
            } else {
                System.out.println("- " + card.getClass().getSimpleName());
            }
        }
    }
    
    //draws the hand of both players
    public void drawHands() {
        drawHand(deck, hand, "Your Hand:");
        drawHand(opponent.getDeck(), opponent.getHand(), "\nOpponent's Hand:");
    }
    //benched and active status of the pokemon in a hand
    private void setActiveAndBenchedStatus(ArrayList<Card> hand) {
        boolean activeSet = false;

        for (Card card : hand) {
            if (card instanceof Pokemon) {
                Pokemon pokemon = (Pokemon) card;
                if (!activeSet) {
                    pokemon.setActive(true); // Set the first pokemon as active
                    activeSet = true;
                } else {
                    pokemon.setActive(false); // Set any additional pokemon as benched
                }
            }
        }
    }

    /*
     * Monte Carlo method
    public double monteCarlo() {
        success = 0;
        for (int i = 0; i < trials; i++) {
            fillDecks();
            drawHands();
            if (havePokemonInHand(hand)) {
                success++;
            }
        }
        return success;
    }
    */
    
}
