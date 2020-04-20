package NonLinear;

import Graphics.DrawGraph;
import org.jfree.ui.RefineryUtilities;

import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static double section_a;
    private static double section_b;
    private static boolean mistake;
    private static Function function = new Function();

    public static void main(String[] args) {
        int func_num;
        double accuracy;
        func_num = Function();
        function.setFunc_num(func_num);
        accuracy = Accuracy();
        checkSection();
        SolvingNonlinearEquations solve = new SolvingNonlinearEquations(func_num, accuracy, section_a, section_b);
        solve.methodChord();
        solve.methodIteration();
        System.out.println(solve.toString());
        DrawGraph drawGraph_chord = new DrawGraph("Метод Хорд", func_num, section_a, section_b, solve.getXy_mChord(), solve.getIteration_chord());
        DrawGraph drawGraph_iterate = new DrawGraph("Метод итераций", func_num, section_a, section_b, solve.getXy_mIter(), solve.getIteration_iterate());
        drawGraph_chord.pack();
        drawGraph_iterate.pack();
        RefineryUtilities.centerFrameOnScreen(drawGraph_chord);
        RefineryUtilities.centerFrameOnScreen(drawGraph_iterate);
        drawGraph_chord.setVisible(true);
        drawGraph_iterate.setVisible(true);
    }

    private static void checkSection() {
        boolean flag = false;
        do {
            if (flag) {
                System.out.println("Необходимое условие существования корня уравнения на" +
                        "отрезке [a,b]:\n" +
                        "Теорема 1. Если непрерывная функция f(x) на концах отрезка\n" +
                        "[a; b] принимает значения разных знаков, т.е. f(a)·f(b)<0,\n" +
                        "то на этом отрезке содержится хотя бы один корень уравнения.\n");
            }
            flag = true;
            section_a = Section("левую");
            section_b = Section("правую");
        } while (!function.checkSection(section_a, section_b));
    }


    private static double Section(String text) {
        double section;
        do {
            System.out.println("Введите " + text + " границу: ");
            section = scanner.nextDouble();
            mistake = false;
            if (section < -1000 || section > 1000) {
                System.out.println("Введите число в диапазоне от -1000 до 1000 включительно");
                mistake = true;
            }
        } while (mistake);
        return section;
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
        } while (mistake);
        return accuracy;
    }

    private static int Function() {
        int number;
        do {
            System.out.println(function.toString());
            mistake = false;
            number = scanner.nextInt();
            if (number < 1 || number > 4) {
                mistake = true;
                System.out.println("Введите число в диапазоне от 1 до 4 включительно.");
            }
        } while (mistake);
        System.out.println(function.showFunction(number));
        return number;
    }
}
