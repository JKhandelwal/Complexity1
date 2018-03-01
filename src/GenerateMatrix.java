/**
 * Created by jalajkhandelwal on 27/02/2018.
 */

import java.util.Random;
public class GenerateMatrix {

    public static int[][] getMatrix(int dim,double fraction){
        int [][] mat = new int[dim][dim];
        double temporary =0.0;
        Random r = new Random();

        for (int row = 0; row < dim; row++) {
            for (int column = 0; column < dim; column++) {
                temporary = Math.random();
                if (temporary > fraction){
                    mat[row][column] = r.nextInt(100);
                }
            }
        }

        return mat;
    }
}




