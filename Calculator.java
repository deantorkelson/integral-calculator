import java.util.Scanner;

public class Calculator
{
	public Calculator() {
	}
	
	public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.integrate();
    }
    
	boolean knowEq = false;
	double a = 0;
	double b = 0;
	double c = 0;
	double d = 0;
	Scanner keyboard = new Scanner(System.in);
	double upperBound = 0;
	double lowerBound = 0;

    public void integrate() {
    	System.out.println("/////////////////////////");
    	System.out.println("/                       /");
    	System.out.println("/ INTEGRAL APPROXIMATOR /");
   		System.out.println("/                       /");
   		System.out.println("/////////////////////////");
   		System.out.println();
   		System.out.println();
   		System.out.println();
   		System.out.println("Do you want to approximate a cubic or quadratic equation?\nType \"cubic\" or \"quadratic\".");
		String answer = keyboard.nextLine();
		while (knowEq == false) {
			if (answer.equals("cubic") || answer.equals("Cubic")) {
				System.out.println(integrateCube(createCubicEquation()));
				//call thing to approximate the equation
				knowEq = true;
			}
			else if (answer.equals("quadratic") || answer.equals("Quadratic")) {
				System.out.println(integrateQuad(createQuadraticEquation()));
				//call thing to approximate the equation
				knowEq = true;
			}
			else {
				System.out.println();
				System.out.println("Sorry, I didn't quite catch that.\nMake sure your answer is either \"cubic\" or \"quadratic\".");
				System.out.println("Do you want to approximate a cubic or quadratic equation?");
				answer = keyboard.nextLine();
			}
		}
    }

	public double[] createCubicEquation(){
		double[] eq = new double[4];
		System.out.println();
		System.out.println("Okay, please input the variables of the equation, following this form: \nax^3 + bx^2 + cx + d");
		System.out.print("a = ");
		a = keyboard.nextDouble();
		System.out.println();
		System.out.print("b = ");
		b = keyboard.nextDouble();
		System.out.println();
		System.out.print("c = ");
		c = keyboard.nextDouble();
		System.out.println();
		System.out.print("d = ");
		d = keyboard.nextDouble();
		System.out.println();
		eq[0] = a;
		eq[1] = b;
		eq[2] = c;
		eq[3] = d;
		return eq;
	}
		
	public double[] createQuadraticEquation(){
		double[] eq = new double[3];
		System.out.println();
		System.out.println("Okay, please input the variables of the equation, following this form: \nax^2 + bx + c");
		System.out.print("a = ");
		a = keyboard.nextDouble();
		System.out.println();
		System.out.print("b = ");
		b = keyboard.nextDouble();
		System.out.println();
		System.out.print("c = ");
		c = keyboard.nextDouble();
		System.out.println();
		eq[0] = a;
		eq[1] = b;
		eq[2] = c;
		return eq;
	}

    public double integrateCube(double[] eq){
    	System.out.println();
    	System.out.println("This is the equation being integrated : ");
    	System.out.println(eq[0] + "x^3 + " + eq[1] + "x^2 + " + eq[2] + "x + " + eq[3]);
    	System.out.println();
    	System.out.println("What's the lower bound of the interval?");
    	lowerBound = keyboard.nextDouble();
    	System.out.println();
    	System.out.println("What's the upper bound?");
    	upperBound = keyboard.nextDouble();
    	if (upperBound < lowerBound){
    		double tmp = upperBound;
    		upperBound = lowerBound;
    		lowerBound = tmp;
    	}
    	System.out.println();
    	System.out.print("The integral of the equation is approximately");
    	return(sumCube(eq, upperBound, lowerBound));
    }

    public double integrateQuad(double[]eq){
    	System.out.println();
    	System.out.println("This is the equation being integrated : ");
    	System.out.println(eq[0] + "x^2 + " + eq[1] + "x + " + eq[2]);
    	System.out.println();
    	System.out.println("What's the lower bound of the interval?");
    	lowerBound = keyboard.nextDouble();
    	System.out.println();
    	System.out.println("What's the upper bound?");
    	upperBound = keyboard.nextDouble();
    	if (upperBound < lowerBound){
    		double tmp = upperBound;
    		upperBound = lowerBound;
    		lowerBound = tmp;
    	}
    	System.out.println();
    	System.out.print("The integral of the equation is approximately");
    	return(sumQuad(eq, upperBound, lowerBound));
    }

	public double sumQuad(double[]eq, double up, double low){
    	double integral = 0.0;
    	for (double i = low; i < up; i += .25){
    		double k = i +.25;
    		double point1 = 0;
    		double point2 = 0;
    		point1 += i*i*eq[0];
    		point1 += i*eq[1];
    		point1 += eq[2];
    		point2 += k*k*eq[0];
    		point2 += k*eq[1];
    		point2 += eq[2];
    		integral += ((point1 + point2)/2)*.25;
    	}
    	return integral;
    }

    public double sumCube(double[]eq, double up, double low){
    	double integral = 0.0;
    	for (double i = low; i < up; i += .25){
    		double k = i +.25;
    		double point1 = 0;
    		double point2 = 0;
    		point1 += i*i*i*eq[0];
    		point1 += i*i*eq[1];
    		point1 += i*eq[2];
    		point1 += eq[3];
    		point2 += k*k*k*eq[0];
    		point2 += k*k*eq[1];
    		point2 += k*eq[2];
    		point2 += eq[3];
    		integral += ((point1 + point2)/2)*.25;
    	}
    	return integral;
    }
}