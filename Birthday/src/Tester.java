import java.util.Random;

public class Tester {
	public static void main(String[] args) {
		
		int people = 50;  
		
        int runs = 1000;  

        int sharedBirthdayCount = 0;

     
        
        
        
        for (int i = 0; i < runs; i++) {
            if (People.hasSharedBirthday(people)) {
                sharedBirthdayCount++;
            }
        }
 
        double probability = (double) sharedBirthdayCount / runs;
        
        System.out.printf("Probability of at least two people sharing a birthday: %.2f%%\n", probability * 100);
     
      
    }
    
		
}
