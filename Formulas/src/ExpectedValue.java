
public class ExpectedValue {
	
	private int[] values =            {1,   2,   3,   4};             
    private double[] probabilities = {0.1, 0.3, 0.4, 0.2};  

   
    public double calculateExpectedValue() {
        double expectedValue = 0;
        
        for (int i = 0; i < values.length; i++) {
            expectedValue += values[i] * probabilities[i];
        }
        return expectedValue;
    }

    public String getResult() {
        return "E(Y) = " + calculateExpectedValue();
    }

}
