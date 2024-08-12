public class InfinityExample {
    public static void main(String[] args) {
        double positiveInf = 1.0 / 0.0;
        double negativeInf = -1.0 / 0.0;

        System.out.println("Positive Infinity: " + positiveInf);
        System.out.println("Negative Infinity: " + negativeInf);

        // Using constants
        System.out.println("Double.POSITIVE_INFINITY: " + Double.POSITIVE_INFINITY);
        System.out.println("Double.NEGATIVE_INFINITY: " + Double.NEGATIVE_INFINITY);
    }
}
