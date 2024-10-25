
public class ConditionalProbability {
	
	private double pAIntersectionB = 0.2;  // P(A ∩ B) 
    private double pB = 0.5;              

    public double calculateConditionalProbability() {
        if (pB == 0) {
            return 0;
        } else {
            return pAIntersectionB / pB;
        }
    }
    
    public String getResult() {
        double conditionalProbability = calculateConditionalProbability();
        return "P(A | B) = " + conditionalProbability;
    }
}
