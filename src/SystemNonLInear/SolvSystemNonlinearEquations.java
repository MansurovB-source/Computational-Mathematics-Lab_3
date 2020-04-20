package SystemNonLInear;

import static java.lang.Math.max;

class SolvSystemNonlinearEquations {
    private SystemFunction systemFunction;
    private double accuracy;
    private double x_1;
    private double x_2;
    private int counter = 0;
    private double computate_accuracy;

    SolvSystemNonlinearEquations(int func_num, double accuracy) {
        systemFunction = new SystemFunction(func_num);
        this.accuracy = accuracy;
    }

    void methodIteration() {
        double prev_x_1 = systemFunction.x0(1);
        double prev_x_2 = systemFunction.x0(2);
        double accuracy_x1;
        double accuracy_x2;

        do {
            counter++;
            x_1 = prev_x_1 + systemFunction.phi(prev_x_1, prev_x_2, 1);
            x_2 = prev_x_2 + systemFunction.phi(prev_x_1, prev_x_2, 2);

            accuracy_x1 = Math.abs(x_1 - prev_x_1);
            accuracy_x2 = Math.abs(x_2 - prev_x_2);
            computate_accuracy = max(accuracy_x1, accuracy_x2);

            prev_x_1 = x_1;
            prev_x_2 = x_2;
            //System.out.println(x_1 + " " + x_2);
        } while (accuracyAssessment(computate_accuracy) && counter < 15000);
    }

    private boolean accuracyAssessment(double accur) {
        return !(accur <= accuracy);
    }

    @Override
    public String toString() {
        if (counter < 1000) {
            return "x_1: " + x_1 + "\n" +
                    "x_2: " + x_2 + "\n" +
                    "Точность: " + computate_accuracy + "\n" +
                    "Количество итераций: " + counter;
        } else {
            return "x_1: " + x_1 + "\n" +
                    "x_2: " + x_2 + "\n" +
                    "Не было достигнута заданная точность за 15000 итераций";
        }
    }
}
