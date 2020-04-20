package SystemNonLInear;


import Graphics.DrawGraph;
import org.jfree.ui.RefineryUtilities;

import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class Main {
    private static boolean mistake;
    private static Scanner scanner = new Scanner(System.in);
    private static SystemFunction systemFunction = new SystemFunction();
    private static double a1;
    private static double b1;
    private static double a2;
    private static double b2;

    public static void main(String[] args) {
        int func_num = Function();
        double accuracy = Accuracy();
        SolvSystemNonlinearEquations system = new SolvSystemNonlinearEquations(func_num, accuracy);
        interval();
        system.methodIteration();
        System.out.println(system.toString());
        DrawGraph drawGraph = new DrawGraph("График уравнений", func_num, a1, b1, a2, b2);
        drawGraph.pack();
        RefineryUtilities.centerFrameOnScreen(drawGraph);
        drawGraph.setVisible(true);

    }


    private static double Accuracy() {
        double accuracy;
        do {
            System.out.println("Введите точность:");
            mistake = false;
            accuracy = scanner.nextDouble();
            if (accuracy < 0.0000001 && accuracy >= 0) {
                mistake = true;
                System.out.println("Точность должна быть не больше 7 знаков после запетой");
            }
            if (systemFunction.getFunc_num() == 1) {
                if (accuracy < 0.01) {
                    accuracy = 0.01;
                }
            }
        } while (mistake);
        return accuracy;
    }

    private static int Function() {
        int number;
        do {
            System.out.println(systemFunction.toString());
            mistake = false;
            number = scanner.nextInt();
            if (number < 1 || number > 4) {
                mistake = true;
                System.out.println("Введите число в диапазоне от 1 до 4 включительно.");
            }
            systemFunction.setFunc_num(number);
        } while (mistake);
        System.out.println(systemFunction.showFunction(number));
        return number;
    }

    private static void interval() {
        int func_num = systemFunction.getFunc_num();
        if (func_num == 1) {
            a1 = 0.5;
            b1 = 10;
            a2 = 0.61;
            b2 = 10;
        } else if (func_num == 2) {
            a1 = -10;
            b1 = 10;
            a2 = -10;
            b2 = 10;
        } else if (func_num == 3) {
            a1 = -5;
            b1 = 5;
            a2 = -3;
            b2 = 3;
        } else {
            a1 = -9;
            b1 = 9;
            a2 = -10;
            b2 = 3.8;
        }
    }
}
