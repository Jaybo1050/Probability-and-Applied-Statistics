import java.util.ArrayList;

public class Opponent {
	
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    public Opponent() {
        deck = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear();
    }

    public void printHand() {
        System.out.println("Opponent's Hand:");
        for (Card card : hand) {
            System.out.println("- " + card.getClass().getSimpleName());
        }
    }
}