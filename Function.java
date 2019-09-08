public class Function {
    private double[] coefficients;

    public Function(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (coefficients[coefficients.length - 1] < 0) {
            sb.append("-");
        }
        for (int i = coefficients.length - 1; i > 0; i--) {
            if (coefficients[i] != 0) {
                sb.append(Math.abs(coefficients[i]) + "x^" + (i));
            }
            sb.append(this.getNextSign(coefficients[i - 1]));
        }
        // do not print constant if it is zero
        if (coefficients[0] == 0) {
            return sb.toString();
        } else {
            sb.append(Math.abs(coefficients[0]));
        }
        return sb.toString();
    }


    private String getNextSign(double coefficient) {
        if (coefficient < 0) {
            return " - ";
        } else if (coefficient > 0) {
            return " + ";
        }
        return "";
    }
}
