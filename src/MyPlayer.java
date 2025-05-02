import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;

    public ArrayList<Board> loseBoards;
    public ArrayList<Board> winBoards;

    public MyPlayer() {
        columns = new int[10];

        loseBoards = new ArrayList<>();
        winBoards = new ArrayList<>();

        Board root = new Board(1, 0, 0);

        loseBoards.add(root);
        /*
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
//        printPossibleBoards(3, 3, 3);
//        getMove(new Board(3, 3, 3));
        isBoardWinning(new Board(2, 2, 0));
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
        for (Board descendant : getPossibleBoards(board.getColumn1(), board.getColumn2(), board.getColumn3())) {
            if (loseBoards.contains(descendant)) {
                System.out.println("yay!");
                winBoards.add(descendant);
                return true;
            }
        }
        return false;
    }

    public void getMove(Board board) {
//        a win board is any board with one winning option
//        -- we already have the isBoardWinning, so we need to calculate through the given board and just return the board that's winning.
        ArrayList<Board> descendants = getPossibleBoards(board.getColumn1(), board.getColumn2(), board.getColumn3());
        int[] bestMove = new int[2];
        for (Board descendant : descendants) {
            if (isBoardWinning(descendant)) {
//                figure out how to convert the new board into coordinates
                System.out.println(descendant.getColumn1() + " " + descendant.getColumn2() + " " + descendant.getColumn3());
//                return bestMove;
            }
        }
//        return null;
    }
}