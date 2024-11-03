import java.util.ArrayList;

public class Bill extends Trainer{
	
	public Bill() {
        super("Bill");
    }


	public void useBill(ArrayList<Card> deck, ArrayList<Card> hand) {
        if (deck.size() < 2) {
            System.out.println("Not enough cards in the deck to draw 2 cards.");
            return;
        }

        System.out.println("Using Bill! Drawing 2 cards...");
        for (int i = 0; i < 2; i++) {
            hand.add(deck.remove(0)); 
        }
    }
    @Override
    public String toString() {
        return "Bill (Draw 2 Cards)";
    }
}
