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
    //fills deck 
    private void fillDeck(ArrayList<Card> deck) {
        deck.clear();
        for (int i = 0; i < 29; i++) { // +29 cards
            deck.add(new FireEnergy());
        }
        for (int i = 0; i < 5; i++) { // + 15 cards
            deck.add(new Potion());
            deck.add(new RareCandy());
            deck.add(new Switch());
        }
        for (int i = 0; i < 1; i++) { // +1 card
            deck.add(new Bill());
        }
        for (int i = 0; i < 7; i++) {  // +7 cards
            deck.add(new Charmander());
        }
        for (int i = 0; i < 2; i++) {  // +8 cards
            deck.add(new Rapidash());
            deck.add(new Crocalor());
            deck.add(new Heatmor());
            deck.add(new Pyroar());
        } 
        //         8 + 7 + 1 + 15 + 29 = 60 total cards for your deck
        
    }
    //method that starts the game and handles each of the turns in a loop
    public void startGame() {
        fillDecks();
        drawHands();
        if (!havePokemonInHand(hand) && !havePokemonInHand(opponent.getHand())) {
            System.out.println("---No Pokemon to Fight With!----");
            return;
        }
    
        while (!gameOver) {
            System.out.println("\n--- Your Turn ---");
            playTurn(hand, opponent.getHand(), true);  // true for player's turn
            if (gameOver || checkWinner()) break;

            System.out.println("\n--- Opponent's Turn ---");
            playTurn(opponent.getHand(), hand, false);  // false for opponent's turn
            if (gameOver || checkWinner()) break;
        }
        System.out.println("Game Over!");
    }    
    private boolean hasRequiredEnergy(ArrayList<Card> hand, Pokemon activePokemon) {
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            if (card instanceof Energy) {
                Energy energy = (Energy) card;
                if (energy.getType().equalsIgnoreCase(activePokemon.getType())) {
                    hand.remove(i); // Remove the energy card after confirming
                    return true;
                }
            }
        }
        return false;
    }   
    //plays a turn for whoevers turn is up
    private void playTurn(ArrayList<Card> currentPlayerHand, ArrayList<Card> opponentHand, boolean isPlayerTurn) {
        Pokemon activePokemon = getActivePokemon(currentPlayerHand);
        Pokemon opponentPokemon = getActivePokemon(opponentHand);

        if (activePokemon != null && activePokemon.getHP() > 0 && opponentPokemon != null && opponentPokemon.getHP() > 0) {
   
            if (!hasRequiredEnergy(currentPlayerHand, activePokemon)) {
                System.out.println(activePokemon.getClass().getSimpleName() + " cannot attack due to lack of " + activePokemon.getType() + " energy!");
                System.out.println(isPlayerTurn ? "You forfeit! Opponent wins!" : "Opponent forfeits! You win!");
                gameOver = true;
                return;
            }

            attack(activePokemon, opponentPokemon);

            if (opponentPokemon.getHP() <= 0) {
                System.out.println(opponentPokemon.getClass().getSimpleName() + " has fainted!");
                
                if (!setNextActivePokemon(opponentHand)) {
                    System.out.println(isPlayerTurn ? "Opponent has no more Pokémon! You win!" : "You have no more Pokémon! Opponent wins!");
                    gameOver = true;
                    return;
                }
            }
            // use a potion if available
            usePotion(currentPlayerHand);
            useTrainerCard(currentPlayerHand, deck);
        }
    }
    private boolean setNextActivePokemon(ArrayList<Card> hand) {
        for (Card card : hand) {
            if (card instanceof Pokemon) {
                Pokemon pokemon = (Pokemon) card;
                if (!pokemon.isActive() && pokemon.getHP() > 0) {
                    pokemon.setActive(true);
                    System.out.println(pokemon.getClass().getSimpleName() + " is now active!");
                    return true;
                }
            }
        }
        return false;
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
    // this method returns the active pokemon from an active hand
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
        
        if (defender.getHP() > 0) {
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
    private void useTrainerCard(ArrayList<Card> hand, ArrayList<Card> deck) {
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            if (card instanceof Bill) {
                ((Bill) card).useBill(deck, hand);
                hand.remove(i); 
                System.out.println("\nNew Hand After Using Bill:");
                for (Card c : hand) {
                    System.out.println("- " + c.toString().replaceAll("@[a-zA-Z0-9]+", ""));
                }
                break;
            }
        }
    }
    // checks the conditions for a win
    private boolean checkWinner() {
        if (getActivePokemon(hand) == null) {
            System.out.println("You have no active Pokémon! Opponent wins!");
            return true;
        }
        if (getActivePokemon(opponent.getHand()) == null) {
            System.out.println("Opponent has no active Pokémon! You win!");
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
            System.out.println("- " + card); 
        }
    }
    
    //methos that draws 7 cards from the deck
    private void drawHand(ArrayList<Card> deck, ArrayList<Card> hand, String handTitle) {
        Random rng = new Random();
        hand.clear();

        for (int i = 0; i < 7; i++) {
            int cardToTakeIndex = rng.nextInt(deck.size());
            Card drawnCard = deck.remove(cardToTakeIndex);
            hand.add(drawnCard);
        }

        setActiveAndBenchedStatus(hand);
        System.out.println(handTitle);

        for (Card card : hand) {
            if (card instanceof Pokemon) {
                System.out.println("- " + card.toString());
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
                    pokemon.setActive(true); 
                    activeSet = true;
                } else {
                    pokemon.setActive(false); 
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
