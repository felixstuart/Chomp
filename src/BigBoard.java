import java.util.Arrays;
import java.util.Objects;

public class BigBoard extends Board {

    int column4;
    int column5;
    int column6;
    int column7;
    int column8;
    int column9;
    int column10;

    public BigBoard(int column1, int column2, int column3,
                    int column4, int column5, int column6,
                    int column7, int column8, int column9, int column10) {
        super(column1, column2, column3);
        this.column4 = column4;
        this.column5 = column5;
        this.column6 = column6;
        this.column7 = column7;
        this.column8 = column8;
        this.column9 = column9;
        this.column10 = column10;
    }

    public BigBoard(int[] columns) {
        super(columns[0], columns[1], columns[2]);
        assert columns.length == 10 : "Array must have exactly 10 elements";
        this.column4 = columns[3];
        this.column5 = columns[4];
        this.column6 = columns[5];
        this.column7 = columns[6];
        this.column8 = columns[7];
        this.column9 = columns[8];
        this.column10 = columns[9];
    }


    public int[] asArray() {
        return new int[]{
                column1, column2, column3, column4, column5, column6, column7, column8, column9, column10
        };
    }


    //    get max column
    public int getMaxColumn() {
        int maxindex = 0;
        int[] columns = this.asArray();
        for (int i = 0; i < columns.length; i++) {
            if (columns[i] > columns[maxindex]) {
                maxindex = i;
            }
        }
        return maxindex;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BigBoard that = (BigBoard) o;
        return this.asArray() == that.asArray();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), column4, column5, column6, column7, column8);
    }

    @Override
    public String toString() {
        return getColumn1() + " " + getColumn2() + " " + getColumn3() + " " +
                column4 + " " + column5 + " " + column6 + " " +
                column7 + " " + column8 + " " + column9 + " " +
                column10;
    }

}
