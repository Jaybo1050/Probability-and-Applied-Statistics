
public class MultiplicationIndependent {
	
	private double pA = 0.5;  
    private double pB = 0.4;  

    public double calculateIntersection() {
        return pA * pB;
    }
    
    public String getResult() {
        double intersectionProbability = calculateIntersection();
        return "Independent Multiplication P(A ∩ B) = " + intersectionProbability;
    }

}
