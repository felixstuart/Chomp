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
        ArrayList<Integer> board = new ArrayList<Integer>();
        board.add(1);
        board.add(0);
        board.add(0);

//        fill the boards, starting from 1,0,0
//        this will produce 9; 10 are still missing...
        for (int columns = 1; columns < 4; columns++) {
            for (int rows = 1; rows < 4; rows++) {
                if(columns + 1 < 3) {
                    if (board.get(columns) > board.get(columns-1)){
                       board.set(columns, 0);
                    }
                }
                board.set(columns-1, rows);
                System.out.println(board);
            }
        }
        for (int rows = 1; rows < 4; rows++) {
            for (int columns = 1; columns < 4; columns++) {
                board.set(columns-1, rows);
                if (board.get(0) < board.get(1)) {
                    board.set(1, 0);
                }
                if (board.get(1) < board.get(2)) {
                    board.set(2,0);
                }
                System.out.println(board);
            }
        }


    }


}
