package rpe;

import java.util.HashMap;
import java.util.Map;

public class OneRepMaxCalculator {

    private static final Map<Double, double[]> rpeChart = new HashMap<>();
    static {
        rpeChart.put(10.0, new double[]{1.000, 0.955, 0.922, 0.892, 0.863, 0.837, 0.811, 0.786, 0.762, 0.739});
        rpeChart.put(9.5, new double[]{0.978, 0.939, 0.907, 0.878, 0.850, 0.824, 0.799, 0.774, 0.751, 0.723});
        rpeChart.put(9.0, new double[]{0.955, 0.922, 0.892, 0.863, 0.837, 0.811, 0.786, 0.762, 0.739, 0.707});
        rpeChart.put(8.5, new double[]{0.939, 0.907, 0.878, 0.850, 0.824, 0.799, 0.774, 0.751, 0.723, 0.694});
        rpeChart.put(8.0, new double[]{0.922, 0.892, 0.863, 0.837, 0.811, 0.786, 0.762, 0.739, 0.707, 0.680});
        rpeChart.put(7.5, new double[]{0.907, 0.878, 0.850, 0.824, 0.799, 0.774, 0.751, 0.723, 0.694, 0.667});
        rpeChart.put(7.0, new double[]{0.892, 0.863, 0.837, 0.811, 0.786, 0.762, 0.739, 0.707, 0.680, 0.653});
        rpeChart.put(6.5, new double[]{0.878, 0.850, 0.824, 0.799, 0.774, 0.751, 0.723, 0.694, 0.667, 0.640});
        rpeChart.put(6.0, new double[]{0.863, 0.837, 0.811, 0.786, 0.762, 0.739, 0.707, 0.680, 0.653, 0.626});
        rpeChart.put(5.5, new double[]{0.85, 0.824, 0.799, 0.774, 0.751, 0.723, 0.694, 0.667, 0.64, 0.613});
        rpeChart.put(5.0, new double[]{0.837, 0.811, 0.786, 0.762, 0.739, 0.707, 0.68, 0.653, 0.626, 0.599});
    }

    public static double calculateOneRepMax(double weight, int reps, double rpe) {
        if (weight == 0 || reps == 0 || rpe == 0) {
            return 0; // Equivalent to blank check in your formula
        }

        double[] coefficients = rpeChart.get(rpe);
        if (coefficients == null || reps - 1 >= coefficients.length) {
            return 0; // Handle invalid RPE or reps
        }

        double coefficient = coefficients[reps - 1];
        return mround(weight / coefficient); // Now rounds to the nearest 1kg
    }

    public static WeightRange calculateWeightForRepsAndRpe(double oneRepMax, int desiredReps, double targetRpe, double rangePercentage) {
        double[] coefficients = rpeChart.get(targetRpe);
        if (coefficients == null || desiredReps - 1 >= coefficients.length) {
            return null; // Handle invalid RPE or reps
        }

        double coefficient = coefficients[desiredReps - 1];
        double baseWeight = oneRepMax * coefficient;

        double lowerBound = baseWeight * (1 - rangePercentage / 100.0);
        double upperBound = baseWeight * (1 + rangePercentage / 100.0);

        return new WeightRange(baseWeight, lowerBound, upperBound);
    }

    private static double mround(double number) {
        return 1.0 * Math.round(number);
    }

    public static class WeightRange {
        public final double baseWeight;
        public final double lowerBound;
        public final double upperBound;

        public WeightRange(double baseWeight, double lowerBound, double upperBound) {
            this.baseWeight = baseWeight;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        public String toString() {
            return String.format("Recommended Weight: %.2f kg\nRange: %.2f kg - %.2f kg", baseWeight, lowerBound, upperBound);
        }
    }
}
