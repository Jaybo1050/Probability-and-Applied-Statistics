
public class BinomialDistribution {
	
	private int n = 5;  // trials
    private double p = 0.6;  // probability of success
    private int y = 3;  //number of successes

    // factorial for the combination method
    private int factorial(int num) {
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    private int calculateCombination(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    // exponent method
    private double power(double base, int exponent) {
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
    
    public double calculateBinomialProbability() {
        double q = 1 - p; 
        int combination = calculateCombination(n, y);  
        double successTerm = power(p, y);  
        double failureTerm = power(q, n - y);  

        return combination * successTerm * failureTerm;
    }
    
    public String getResult() {
        return "P(Y = " + y + ") = " + calculateBinomialProbability();
    }

}
