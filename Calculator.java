import java.util.Scanner;
import java.lang.Math;

public class Calculator
{
	public Calculator() {
	}
	Scanner keyboard = new Scanner(System.in);
	
	
	public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.intro();
        double[] coefs = calc.createFunction();
        double[] bounds = calc.generateBounds();
        System.out.println(calc.integrate(bounds, coefs));
    }

    public void intro() {
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
   		    System.err.println("ERROR: Invalid input.");
   		}
   		if (answer == 0) {
   			throw new IllegalArgumentException("Invalid function. Number of terms must be greater than 0.");
   		}
   		double[] nums = new double[answer];
    	for (int i = answer - 1; i > 0; i--) {
    		System.out.println("\nWhat is the coefficient for x^" + (i) + "?");
    		nums[i] = keyboard.nextDouble();
    	}
    	System.out.println("\nWhat is the constant?");
    	nums[0] = keyboard.nextDouble();
    	System.out.println("\nHere is the function you're integrating: \n" + toString(nums));
    	return nums;
    }
    
    public double[] generateBounds() {
    	double[] bounds = new double[3];
    	System.out.println("\nWhat's the lower bound of the interval?");
    	bounds[0] = keyboard.nextDouble();
    	System.out.println("\nWhat's the upper bound?");
    	bounds[1] = keyboard.nextDouble();
    	if (bounds[1] < bounds[0]){
    		double tmp = bounds[1];
    		bounds[1] = bounds[0];
    		bounds[0] = tmp;
    	}
    	System.out.println("\nHow many trapezoids?");
    	bounds[2] = keyboard.nextDouble();
    	System.out.print("\nThe integral of the equation is approximately ");
    	return bounds;
    }
    
    public double integrate(double[] bounds, double[] coefs) {
    	double y1 = 0;
    	double y2 = 0;
    	double area = 0;
    	//distance i must increment each time to have the appropriate number of trapezoids
    	double increment = (bounds[1] - bounds[0]) / bounds[2];
    	//finds the total area
    	for (double i = bounds[0]; i <= bounds[1] - increment; i += increment) {
    		//creates a value for y1
    		for (double j = 1; j < coefs.length; j++) {
    			y1 += coefs[(int)j] * Math.pow(i, j);
    		}
    		y1 += coefs[0];
    		//creates a value for y2
    		for (double j = 1; j < coefs.length; j++) {
    			y2 += coefs[(int)j] * Math.pow(i + increment, j);
    		}
    		y2 += coefs[0];
    		//applying trapezoid area equation
    		area += ((y1 + y2) / 2) * increment;
    		y1 = 0;
    		y2 = 0;
    	}
    	area *= 100000;
    	area = (int)area;
    	area = (double)area;
    	area /= 100000;
    	return area;
    }

    public String toString(double[] nums) {
    	StringBuilder sb = new StringBuilder();
    	if (nums[nums.length - 1] < 0) {
    		sb.append("-");
    	}
    	for (int i = nums.length - 1; i > 0; i--) {
    		if(nums[i] != 0) {
    			sb.append(Math.abs(nums[i]) + "x^" + (i));
    		}
    		if (nums[i - 1] < 0) {
    			sb.append(" - ");
    		} else if (nums[i - 1] > 0) {
    			sb.append(" + ");
    		}
    	}
    	if (nums[0] == 0) {
			return sb.toString();
		} else {
    		sb.append(Math.abs(nums[0]));
    	}
    	return sb.toString();
    }
}