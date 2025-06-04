import java.util.Objects;

public class Board {
    public final int column1;
    public final int column2;
    public final int column3;


    public int columncollectionid;

    public final int[] columns;

    public Board(int column1, int column2, int column3) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;

        columns = new int[]{column1, column2, column3};

        for (int column : columns) {
            columncollectionid = columncollectionid + 10 * column;
        }


    }

    public int getColumn1() {
        return column1;
    }

    public int getColumn2() {
        return column2;
    }

    public int getColumn3() {
        return column3;
    }


    @Override
    public String toString() {
        return this.column1 + " " + this.column2 + " " + this.column3;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Board that = (Board) o;
        return column1 == that.column1 && column2 == that.column2 && column3 == that.column3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column1, column2, column3);
    }
}
