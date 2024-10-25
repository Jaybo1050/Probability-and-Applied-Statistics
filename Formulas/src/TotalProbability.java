
public class TotalProbability {
	
	private double[] pAIB = {0.9, 0.5, 0.2};  // P(A | Bi) 
    private double[] pBi = {0.3, 0.4, 0.3};         

    public double calculateTotalProbability() {
        double totalProbability = 0;
        
        for (int i = 0; i < pAIB.length; i++) {
            totalProbability += pAIB[i] * pBi[i];
        }
        return totalProbability;
    }
    public String getResult() {
        return "Total Probability P(A) = " + calculateTotalProbability();
    }

}
