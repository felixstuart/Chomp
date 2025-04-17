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
        printBoards(3,2,1);
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

    public void printBoards(int column1, int column2, int column3) {
        System.out.println(column1 + " " + column2 + " " + column3);
        System.out.println("--------------------------");
        for (int i = column1; i > 0; i--) {
            for (int j = column2; j >= 0; j--) {
                for (int k = column3; k >= 0; k--) {
                    if(i == column1 && k == column2 && j == column3) {
                        continue;
                    }
                    if (i >= j && j >= k) {
                        if (i == 2) {
                            if (i == j && j == k) {
                                System.out.println(i+" "+j+" "+k);
                                break;
                            }
                        }
                        if (j == k) {
                            System.out.println(i+" "+j+" "+k);
                        }
                    }
                }
            }
        }
    }

}
