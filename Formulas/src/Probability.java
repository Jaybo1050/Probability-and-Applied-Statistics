
public class Probability {
	
	private double[] probabilities = {0.1, 0.2, 0.05, 0.15, 1};

    public double calculateProbability() {
        double sum = 0;
        for (double p : probabilities) {
            sum += p;
        }
        return sum;
    }

}
