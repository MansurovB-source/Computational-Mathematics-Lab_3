package Graphics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

import java.awt.*;

public class DrawGraph extends ApplicationFrame {
    private int func_num;
    private double section_a1;
    private double section_b1;
    private double section_a2;
    private double section_b2;
    private double[][] xy;
    private int iteration;

    public DrawGraph(String title, int func_num, double a1, double b1, double a2, double b2) {
        super(title);
        this.func_num = func_num;
        this.section_a1 = a1;
        this.section_b1 = b1;
        this.section_a2 = a2;
        this.section_b2 = b2;
        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new Dimension(560, 480));
        setContentPane(chartPanel);
    }

    public DrawGraph(final String title, int func_num, double section_a, double section_b, double[][] xy, int iteration) {
        super(title);
        this.func_num = func_num;
        this.section_a1 = section_a;
        this.section_b1 = section_b;
        this.xy = xy;
        this.iteration = iteration;
        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new Dimension(560, 480));
        setContentPane(chartPanel);
    }

    private JFreeChart createChart() {
        final JFreeChart chart;

        if (getTitle().equals("График уравнений")) {
            chart = ChartFactory.createXYLineChart(
                    "Демонстрация графика",
                    "x_1",
                    "x_2",
                    null,
                    PlotOrientation.VERTICAL,
                    false,
                    false,
                    false
            );
        } else {
            chart = ChartFactory.createXYLineChart(
                    "Демонстрация графика",
                    "x",
                    "Значения функции в точке x",
                    null,
                    PlotOrientation.VERTICAL,
                    false,
                    false,
                    false
            );
        }
        chart.setBackgroundPaint(Color.WHITE);
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(232, 232, 232));

        //Цвет сетки графика
        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
//        plot.setRangeGridlinePaint(Color.BLACK);
//        plot.setDomainGridlinePaint(Color.BLACK);

        // Определение отступа меток делений
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));

        // Скрытие осевых линий и меток делений
        ValueAxis axis = plot.getDomainAxis();
        axis.setAxisLineVisible(false);

        //Настройка NumberAxis
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAxisLineVisible(true);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Настройка XYSplineRenderer
        // Precision: количество отрезков между двумя точками [по умолчанию: 5]
        XYSplineRenderer renderer = new XYSplineRenderer();
        renderer.setPrecision(80);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesPaint(0, Color.orange);
        renderer.setSeriesStroke(0, new BasicStroke(2.5f));


        //Набор данных
        if (getTitle().equals("График уравнений")) {
            XYDataset dataset = DataSet.createDataSetSystem(func_num, 1, section_a1, section_b1, section_a2, section_b2);
            XYDataset dataset1 = DataSet.createDataSetSystem(func_num, 2, section_a1, section_b1, section_a2, section_b2);

            plot.setDataset(0, dataset1);
            plot.setDataset(1, dataset);

            plot.setRenderer(1, renderer);
//            plot.setRenderer(0, renderer);

        } else {
            XYDataset dataset = DataSet.createDataset(getTitle(), func_num, section_a1, section_b1, xy, iteration);
            plot.setDataset(dataset);
            plot.setRenderer(renderer);
        }

        return chart;
    }
}
