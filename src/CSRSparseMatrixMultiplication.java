
public class CSRSparseMatrixMultiplication {

    public static CSRObject makeCSR(IntMatrix matrix){
        CSRObject csr = new CSRObject();
        int lastColumnNumber =0;
        csr.setRowPtr(0);

        for (int row =0;row < matrix.getDim();row++){
            int count =0;
            for (int column = 0; column < matrix.getDim(); column++) {
                if (matrix.get(row,column) != 0){
                    csr.setValue(matrix.get(row,column));
                    csr.setColumn(column);
                    count++;
                }
            }

            lastColumnNumber += count;
            csr.setRowPtr(lastColumnNumber);
        }
        return csr;
    }


    public static IntMatrix multiply(IntMatrix m,CSRObject c){

        IntMatrix retMatrix = new IntMatrix(m.getDim());
        for (int column = 0; column < m.getDim(); column++) {
            int i=0;
            int k =0;

            for (k = 0; k < m.getDim(); k = k + 1)
                retMatrix.set(i,column,0);

            for (i = 0; i < m.getDim(); i = i + 1) {
                for (k = c.getRowPtr(i); k < c.getRowPtr(i + 1); k = k + 1) {
                    retMatrix.set(i,column,retMatrix.get(i,column) + c.getValue(k)*m.get(c.getColumn(k),column));
                }
            }
        }
        return retMatrix;
    }


    public static IntMatrix makeIntMatrix(CSRObject c, int dim){
        IntMatrix t = new IntMatrix(dim);

        int numToRead =0;
        int rowNum = 0;


        for (int numberInRow = 1; numberInRow < c.getCol().size()-1; numberInRow++) {
            int currval = c.getColumn(numberInRow);
            if ( currval != numToRead){

                for (int i =numToRead ; i < currval;i++){
                    t.set(rowNum,c.getRowPtr(i),c.getValue(i));
                }

                numToRead += currval;
            }
            rowNum++;
        }

        return t;
    }
}
