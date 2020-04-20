package NonLinear;

class SolvingNonlinearEquations {
    private double accuracy;
    private double section_a;
    private double section_b;
    private Function func;
    private double root_chord = 0;
    private double root_iterate = 0;
    private int iteration_chord = 0;
    private int iteration_iterate = 0;
    private double[][] xy_mChord = new double[30000][2];
    private double[][] xy_mIter = new double[30000][2];


    SolvingNonlinearEquations(int func_num, double accuracy, double section_a, double section_b) {
        this.accuracy = accuracy;
        if (section_a > section_b) {
            this.section_a = section_b;
            this.section_b = section_a;
        } else {
            this.section_a = section_a;
            this.section_b = section_b;
        }
        func = new Function(func_num);
    }

    double[][] getXy_mChord() {
        return xy_mChord;
    }

    double[][] getXy_mIter() {
        return xy_mIter;
    }

    int getIteration_chord() {
        return iteration_chord;
    }

    int getIteration_iterate() {
        return iteration_iterate;
    }

    void methodChord() {
        double prev_root;
        double section_a = this.section_a;
        double section_b = this.section_b;
        root_chord = section_a;
        do {
            prev_root = root_chord;

            root_chord = ((section_a * func.calcFunc(section_b)) - (section_b * func.calcFunc(section_a)))
                    / (func.calcFunc(section_b) - func.calcFunc(section_a));

            xy_mChord[iteration_chord][0] = section_a;
            xy_mChord[iteration_chord][1] = func.calcFunc(section_a);
            xy_mChord[iteration_chord + 1][0] = section_b;
            xy_mChord[iteration_chord + 1][1] = func.calcFunc(section_b);

            iteration_chord += 2;

            if (func.checkSection(root_chord, section_a)) {
                section_b = root_chord;

            } else section_a = root_chord;
        } while (accuracyAssessment(root_chord, prev_root) && iteration_chord < 30000);
    }

    void methodIteration() {
        double prev_root;
        double x = section_b;
        root_iterate = (section_a + section_b) / 2;
        double lambda = func.lambda(section_a, section_b);
        do {
            prev_root = root_iterate;
            root_iterate = x + lambda * (func.calcFunc(x));

            xy_mIter[iteration_iterate][0] = x;
            xy_mIter[iteration_iterate][1] = func.calcFunc(x);
            xy_mIter[iteration_iterate + 1][0] = root_iterate;
            xy_mIter[iteration_iterate + 1][1] = func.calcFunc(root_iterate);

            x = root_iterate;

            iteration_iterate += 2;
        } while (accuracyAssessment(root_iterate, prev_root) && iteration_iterate < 30000);
    }

    private boolean accuracyAssessment(double root, double prev_root) {
        return !(Math.abs(root - prev_root) <= accuracy);
    }

    @Override
    public String toString() {
        return "Метод хорд:\n" +
                "       корень: " + root_chord + "  количество итераций: " + iteration_chord / 2 + "\n" +
                "F(x) = " + func.calcFunc(root_chord) + "\n" +
                "Метод итераций:\n" +
                "       корень: " + root_iterate + "  количество итераций: " + iteration_iterate / 2 + "\n" +
                "F(x) = " + func.calcFunc(root_iterate);
    }
}
