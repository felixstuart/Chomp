import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;

    public MyPlayer() {
        columns = new int[10];

        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */
    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer Move");

        gameBoard = pBoard;
        int column = 0;
        int row = 0;

        row = 1;
        column = 1;

        /*
          This code will run each time the "MyPlayer" button is pressed.
          Add your code to return the row and the column of the chip you want to take.
          You'll be returning a data type called Point which consists of two integers.
         */
        toColumns();
//        print all the different possible boards in one go
//        printBoards();
        printPossibleBoards(3, 3, 3);
        System.out.println(isBoardWinning(new Board(2, 0, 0)));
        Point myMove = new Point(row, column);
        System.out.println(Arrays.toString(columns));
        return myMove;
    }

    public void toColumns() {
        Arrays.fill(columns, 0);
        for (Chip[] chips : gameBoard) {
            for (int column = 0; column < gameBoard[0].length; column++) {
                if (!chips[column].isAlive) break;
                columns[column] += 1;
            }
        }
    }

    public void printBoards() {
        for (int i = 3; i > 0; i--) {
            for (int j = 3; j >= 0; j--) {
                for (int k = 3; k >= 0; k--) {
                    if (i >= j && j >= k) {
                        System.out.println(i + " " + j + " " + k);
                    }
                }
            }
        }
    }

    public void printPossibleBoards(int column1, int column2, int column3) {
        System.out.println(column1 + " " + column2 + " " + column3);
        System.out.println("--------------------------");
        int col1 = column1;
        int col2 = column2;
        int col3 = column3;
        for (int i = 1; i <= column3; i++) {
            column3 -= i;
            System.out.println(column1 + " " + column2 + " " + column3);
            column3 = col3;
        }
        for (int i = 1; i <= column2; i++) {
            column2 -= i;
            if (column2 < 3) {
                column3 = column2;
            }
            System.out.println(column1 + " " + column2 + " " + column3);
            column2 = col2;
        }
        column3 = col3;
        for (int i = 1; i < column1; i++) {
            column1 -= i;
            if (column1 < 3) {
                column2 = column1;
            }
            if (column2 < 3) {
                column3 = column2;
            }
            System.out.println(column1 + " " + column2 + " " + column3);
            column1 = col1;
        }
    }

    public ArrayList<Board> getPossibleBoards(int column1, int column2, int column3) {
        ArrayList<Board> possibleBoards = new ArrayList<>();
        int col1 = column1;
        int col2 = column2;
        int col3 = column3;
        for (int i = 1; i <= column3; i++) {
            column3 -= i;
            possibleBoards.add(new Board(column1, column2, column3));
            column3 = col3;
        }
        for (int i = 1; i <= column2; i++) {
            column2 -= i;
            if (column2 < 3) {
                column3 = column2;
            }
            possibleBoards.add(new Board(column1, column2, column3));
            column2 = col2;
        }
        column3 = col3;
        for (int i = 1; i < column1; i++) {
            column1 -= i;
            if (column1 < 3) {
                column2 = column1;
            }
            if (column2 < 3) {
                column3 = column2;
            }
            possibleBoards.add(new Board(column1, column2, column3));
            column1 = col1;
        }
        return possibleBoards;
    }

    public boolean isBoardWinning(Board board) {
        Board rootWin = new Board(1, 0, 0);

        int winningBoards = 0;
        if (board.equals(rootWin)) return true;

        ArrayList<Board> descendants = getPossibleBoards(board.getColumn1(), board.getColumn2(), board.getColumn3());

        if (descendants.contains(rootWin)) {
            return true;
        } else {
            for (Board descendant : descendants) {
                if (isBoardWinning(descendant)) {
                    winningBoards++;
                }
            }
        }
        return winningBoards > 0 && winningBoards < descendants.size();
    }
}
