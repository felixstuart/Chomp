import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

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
        printBoards();
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
        ArrayList<Integer> prettyBoard = new ArrayList<Integer>();
        int[][] board = new int[3][3];
        prettyBoard.add(3);
        prettyBoard.add(3);
        prettyBoard.add(3);

        for (int[] i : board) Arrays.fill(i, 1);

        System.out.println(prettyBoard);
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
//                remove everything right and above
                board[x][y] = 0;
//                prettify the board and print it
                for (int[] i : board) {
                    for (int j = 0; j < board[0].length; j++) {
                        if (i[j] != 1) break;
                        prettyBoard.set(j, prettyBoard.get(j)+1);
                    }
                }
            }
        }
    }


}
