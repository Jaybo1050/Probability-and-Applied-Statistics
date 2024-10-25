
public class StandardDeviation {

    private int[] data = {1, 2, 3, 4, 5, 6, 7, 8};

    private double mean() {
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        return (double) sum / data.length;
    }
    private double variance() {
        double mean = mean();
        double sumOfSquares = 0;

        for (int value : data) {
            sumOfSquares += Math.pow(value - mean, 2);
        }

        return sumOfSquares / (data.length - 1);
    }
    public double calculateStandardDeviation() {
        double variance = variance();
        return Math.sqrt(variance);
    }

}
