package NonLinear;

import static java.lang.Math.*;

@SuppressWarnings("Duplicates")
public class Function {

    private int func_num;

    Function() {
    }

    public Function(int func_num) {
        this.func_num = func_num;
    }

    void setFunc_num(int func_num) {
        this.func_num = func_num;
    }

    public double calcFunc(double x) {
        if (func_num == 1) {
            return pow(x, 3) - x + 4;
        } else if (func_num == 2) {
            return pow(x, 2) - exp(-(x / 2));
        } else if (func_num == 3) {
            return exp(-(x / 2)) - 3;
        } else {
            return (cos(x + 2)) / 2;
        }
    }

    private double calcDerivativeOfFunc(double x) {
        if (func_num == 1) {
            return 3 * pow(x, 2) - 1;
        } else if (func_num == 2) {
            return 2 * x + (exp(-x / 2) / 2);
        } else if (func_num == 3) {
            return -(exp(-x / 2) / 2);
        } else {
            return -(sin(x + 2)) / 2;
        }
    }

    boolean checkSection(double a, double b) {
        return calcFunc(a) * calcFunc(b) < 0;
    }

    private double maxDerivativeFunc(double a, double b) {
        return a > b ? a : b;
    }

    double lambda(double a, double b) {
        double maxDerivative = maxDerivativeFunc(calcDerivativeOfFunc(a), calcDerivativeOfFunc(b));
        return -(1 / maxDerivative);
    }

    String showFunction(int func_num) {
        System.out.println("Выбранное уравнение: ");
        if (func_num == 1) {
            return "x^3 - x + 4 = 0";
        } else if (func_num == 2) {
            return "x^2 - e^(-x/2)";
        } else if (func_num == 3) {
            return "e^(-x/2) - 3 = 0";
        } else {
            return "cos(x + 2) / 2";
        }
    }

    @Override
    public String toString() {
        return "Выбирите уравнение, корень которого хотите найти:\n" +
                "1.x^3 - x + 4 = 0\n" +
                "2.x^2 - e^(-x/2) = 0\n" +
                "3.e^(-x/2) - 3 = 0\n" +
                "4.(cos(x + 2)) / 2";
    }

}


// [0,5; 1]

// pow(x, 3) - pow(x, 2) - 9 * x + 9
// [-4; 2] [2,5; 4] [0,5; 2]