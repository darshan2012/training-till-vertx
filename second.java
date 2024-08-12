
class NaNExample {
    public static void main(String[] args) {
        double nanValue = 0.0 / 0.0;
        double sqrtOfNegative = Math.sqrt(-1);

        System.out.println("NaN value: " + nanValue);
        System.out.println("Square root of -1: " + sqrtOfNegative);

        // Using constant
        System.out.println("Double.NaN: " + Double.NaN);

        // Checking for NaN
        System.out.println("Is NaN: " + Double.isNaN(nanValue));
        System.out.println("Is NaN: " + Double.isNaN(sqrtOfNegative));
    }
}
