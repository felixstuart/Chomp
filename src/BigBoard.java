import java.util.Arrays;
import java.util.Objects;

public class BigBoard extends Board {

    public final int column4;
    public final int column5;
    public final int column6;
    public final int column7;
    public final int column8;
    public final int column9;
    public final int column10;

    public final int hashCode;

    public final int[] asArray;

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

        asArray = Arrays.copyOf(columns, columns.length);

        hashCode = hashCode();
    }


    //    get max column
    public int getMaxColumn() {
        int maxindex = 0;
        int max = 0;

        for (int i = 0; i < asArray.length; i++) {
            if (asArray[i] > max) {
                maxindex = i;
                max = asArray[i];
            }
        }
        return maxindex;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BigBoard that = (BigBoard) o;
        return hashCode == that.hashCode;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(asArray);
    }

    @Override
    public String toString() {
        return getColumn1() + " " + getColumn2() + " " + getColumn3() + " " +
                column4 + " " + column5 + " " + column6 + " " +
                column7 + " " + column8 + " " + column9 + " " +
                column10;
    }

}
