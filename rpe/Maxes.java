package rpe;

import java.io.*;

public class Maxes implements Serializable {
    private double squatMax;
    private double benchMax;
    private double deadliftMax;

    // Getters and setters for each max
    public double getSquatMax() {
        return squatMax;
    }

    public void setSquatMax(double squatMax) {
        this.squatMax = squatMax;
    }

    public double getBenchMax() {
        return benchMax;
    }

    public void setBenchMax(double benchMax) {
        this.benchMax = benchMax;
    }

    public double getDeadliftMax() {
        return deadliftMax;
    }

    public void setDeadliftMax(double deadliftMax) {
        this.deadliftMax = deadliftMax;
    }

    // Method to save maxes to a file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Static method to load maxes from a file
    public static Maxes loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Maxes) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new Maxes(); // Return empty Maxes object if file not found or error occurs
        }
    }
}
