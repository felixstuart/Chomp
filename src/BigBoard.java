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

    public int[] getAllColumns() {
        return new int[]{
                this.getColumn1(),
                this.getColumn2(),
                this.getColumn3(),
                this.getColumn4(),
                this.getColumn5(),
                this.getColumn6(),
                this.getColumn7(),
                this.getColumn8(),
                this.getColumn9(),
                this.getColumn10()
        };
    }

    public int getColumn4() {
        return column4;
    }

    public int getColumn5() {
        return column5;
    }

    public int getColumn6() {
        return column6;
    }

    public int getColumn7() {
        return column7;
    }

    public int getColumn8() {
        return column8;
    }

    public int getColumn9() {
        return column9;
    }

    public int getColumn10() {
        return column10;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BigBoard bigBoard = (BigBoard) o;
        return getColumn4() == bigBoard.getColumn4() && getColumn5() == bigBoard.getColumn5() && getColumn6() == bigBoard.getColumn6() && getColumn7() == bigBoard.getColumn7() && getColumn8() == bigBoard.getColumn8() && getColumn9() == bigBoard.getColumn9() && getColumn10() == bigBoard.getColumn10();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getColumn4(), getColumn5(), getColumn6(), getColumn7(), getColumn8(), getColumn9(), getColumn10());
    }

    @Override
    public String toString() {
        return getColumn1() + " " + getColumn2() + " " + getColumn3() + " " +
                getColumn4() + " " + getColumn5() + " " + getColumn6() + " " +
                getColumn7() + " " + getColumn8() + " " + getColumn9() + " " +
                getColumn10();
    }

}
