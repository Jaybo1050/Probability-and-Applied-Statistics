
public class Tester {
	
    public static void main(String[] args) {
        int trials = 1000000;
        DoorGame game = new DoorGame(trials);
        
        double stayPercentage = game.monteCarloStay();
        double switchPercentage = game.monteCarloSwitch();
        
        System.out.println("Correct Picking Percentage without Switching: " + stayPercentage + "%");
        System.out.println("Correct Picking Percentage with Switching: " + switchPercentage + "%");
    }
}
