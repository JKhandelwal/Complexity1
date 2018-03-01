import java.util.ArrayList;

public class CSRObject {
    //Values
    private ArrayList<Integer> values;
    // row pointer
    private ArrayList<Integer> rowPtr;
    //col
    private ArrayList<Integer> col;


    public CSRObject() {
        this.values = new ArrayList<>();
        this.rowPtr = new ArrayList<>();
        this.col = new ArrayList<>();

    }

    public CSRObject(ArrayList<Integer> values, ArrayList<Integer> columnIndex, ArrayList<Integer> numberCount) {

        this.values = values;
        this.rowPtr = columnIndex;
        this.col = numberCount;
    }


    public int getValue(int index) { return this.values.get(index); }

    public int getRowPtr(int index) {
        return this.rowPtr.get(index);
    }

    public int getColumn(int index) {
        return this.col.get(index);
    }


    public void setRowPtr(int value){
            this.rowPtr.add(value);
    }

    public void setColumn(int value){
            this.col.add(value);
    }

    public void setValue(int value){ this.values.add(value); }

    public ArrayList<Integer> getValues() {
        return values;
    }

    public ArrayList<Integer> getColumnIndices() {
        return rowPtr;
    }

    public ArrayList<Integer> getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "CSRObject{\n" +
                "values=" + values +
                ",\n col=" + col +
                ",\n rowPtr=" + rowPtr +

                "\n}";
    }
}



