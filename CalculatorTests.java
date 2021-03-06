public class CalculatorTests {

    public static void main(String[] args) {
//        CalculatorTests.testIntegralCalculator();
        CalculatorTests.testInputHandler();
    }

    public static void testIntegralCalculator() {
        Calculator calc = new Calculator();
        double[] coefficients = {5, -2.7, 1};
        Function function = new Function(coefficients);
        assert function.toString().equals("1.0x^2 - 2.7x^1 + 5.0");
        double[] bounds = {0, 5};
        double integral = calc.calculateIntegral(function, bounds, 50);
        double expected = 32.91666;
        // asserting less than 5% error
        assert Math.abs((integral - expected)/expected) < .05;
    }

    public static void testInputHandler() {
        Calculator calc = new Calculator();
        // use '3, 5, -2.7, 1, 0, 5, 50' as input
        double integral = calc.calculateIntegralFromInput();
        double expected = 32.91666;
        // asserting less than 5% error
        assert Math.abs((integral - expected)/expected) < .05;
    }
}
