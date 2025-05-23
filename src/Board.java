import java.util.Objects;

public class Board {
    private final int column1;
    private final int column2;
    private final int column3;

    public Board(int column1, int column2, int column3) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;

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
        return this.getColumn1() + " " + this.getColumn2() + " " + this.getColumn3();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Board that = (Board) o;
        return getColumn1() == that.getColumn1() && getColumn2() == that.getColumn2() && getColumn3() == that.getColumn3();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn1(), getColumn2(), getColumn3());
    }
}
