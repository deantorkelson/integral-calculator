import java.util.Scanner;
import java.lang.Math;

public class Calculator {
	public Calculator() {
	}

	Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		calc.intro();
		double[] coefs = calc.createFunction();
		double[] bounds = calc.generateBounds();
		System.out.print("\nThe integral of the equation is approximately " + calc.integrate(bounds, coefs));
	}

	public void intro() {
		// nothing says "employable" like mastery of ASCII art!
		System.out.println("/////////////////////////");
		System.out.println("/                       /");
		System.out.println("/ INTEGRAL APPROXIMATOR /");
		System.out.println("/                       /");
		System.out.println("/////////////////////////\n\n");
	}

	public double[] createFunction() {
		int answer = 0;
		System.out.println("How many terms are in the function you want to integrate?");
		try {
			answer = keyboard.nextInt();
		} catch (Exception e) {
			// if the input is not an int, throws an error
			throw new IllegalArgumentException("ERROR: Invalid input.");
		}
		if (answer == 0) {
			// if the number of terms is zero, throws an error
			throw new IllegalArgumentException("Invalid function. Number of terms must be greater than 0.");
		}
		double[] nums = new double[answer];
		// creates the array of coefficients. The index of each element is its
		// corresponding power of x
		for (int i = answer - 1; i > 0; i--) {
			System.out.println("\nWhat is the coefficient for x^" + i + "?");
			nums[i] = keyboard.nextDouble();
		}
		// this is outside the original loop so as not to output "x^0",
		// which makes it look much cleaner and more recognizable as a function
		System.out.println("\nWhat is the constant?");
		nums[0] = keyboard.nextDouble();
		System.out.println("\nHere is the function you're integrating: \n" + toString(nums));
		return nums;
	}

	public double[] generateBounds() {
		// bounds[0] = lower bounds, bounds[1] = upper bounds,
		// bounds[2] = number of trapezoids to generate the integral with
		double[] bounds = new double[3];
		System.out.println("\nWhat's the lower bound of the interval?");
		bounds[0] = keyboard.nextDouble();
		System.out.println("\nWhat's the upper bound?");
		bounds[1] = keyboard.nextDouble();
		// if they are accidentally put out of order, swaps them
		// assumes user error, not trying to find the negative integral
		if (bounds[1] < bounds[0]) {
			double tmp = bounds[1];
			bounds[1] = bounds[0];
			bounds[0] = tmp;
		}
		System.out.println("\nHow many trapezoids?");
		bounds[2] = keyboard.nextDouble();
		return bounds;
	}

	public double integrate(double[] bounds, double[] coefs) {
		double y1 = 0;
		double y2 = 0;
		double area = 0;
		// distance x must increment each time to have the appropriate number of
		// trapezoids
		double increment = (bounds[1] - bounds[0]) / bounds[2];
		// finds the total area, where x is the x value of the trapezoids
		for (double x = bounds[0]; x <= bounds[1] - increment; x += increment) {
			// creates a value for y1 (height 1 of the trapezoid)
			for (double i = 1; i < coefs.length; i++) {
				y1 += coefs[(int) i] * Math.pow(x, i);
			}
			y1 += coefs[0];
			// creates a value for y2 (height 2 of the trapezoid)
			for (double i = 1; i < coefs.length; i++) {
				y2 += coefs[(int) i] * Math.pow(x + increment, i);
			}
			y2 += coefs[0];
			// applying trapezoid area equation find the area of the trapezoid,
			// which gets added to the total
			area += ((y1 + y2) / 2) * increment;
			y1 = 0;
			y2 = 0;
		}
		// rounding off to 4 decimal places
		area = Math.floor(area * 10000) / 10000;
		return area;
	}

	public String toString(double[] nums) {
		StringBuilder sb = new StringBuilder();
		if (nums[nums.length - 1] < 0) {
			sb.append("-");
		}
		for (int i = nums.length - 1; i > 0; i--) {
			// not necessary, but looks much cleaner without all the zeroes
			if (nums[i] != 0) {
				sb.append(Math.abs(nums[i]) + "x^" + (i));
			}
			// applies the proper sign for the next term
			if (nums[i - 1] < 0) {
				sb.append(" - ");
			} else if (nums[i - 1] > 0) {
				sb.append(" + ");
			}
		}
		// again, unnecessary, but if constant is zero, it gets left out
		// of the function for cleanliness purposes
		if (nums[0] == 0) {
			return sb.toString();
		} else {
			sb.append(Math.abs(nums[0]));
		}
		return sb.toString();
	}
}