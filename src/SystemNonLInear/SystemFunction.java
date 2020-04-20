package SystemNonLInear;


import static java.lang.Math.log10;
import static java.lang.Math.pow;

@SuppressWarnings("Duplicates")
public class SystemFunction {

    private int func_num;


    SystemFunction() {
    }

    public SystemFunction(int func_num) {
        this.func_num = func_num;
    }

    public int getFunc_num() {
        return func_num;
    }

    public void setFunc_num(int func_num) {
        this.func_num = func_num;
    }

    double phi(double x_1, double x_2, int x) {
        if (func_num == 1) {
            return (-1 / (((1 + ((3 * 0.43429) / x_1)) * (-x_1)) - (((4 * x_1) - x_2 - 5) * (-2 * x_2)))) * funcPhi_1(x_1, x_2, x);
        } else if (func_num == 2) {
//            double k = (-1 / ((9 * pow(x_1, 2) * pow(x_2, 2)) + 1));
//            double l = funcPhi_2(x_1, x_2, x);
            return (-1 / ((9 * pow(x_1, 2) * pow(x_2, 2)) + 1)) * funcPhi_2(x_1, x_2, x);
        } else if (func_num == 3) {
            return (-1 / (2 * (x_2 - x_1))) * funcPhi_3(x_1, x_2, x);
        } else {
            return (-1 / ((64 * x_1 * x_2) + (27 * pow(x_1, 2) * pow(x_2, 2)))) * funcPhi_4(x_1, x_2, x);
        }
    }

    private double funcPhi_1(double x_1, double x_2, int x) {
        if (x == 1) {
            return x_1 + (3 * log10(x_1)) - pow(x_2, 2);
        } else {
            return (2 * pow(x_1, 2)) - (x_1 * x_2) - (5 * x_1) + 1;
        }
    }

    private double funcPhi_2(double x_1, double x_2, int x) {
        if (x == 1) {
            return pow(x_1, 3) + x_2 - 1;
        } else {
            return pow(x_2, 3) - x_1 + 1;
        }
    }

    private double funcPhi_3(double x_1, double x_2, int x) {
        if (x == 1) {
            return x_1 + x_2 - 3;
        } else {
            return pow(x_1, 2) + pow(x_2, 2) - 9;
        }
    }

    private double funcPhi_4(double x_1, double x_2, int x) {
        if (x == 1) {
            return (4 * pow(x, 2)) - pow(x_2, 3) + 28;
        } else {
            return (3 * pow(x_1, 3)) + (4 * pow(x_2, 2)) - 145;
        }
    }

    public double funcForGraphic(int number, double x) {
        if (func_num == 1) {
            return funcForGraphic_1(number, x);
        } else if (func_num == 2) {
            return funcForGraphic_2(number, x);
        } else if (func_num == 3) {
            return funcForGraphic_3(number, x);
        } else {
            return funcForGraphic_4(number, x);
        }
    }

    private double funcForGraphic_1(int number, double x) {
        if (number == 1) {
            if (x < 0.05) {
                return 0;
            } else {
                return (2 * pow(x, 2) - (5 * x) + 1) / x;
            }
        } else {
            if (x < 0.62) {
                return 0;
            }
            return pow((x + (3 * log10(x))), 0.5);
        }
    }

    private double funcForGraphic_2(int number, double x) {
        if (number == 1) {
            return 1 - pow(x, 3);
        } else {
            return pow((x - 1), (1.0 / 3));
        }
    }

    private double funcForGraphic_3(int number, double x) {
        if (number == 1) {
            return 3 - x;
        } else {
            return pow((9 - pow(x, 2)), 0.5);
        }
    }

    private double funcForGraphic_4(int number, double x) {
        if (number == 1) {
            return pow((4 * pow(x, 2) + 28), (1.0 / 3));
        } else {
            return pow(((145 - (3 * pow(x, 3))) / 4), 0.5);
        }
    }

    double x0(int num) {
        if (func_num == 1) {
            if (num == 1) {
                return 3.5;
            } else {
                return 2.2;
            }
        } else if (func_num == 2) {
            if (num == 1) {
                return 1;
            } else {
                return 1;
            }
        } else if (func_num == 3) {
            if (num == 1) {
                return 1;
            } else {
                return 5;
            }
        } else {

            if (num == 1) {
                return 2.5;
            } else {
                return 3.5;
            }
        }
    }

    String showFunction(int func_num) {
        System.out.println("Выбранное система уравнений: ");
        if (func_num == 1) {
            return "---\n" +
                    "| 2x1^2 - x1x2 - 5x1 + 1 = 0\n" +
                    "| x1 + 3lgx1 + x2^2 = 0\n" +
                    "---\n" +
                    "\n";
        } else if (func_num == 2) {
            return "---\n" +
                    "| x1^3 + x2 - 1 = 0\n" +
                    "| x2^3 - x1 + 1 = 0\n" +
                    "---\n" +
                    "\n";
        } else if (func_num == 3) {
            return "---\n" +
                    "| x1 + x2 - 3 = 0\n" +
                    "| x1^2 + x2^2 - 9 = 0\n" +
                    "---\n" +
                    "\n";
        } else {
            return "---\n" +
                    "| 4*x1^2 - x2^3 + 28 = 0\n" +
                    "| 3*x1^3 + 4*x2^2 - 145 = 0\n" +
                    "---\n" +
                    "\n";
        }
    }


    @Override
    public String toString() {
        return "Выбирите систему уравнений:\n" +
                "1)\n" +
                "---\n" +
                "| 2x1^2 - x1x2 - 5x1 + 1 = 0\n" +
                "| x1 + 3lgx1 + x2^2 = 0\n" +
                "---\n" +

                "2)\n" +
                "---\n" +
                "| x1^3 + x2 - 1 = 0\n" +
                "| x2^3 - x1 + 1 = 0\n" +
                "---\n" +

                "3)\n" +
                "---\n" +
                "| x1 + x2 - 3 = 0\n" +
                "| x1^2 + x2^2 - 9 = 0\n" +
                "---\n" +

                "4)\n" +
                "---\n" +
                "| 4*x1^2 - x2^3 + 28 = 0 \n" +
                "| 3*x1^3 + 4*x2^2 - 145 = 0\n" +
                "---\n" +
                "\n";
    }
}
