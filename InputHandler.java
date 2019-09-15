import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private Scanner keyboard;

    public InputHandler(Scanner input) {
         keyboard = input;
    }

    public Function getFunction() {
        int degree = getFunctionDegree();
        double[] coefficients = getCoefficients(degree);
        Function function = new Function(coefficients);
        System.out.println("\nHere is the function you're integrating: \n" + function.toString());
        return function;
    }

    public double[] getBounds() {
        // bounds[0] = lower bound, bounds[1] = upper bound
        double[] bounds = new double[2];
        System.out.println("\nWhat's the lower bound of the interval?");
        bounds[0] = this.getSafeInputDouble();
        System.out.println("\nWhat's the upper bound?");
        bounds[1] = this.getSafeInputDouble();
        // if they are accidentally put out of order, swaps them (doesn't handle negative integrals)
        if (bounds[1] < bounds[0]) {
            double tmp = bounds[1];
            bounds[1] = bounds[0];
            bounds[0] = tmp;
        }
        return bounds;
    }

    public int getNumberOfTrapezoids() {
        System.out.println("\nHow many trapezoids?");
        return this.getSafeInputInt();
    }

    private int getFunctionDegree() {
        System.out.println("How many terms are in the function you want to integrate?");
        int degree = getSafeInputInt();
        if (degree <= 0) {
            throw new IllegalArgumentException("Invalid value. Number of terms must be greater than 0.");
        }
        return degree;
    }

    private double[] getCoefficients(int degree) {
        double[] coefficients = new double[degree];
        // creates an array of coefficients with index == degree of term
        for (int i = degree - 1; i > 0; i--) {
            System.out.println("\nWhat is the coefficient for x^" + i + "?");
            coefficients[i] = this.getSafeInputDouble();
        }
        // doing this outside loop to prevent x^0 ugliness
        System.out.println("\nWhat is the constant?");
        coefficients[0] = this.getSafeInputDouble();
        return coefficients;
    }

    // Error-handling functions for getting input
    private int getSafeInputInt() {
        while (true) {
            int input;
            try {
                input = keyboard.nextInt();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Error: invalid input. Please try again.");
                keyboard.nextLine();
            }
        }
    }

    private double getSafeInputDouble() {
        while (true) {
            try {
                return keyboard.nextDouble();
            } catch (Exception e) {
                System.out.println("Error: invalid input. Please try again.");
                keyboard.nextLine();
            }
        }
    }
}
