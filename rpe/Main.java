package rpe;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "maxes.dat";
    private static Maxes maxes; // Will hold the maxes

    public static void main(String[] args) {
        // Load maxes from file at start
        maxes = Maxes.loadFromFile(FILE_NAME);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Calculate Estimated 1RM");
            System.out.println("2. Determine Weight for Specific Reps and RPE");
            System.out.println("3. Reset Maxes");
            System.out.println("4. Show Current Maxes");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    calculateOneRepMax();
                    break;
                case 2:
                    determineWeightForRepsAndRpe();
                    break;
                case 3:
                    updateMaxes();
                    maxes.saveToFile(FILE_NAME); // Save after updating maxes
                    break;
                case 4:
                    showCurrentMaxes();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }

    private static void showCurrentMaxes() {
        System.out.println("Current Maxes:");
        System.out.printf("Squat: %.2f kg\n", maxes.getSquatMax());
        System.out.printf("Bench: %.2f kg\n", maxes.getBenchMax());
        System.out.printf("Deadlift: %.2f kg\n", maxes.getDeadliftMax());
    }

    private static void updateMaxes() {
        System.out.println("Choose the lift to update:");
        System.out.println("1. Squat");
        System.out.println("2. Bench");
        System.out.println("3. Deadlift");
        System.out.print("Enter your choice: ");
        int liftChoice = scanner.nextInt();
        System.out.print("Enter the new max: ");
        double newMax = scanner.nextDouble();

        switch (liftChoice) {
            case 1:
                maxes.setSquatMax(newMax);
                System.out.println("Squat max updated.");
                maxes.saveToFile(FILE_NAME); // Save after updating any max
                break;
            case 2:
                maxes.setBenchMax(newMax);
                System.out.println("Bench max updated.");
                maxes.saveToFile(FILE_NAME); // Save after updating any max
                break;
            case 3:
                maxes.setDeadliftMax(newMax);
                System.out.println("Deadlift max updated.");
                maxes.saveToFile(FILE_NAME); // Save after updating any max
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void calculateOneRepMax() {
        System.out.println("Enter weight lifted: ");
        double weight = scanner.nextDouble();

        System.out.println("Enter number of reps: ");
        int reps = scanner.nextInt();

        System.out.println("Enter RPE (e.g., 6, 6.5, 7, ...): ");
        double rpe = scanner.nextDouble();

        double oneRepMax = OneRepMaxCalculator.calculateOneRepMax(weight, reps, rpe);
        if (oneRepMax != 0) {
            System.out.printf("Estimated 1RM: %.2f\n", oneRepMax);
        } else {
            System.out.println("Invalid input or calculation error.");
        }
    }

    private static void determineWeightForRepsAndRpe() {
        System.out.println("Enter your estimated 1RM: ");
        double oneRepMax = scanner.nextDouble();

        System.out.println("Enter desired number of reps: ");
        int desiredReps = scanner.nextInt();

        System.out.println("Enter target RPE: ");
        double targetRpe = scanner.nextDouble();

        System.out.println("Enter range percentage (e.g., 5 for +/- 5%): ");
        double rangePercentage = scanner.nextDouble();

        OneRepMaxCalculator.WeightRange weightRange = OneRepMaxCalculator.calculateWeightForRepsAndRpe(oneRepMax, desiredReps, targetRpe, rangePercentage);
        if (weightRange != null) {
            System.out.println(weightRange);
        } else {
            System.out.println("Invalid input or calculation error.");
        }
    }
}
