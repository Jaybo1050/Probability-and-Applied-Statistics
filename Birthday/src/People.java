import java.util.Random;

public class People {
	
	int birthday;
	
	public People() {
		
        Random rand = new Random();
        
        this.birthday = rand.nextInt(365); 
        
    }

    
    public int getBirthday() {
    	
        return birthday;
        
    }
    
    public static boolean hasSharedBirthday(int people) {
    	
        boolean[] calendar = new boolean[365]; 
        
        for (int i = 0; i < people; i++) {
        	
            People person = new People(); 
            
            int birthday = person.getBirthday();
            
            if (calendar[birthday]) {
                return true; 
            }
            calendar[birthday] = true; 
        }
        return false;  
    }
}
	


