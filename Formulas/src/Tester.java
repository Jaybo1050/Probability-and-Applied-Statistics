
public class Tester {

	public static void main(String[] args) {
		
		Mean calculator = new Mean();  
        System.out.println("Mean: " + calculator.calculateMean());
		
        Variance calculator1 = new Variance();  
        System.out.println("Sample Variance: " + calculator1.calculateVariance());
        
        StandardDeviation calculator2 = new StandardDeviation();  
        System.out.println("Standard Deviation: " + calculator2.calculateStandardDeviation());
        
        Probability calculator3 = new Probability();  
        System.out.println("Union Probability: " + calculator3.calculateProbability()); 
        
        Permutations calculator4 = new Permutations(); 
        System.out.println("Permutation P(n, r) = " + calculator4.calculatePermutation());
        
        Combinations calculator5 = new Combinations();  
        System.out.println("Combination C(n, r) = " + calculator5.calculateCombination());
        
        ConditionalProbability calculator6 = new ConditionalProbability(); 
        System.out.println(calculator6.getResult()); 
        
        MultiplicationIndependent calculator7 = new MultiplicationIndependent(); 
        System.out.println(calculator7.getResult());  
        
        TotalProbability calculator8 = new TotalProbability();  
        System.out.println(calculator8.getResult());  
        
        ExpectedValue calculator9 = new ExpectedValue();  
        System.out.println(calculator9.getResult());
        
        BinomialDistribution calculator10 = new BinomialDistribution();  
        System.out.println(calculator10.getResult()); 
        
	}
}
        
  
        
   
        




