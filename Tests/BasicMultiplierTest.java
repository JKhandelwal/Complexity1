import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;







import static org.junit.jupiter.api.Assertions.*;

class BasicMultiplierTest {
    private int dimension;
    private double fraction;
    @BeforeEach
    void setUp() {
        dimension = 5;
        fraction = 0.850;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void multiply() {
    }

    @Test
    public void testMaking(){
        IntMatrix t =  new IntMatrix(GenerateMatrix.getMatrix(dimension,fraction));

        System.out.println(t.toString());

    }

    @Test
    public void testCSR(){
        IntMatrix t =  new IntMatrix(GenerateMatrix.getMatrix(4,fraction));
        System.out.println(t.toString());
        CSRObject c = CSRSparseMatrixMultiplication.makeCSR(t);
        System.out.println(c.toString());
        IntMatrix p = CSRSparseMatrixMultiplication.makeIntMatrix(c,t.getDim());
        System.out.println(p.toString());

        System.out.println(p.equals(t));

    }

    @Test
    public void doStuff(){
//        Stage stage = new Stage();
//        stage.setTitle("Line Chart Sample");
//        //defining the axes
//        final NumberAxis xAxis = new NumberAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("Number of Month");
//        //creating the chart
//        final LineChart<Number, Number> lineChart =
//                new LineChart<Number, Number>(xAxis, yAxis);
//
//        lineChart.setTitle("Stock Monitoring, 2010");
//        //defining a series
//        XYChart.Series series = new XYChart.Series();
//        series.setName("Basic");
//        XYChart.Series series2 = new XYChart.Series();
//        series2.setName("Faster");

        for (int i= 10;i < 1000;i+=100) {
            IntMatrix t = new IntMatrix(GenerateMatrix.getMatrix(i, fraction));
            IntMatrix e = new IntMatrix(GenerateMatrix.getMatrix(i, fraction));
            CSRObject o = CSRSparseMatrixMultiplication.makeCSR(t);

            BasicMultiplier b = new BasicMultiplier();
            long time1 = System.nanoTime();
            IntMatrix a = b.multiply(t, e);
            long time2 = System.nanoTime();
//            series.getData().add(new XYChart.Data(i, (time2 - time1)));
            System.out.println("Basic Multiply time " + (time2 - time1));

            time1 = System.nanoTime();
            IntMatrix mat = CSRSparseMatrixMultiplication.multiply(e, o);
            time2 = System.nanoTime();
            System.out.println("Better Multiply time " + (time2 - time1));
            System.out.println("\n\n");

            assert mat.equals(a);

            //populating the series with data

//            series2.getData().add(new XYChart.Data(i, (time2-time1)));


        }
//        Scene scene  = new Scene(lineChart,800,600);
//        lineChart.getData().add(series);
//
//        stage.setScene(scene);
//        stage.show();


        System.out.println("It works!");
    }


}