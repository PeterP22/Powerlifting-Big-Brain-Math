package random;

import java.util.Scanner;

public class BenchPressOneRepMaxCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the user's input
        System.out.println("How many reps did you perform?");
        int reps = scanner.nextInt();

        System.out.println("What was the weight you lifted (in kilograms)?");
        double weight = scanner.nextDouble();

        // Calculate the 1RM using the Brzycki formula
        double oneRepMax = weight / (1.0278 - (0.0278 * reps));

        // Print the result
        System.out.println("Your estimated 1RM is " + oneRepMax + " kilograms.");
    }
}