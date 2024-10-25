
public class Combinations {

	private int n = 5;  
    private int r = 3; 
    
    private int factorial(int num) {
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
    
    public int calculateCombination() {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    
}