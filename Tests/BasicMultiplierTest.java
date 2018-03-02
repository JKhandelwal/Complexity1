import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

class BasicMultiplierTest {
    private int dimension;
    private double fraction;
    @BeforeEach
    void setUp() {
        dimension = 4;
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

//        for (int i= 10;i < 1000;i+=100) {
            IntMatrix t = new IntMatrix(GenerateMatrix.getMatrix(dimension, fraction));
            IntMatrix e = new IntMatrix(GenerateMatrix.getMatrix(dimension, fraction));
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

        System.out.println(mat.toString());
        System.out.println(a.toString());
//            assert mat.equals(a);

            //populating the series with data

//            series2.getData().add(new XYChart.Data(i, (time2-time1)));


//        }
//        Scene scene  = new Scene(lineChart,800,600);
//        lineChart.getData().add(series);
//
//        stage.setScene(scene);
//        stage.show();


        System.out.println("It works!");
    }


    @Test
    public void strassenTest() {
        IntMatrix t = new IntMatrix(GenerateMatrix.getMatrix(8, fraction));
        IntMatrix e = new IntMatrix(GenerateMatrix.getMatrix(8, fraction));

        BasicMultiplier m = new BasicMultiplier();

        IntMatrix res = m.multiply(t, e);
        System.out.println(res);

        IntMatrix strassen = Strassen.multiply(t, e);


        System.out.println(strassen);
        assert (res.equals(strassen));

    }

    @Test
    public void compareTest() throws IOException {
        try{
            File f = new File("Files/" + "ComparisonTimes" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ".csv");
            BufferedWriter br = new BufferedWriter(new FileWriter(f));


        for (int i =8;i < 3000;i*= 2) {
            System.out.println(i);
            long strassenTotal =0;
            long CSRTotal =0;
            long basicTotal =0;


           for (int j =0;j < 10;j++) {

               IntMatrix t = new IntMatrix(GenerateMatrix.getMatrix(i, fraction));
               IntMatrix e = new IntMatrix(GenerateMatrix.getMatrix(i, fraction));

               System.out.println("Started Basic " + j);
               BasicMultiplier m = new BasicMultiplier();
                long one = System.nanoTime();
               IntMatrix res = m.multiply(t, e);
               long two = System.nanoTime();
               basicTotal += (two-one);

               System.out.println("Started Strassen");
                one = System.nanoTime();
               IntMatrix strassen = Strassen.multiply(t, e);
                two = System.nanoTime();
               strassenTotal += (two-one);
               assert res.equals(strassen);

               System.out.println("Started CSR ");
               one = System.nanoTime();
               CSRObject o = CSRSparseMatrixMultiplication.makeCSR(t);
               IntMatrix csr = CSRSparseMatrixMultiplication.multiply(e, o);
               two = System.nanoTime();
               CSRTotal += (two-one);
               assert res.equals(csr);


           }

           br.write(i + "," + (basicTotal/10) + "," + (strassenTotal/10)+"," + (CSRTotal/10));
           br.newLine();

        }
        br.close();
        } catch(Exception e){
            System.out.println("Borked");
        }

    }


}