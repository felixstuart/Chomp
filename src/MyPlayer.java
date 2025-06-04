import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;

    public ArrayList<Board> loseBoards;
    public ArrayList<Board> winBoards;

    public ArrayList<BigBoard> loseBigBoards;
    public ArrayList<BigBoard> winBigBoards;

    public MyPlayer() {
        columns = new int[10];

        loseBoards = new ArrayList<>();
        winBoards = new ArrayList<>();

        loseBigBoards = new ArrayList<>();
        winBigBoards = new ArrayList<>();

        Board root = new Board(1, 0, 0);

        BigBoard bigRoot = BigBoardHelpers.ROOT_BOARD;

        loseBoards.add(root);
        loseBigBoards.add(bigRoot);

        getWinLoseBigBoards();
    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer Move");

        gameBoard = pBoard;

        /*
          This code will run each time the "MyPlayer" button is pressed.
          Add your code to return the row and the column of the chip you want to take.
          You'll be returning a data type called Point which consists of two integers.
         */
        toColumns();

        return getMove(new BigBoard(columns));
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

    public Board reduceColumns() {
        Board board;

        int[] firstThree = new int[3];

        System.arraycopy(columns, 0, firstThree, 0, 3);

//        ok, now take the values and if they're greater
//        than 3, make them three. if they are less than 3,
//        leave them.

        for (int i = 0; i < 3; i++) {
            if (firstThree[i] > 3) {
                firstThree[i] = 3;
            }
        }

        board = new Board(firstThree[0], firstThree[1], firstThree[2]);

        return board;
    }

    public void printBoards() {
        for (int i = 3; i > 0; i--) {
            for (int j = i; j >= 0; j--) {
                for (int k = j; k >= 0; k--) {
                    System.out.println(new Board(i, j, k));
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
    
    public ArrayList<BigBoard> getPossibleBoards(BigBoard board) {
        int[] columns = board.asArray;

        ArrayList<BigBoard> possibleBoards = new ArrayList<>();

        int[] copycols = columns.clone();

        for (int i = 9; i >= 0; i--) {
            for (int j = 1; j <= copycols[i]; j++) {
                columns[i] -= j;
//                check that all columns are descending in value
//                also, make sure column[0] equals at least 1
                boolean[] resetcols = new boolean[10];
                for (int k = 1; k < columns.length; k++) {
                    if (columns[k] > columns[k - 1]) {
                        if (columns[k] < copycols[k]) {
                            columns[k] = copycols[k];
                            resetcols[k] = true;
                        } else {
                            columns[k] = columns[k - 1];
                            resetcols[k - 1] = true;
                            resetcols[k] = true;
                        }
                    }
                }
                if (columns[0] > 0) {
                    possibleBoards.add(new BigBoard(columns));
                }

                for (int k = 0; k < resetcols.length; k++) {
                    if (resetcols[k]) {
                        columns[k] = copycols[k];
                    }
                }
                columns[i] = copycols[i];
            }
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
//        start by going from corner to corner and looking for them
        ArrayList<Board> derivatives = getPossibleBoards(board);

        int winCount = 0;
        for (Board derivative : derivatives) {
            if (loseBoards.contains(derivative)) {
                winBoards.add(0, board);
                return;
            }
            if (winBoards.contains(derivative)) {
                winCount++;
            }
        }
//        if the count is equal to the number of possible boards,
//        the board we have is a lose board. However, if the count
//        is less then we have a win board!
        if (winCount == derivatives.size()) {
            loseBoards.add(0, board);
        }
    }

    public void BoardWins(BigBoard board) {
        ArrayList<BigBoard> derivatives = getPossibleBoards(board);

        int winCount = 0;
        for (BigBoard derivative : derivatives) {
            if (loseBigBoards.contains(derivative)) {
                winBigBoards.add(0, board);
                return;
            }
            if (winBigBoards.contains(derivative)) {
                winCount++;
            }
        }
//        if the count is equal to the number of possible boards,
//        the board we have is a lose board. However, if the count
//        is less then we have a win board!
        if (winCount == derivatives.size()) {
            loseBigBoards.add(0, board);
        }
    }

    public void getWinLoseBoards() {
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k <= j; k++) {
                    BoardWins(new Board(i, j, k));
                }
            }
        }
    }

    public void getWinLoseBigBoards() {
        for (int i = 1; i < 11; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k <= j; k++) {
                    for (int l = 0; l <= k; l++) {
                        for (int m = 0; m <= l; m++) {
                            for (int n = 0; n <= m; n++) {
                                for (int o = 0; o <= n; o++) {
                                    for (int p = 0; p <= o; p++) {
                                        for (int q = 0; q <= p; q++) {
                                            for (int r = 0; r <= q; r++) {
                                                BoardWins(new BigBoard(new int[]{i, j, k, l, m, n, o, p, q, r}));
                                            }
                                        }
                                    }
                                }
                            }
                        }
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

        if (!board.equals(new Board(1, 0, 0))) {
//            check if 1,0 is a valid coordinate
            if (board.getColumn2() >= 1) {
                coordinates.setLocation(1, 0);
            } else if (board.getColumn1() >= 2) {
                coordinates.setLocation(0, 1);
            }


        }
        for (Board descendant : descendants) {
            if (loseBoards.contains(descendant)) {
                System.out.println(descendant);
//                figure out how to get to the target from the current board
//                the x coordinate can be figured out by looking at how "far"
//                out the board has been untouched. i.e. for board 3,1,1, the
//                x coordinate must be 1. to figure out the y-coordinate, can
//                compare the columns vs the original board
                int y = 0;
                int x = 0;
                if (descendant.getColumn2() < board.getColumn2()) {
                    y = 1;
                } else if (descendant.getColumn3() < board.getColumn3()) {
                    y = 2;
                }

                x = switch (y) {
                    case 0 -> descendant.getColumn1();
                    case 1 -> descendant.getColumn2();
                    case 2 -> descendant.getColumn3();
                    default -> x;
                };

                coordinates.setLocation(x, y);
                return coordinates;
            }
        }

        System.out.println("death board");


        return coordinates;
    }

    public Point getMove(BigBoard board) {
        Point coordinates = new Point();

        ArrayList<BigBoard> descendants = getPossibleBoards(board);

        if (!board.equals(BigBoardHelpers.ROOT_BOARD)) {
//            check if 1,0 is a valid coordinate
            if (board.getColumn2() >= 1) {
                coordinates.setLocation(1, 0);
            } else if (board.getColumn1() >= 2) {
                coordinates.setLocation(0, 1);
            }
        }

        for (BigBoard descendant : descendants) {
            if (loseBigBoards.contains(descendant)) {
                System.out.println(descendant);
                int y = 0;
                for (int i = 0; i < 10; i++) {
                    if (descendant.asArray[i] < board.asArray[i]) {
                        y = i;
                        break;
                    }
                }
                int x = descendant.asArray[y];
                coordinates.setLocation(x, y);
                return coordinates;
            }
        }

        System.out.println("death board");

        return coordinates;
    }
}

