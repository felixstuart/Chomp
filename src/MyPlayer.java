import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        int column = 1;
        int row = 1;

        /*
          This code will run each time the "MyPlayer" button is pressed.
          Add your code to return the row and the column of the chip you want to take.
          You'll be returning a data type called Point which consists of two integers.
         */
        toColumns();

        System.out.println();
        getWinLoseBoards();
        System.out.println(winBoards);
        System.out.println(loseBoards);


        System.out.println(getMove(new Board(3, 3, 3)));
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


    public ArrayList<Board> getPossibleBoards(Board board) {
        int column1 = board.getColumn1();
        int column2 = board.getColumn2();
        int column3 = board.getColumn3();
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
                if (column3 != 0) {
                    column3 = column2;
                }
            }
            possibleBoards.add(new Board(column1, column2, column3));
            column2 = col2;
        }
        column3 = col3;
        for (int i = 1; i < column1; i++) {
            column1 -= i;
            if (column1 < 3) {
                if (column1 < column2) {
                    column2 = column1;
                }
            }
            if (column2 < 3) {
                column3 = column2;
            }
            if (col3 == 0) {
                column3 = 0;
            }
            if (col2 == 0) {
                column2 = 0;
            }
            possibleBoards.add(new Board(column1, column2, column3));
            column1 = col1;
        }
        return possibleBoards;
    }



    public void BoardWins(Board board) {
//        first, we need to get the derivatives of the board.
//        if the board has all win boards, it's a lose board.
//        if the board has at least one lose board, it's a win board.
//        probably the only issue is that we only start with one
//        lose board. the trick to fix this is to add more.
//        the best way to do this is going to be to find them.
//        start by going from corner to corner and looking for them 😭
        ArrayList<Board> derivatives = getPossibleBoards(board);

        int winCount = 0;
        int loseCount = 0;
        for (Board derivative : derivatives) {
            if (loseBoards.contains(derivative)) {
                loseCount++;
            }
            if (winBoards.contains(derivative)) {
                winCount++;
            }
        }
//        if the count is equal to the number of possible boards,
//        the board we have is a lose board. However, if the count
//        is less then we have a win board!

        if (loseCount <= derivatives.size() && loseCount > 0) {
            winBoards.add(board);
            System.out.println("board " + board + " is a win board.");
        }
        if (winCount == derivatives.size()) {
            loseBoards.add(board);
            System.out.println("board " + board + " is a lose board.");
        }
        System.out.println(winCount);
        System.out.println(loseCount);
    }

    public void getWinLoseBoards() {
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j <= 3; j++) {
                for (int k = 0; k <= 3; k++) {
                    if (i >= j && j >= k) {
                        BoardWins(new Board(i, j, k));
                    }
                }
            }
        }
    }


    public Point getMove(Board board) {

        Point coordinates = new Point();

//        find the "target" board. The target
//        board is the board that is a lose board
        ArrayList<Board> descendants = getPossibleBoards(board);

        for (Board descendant : descendants) {
            if (loseBoards.contains(descendant)) {
                System.out.println(descendant);
            }
        }

        return coordinates;
    }

}

