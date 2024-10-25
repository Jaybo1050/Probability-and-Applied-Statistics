
public class Mean {
	
	private int[] data = {1, 2, 3, 4, 5, 7, 8, 9, 32};

   
    public double calculateMean() {
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        return (double) sum / data.length;
    }

}
