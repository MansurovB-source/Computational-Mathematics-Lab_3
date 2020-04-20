package Graphics;

import NonLinear.Function;
import SystemNonLInear.SystemFunction;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


@SuppressWarnings("Duplicates")
class DataSet {
    static XYDataset createDataset(String title, int func_number, double section_a, double section_b, double[][] xy, int iteration) {
        Function function = new Function(func_number);
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries func_series = new XYSeries("func_series");
        for (double i = (section_a - 2); i <= (section_b + 2); i++) {
            func_series.add(i, function.calcFunc(i));
        }
        dataset.addSeries(func_series);
        XYSeries series;
        if (title.equals("Метод Хорд")) {
            for (int i = 0; i < iteration; i += 2) {
                series = new XYSeries("series " + (i + 1));
                series.add(xy[i][0], xy[i][1]);
                series.add(xy[i + 1][0], xy[i + 1][1]);
                dataset.addSeries(series);
            }
        } else {
            if (iteration == 2) {
                series = new XYSeries("series_iterate ");
                series.add(xy[0][0], xy[0][1]);
                series.add(xy[iteration - 1][0], xy[iteration - 1][1]);
                dataset.addSeries(series);
            } else {
                series = new XYSeries("series_iterate ");
                series.add(xy[1][0], xy[1][1]);
                series.add(xy[iteration - 1][0], xy[iteration - 1][1]);
                dataset.addSeries(series);
            }
        }
        return dataset;
    }

    static XYDataset createDataSetSystem(int func_num, int num, double a1, double b1, double a2, double b2) {

        SystemFunction systemFunction = new SystemFunction(func_num);
        XYSeriesCollection dataset = new XYSeriesCollection();
        if(num == 1) {
            XYSeries func_1 = new XYSeries("system_func_1");
            for (double i = a1; i <= b1; i += 0.05) {
                double l = systemFunction.funcForGraphic(1, i);
                func_1.add(i, systemFunction.funcForGraphic(1, i));
            }
            dataset.addSeries(func_1);
        } else {
            XYSeries func_2 = new XYSeries("system_func_2");
            for (double i = a2; i <= b2; i += 0.001) {
                func_2.add(i, systemFunction.funcForGraphic(2, i));
            }
            dataset.addSeries(func_2);

            if (func_num == 1) {
                if (func_num == 4) {
                    XYSeries func_2_1 = new XYSeries("system_func_2_1");
                    for (double i = a2; i <= b2; i += 0.001) {
                        func_2_1.add((i), -(systemFunction.funcForGraphic(2, i) - 4));
                    }
                    dataset.addSeries(func_2_1);
                } else {
                    XYSeries func_2_1 = new XYSeries("system_func_2_1");
                    for (double i = a2; i <= b2; i += 0.001) {
                        func_2_1.add((i), -(systemFunction.funcForGraphic(2, i)));
                    }
                    dataset.addSeries(func_2_1);
                }
            }
        }
        return dataset;
    }
}

// 0.5 0.05 10
// 0.61 0.1 10

// 1 - 4