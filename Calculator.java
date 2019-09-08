import java.util.Scanner;
import java.lang.Math;

public class Calculator {

	public double calculateIntegral() {
		InputHandler inputHandler = new InputHandler(new Scanner(System.in));
		Function function = inputHandler.getFunction();
		double[] bounds = inputHandler.getBounds();
		int numberOfTrapezoids = inputHandler.getNumberOfTrapezoids();
		return integrate(function, bounds, numberOfTrapezoids);
	}

	public double integrate(Function function, double[] bounds, int numberOfTrapezoids) {
		double area = 0;
		double increment = (bounds[1] - bounds[0]) / numberOfTrapezoids;
		// finds the total area, where x is the x value of the trapezoids
		for (double x = bounds[0]; x <= bounds[1] - increment; x += increment) {
			area += getAreaOfTrapezoid(function, x, increment);
		}
		// rounding off to 4 decimal places
		area = Math.floor(area * 10000) / 10000;
		return area;
	}

	private double getAreaOfTrapezoid(Function function, double x, double increment) {
		double y1 = getYValue(function, x);
		double y2 = getYValue(function, x + increment);
		return ((y1 + y2) / 2) * increment;
	}

	private double getYValue(Function function, double x) {
		double yValue = 0;
		for (double i = 1; i < function.getCoefficients().length; i++) {
			yValue += function.getCoefficients()[(int) i] * Math.pow(x, i);
		}
		yValue += function.getCoefficients()[0];
		return yValue;
	}
}
