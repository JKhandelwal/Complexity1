/**
 * Created by jalajkhandelwal on 02/03/2018.
 */
public class Strassen {

    public static IntMatrix multiply(IntMatrix a,IntMatrix b){


        IntMatrix retMatrix = new IntMatrix(a.getDim());
        if (a.getDim() == 1)
            retMatrix.set(0,0,a.get(0,0)*b.get(0,0));
        else {

            IntMatrix A11 = a.getSlice(0, 0, a.getDim() / 2);
            IntMatrix A21 = a.getSlice(a.getDim() / 2, 0, a.getDim() / 2);
            IntMatrix A12 = a.getSlice(0, a.getDim() / 2, a.getDim() / 2);
            IntMatrix A22 = a.getSlice(a.getDim() / 2, a.getDim() / 2, a.getDim() / 2);

            IntMatrix B11 = b.getSlice(0, 0, b.getDim() / 2);
            IntMatrix B21 = b.getSlice(b.getDim() / 2, 0, b.getDim() / 2);
            IntMatrix B12 = b.getSlice(0, b.getDim() / 2, b.getDim() / 2);
            IntMatrix B22 = b.getSlice(b.getDim() / 2, b.getDim() / 2, b.getDim() / 2);

            IntMatrix M1 = multiply(IntMatrix.add(A11, A22), IntMatrix.add(B11, B22));
            IntMatrix M2 = multiply(IntMatrix.add(A21, A22), B11);
            IntMatrix M3 = multiply(A11, IntMatrix.subtract(B12, B22));
            IntMatrix M4 = multiply(A22, IntMatrix.subtract(B21, B11));
            IntMatrix M5 = multiply(IntMatrix.add(A11, A12), B22);
            IntMatrix M6 = multiply(IntMatrix.subtract(A21, A11), IntMatrix.add(B11, B12));
            IntMatrix M7 = multiply(IntMatrix.subtract(A12, A22), IntMatrix.add(B21, B22));

            IntMatrix C11 = IntMatrix.add(IntMatrix.subtract(IntMatrix.add(M1, M4), M5), M7);
            IntMatrix C12 = IntMatrix.add(M3, M5);
            IntMatrix C21 = IntMatrix.add(M2, M4);
            IntMatrix C22 = IntMatrix.add(IntMatrix.subtract(IntMatrix.add(M1, M3), M2), M6);

            retMatrix.setSlice(0,0,C11);
            retMatrix.setSlice(0,retMatrix.getDim()/2,C12);
            retMatrix.setSlice(retMatrix.getDim()/2,0,C21);
            retMatrix.setSlice(retMatrix.getDim()/2,retMatrix.getDim()/2,C22);

        }

        return retMatrix;
    }


}
