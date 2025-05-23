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

        Board root = new Board(3, 3, 3);

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

        printBoards();

        getWinLoseBoards();
        System.out.println(loseBoards);

//        getPossibleBoards(new BigBoard(columns));

        System.out.println(getPossibleBoards(new Board(3, 2, 1), 0));
        System.out.println(getPossibleBoards(new Board(3, 2, 1)));
        System.out.println(getPossibleBoards(new Board(3, 2, 1), 0) == getPossibleBoards(new Board(3, 2, 1)));


//        slice the columns to be a 3x3
        System.out.println(reduceColumns());
        System.out.println(getMove(reduceColumns()));
        return getMove(reduceColumns());
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
        for (int i = 10; i > 0; i--) {
            for (int j = 10; j >= 0; j--) {
                for (int k = 10; k >= 0; k--) {
                    for (int l = 10; l >= 0; l--) {
                        for (int m = 10; m >= 0; m--) {
                            for (int n = 10; n >= 0; n--) {
                                for (int o = 10; o >= 0; o--) {
                                    for (int p = 10; p >= 0; p--) {
                                        for (int q = 10; q >= 0; q--) {
                                            for (int r = 10; r >= 0; r--) {
                                                if (i >= j && j >= k && k >= l && l >= m && m >= n && n >= o && o >= p && p >= q && q >= r) {

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

    public ArrayList<Board> getPossibleBoards(Board board, int useadifferentalgorithmlol) {
        int column1 = board.getColumn1();
        int column2 = board.getColumn2();
        int column3 = board.getColumn3();

        int[] columns = {
                board.getColumn1(),
                board.getColumn2(),
                board.getColumn3()
        };
        ArrayList<Board> possibleBoards = new ArrayList<>();
        int col1 = column1;
        int col2 = column2;
        int col3 = column3;
        int[] copycols = columns.clone();

        boolean[] resetcols = new boolean[3];

        for (int i = 2; i >= 1; i--) {
            for (int j = 1; j <= columns[i]; j++) {
                columns[i] -= j;
            }

            if (columns[i] < 3) {
                if (i < 2 && columns[i + 1] != 0) {
                    columns[i + 1] = columns[i];
                    resetcols[i] = true;
                }

                if (i == 0) {
                    if (columns[1] < columns[2]) columns[2] = columns[1];
                    if (copycols[1] == 0) columns[1] = 0;
                    if (copycols[2] == 0) columns[2] = 0;

                }
            }


            possibleBoards.add(new Board(columns[0], columns[1], columns[2]));

            for (int j = 0; j < resetcols.length; j++) {
                if (resetcols[j]) {
                    columns[j] = copycols[j];
                }
            }
        }

        return possibleBoards;
    }
    public ArrayList<BigBoard> getPossibleBoards(BigBoard board) {
        int[] columns = {
                board.getColumn1(),
                board.getColumn2(),
                board.getColumn3(),
                board.getColumn4(),
                board.getColumn5(),
                board.getColumn6(),
                board.getColumn7(),
                board.getColumn8(),
                board.getColumn9(),
                board.getColumn10()
        };

        ArrayList<BigBoard> possibleBoards = new ArrayList<>();

        int[] cols = columns;

        for (int i = 9; i >= 0; i--) {
            if (i == 9) {
                for (int j = 1; j < columns[i]; j++) {
                    columns[i] -= j;
                    possibleBoards.add(new BigBoard(columns));
                    columns[i] = cols[i];
                }
            } else {
                if (columns[i] < 10) {
//                    make every column to the right of the current one equal to the current one
                    for (int j = i; j < 9 - i; j++) {
                        System.out.println(j);
                    }
                }
            }
        }
//        System.out.println(possibleBoards);
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
        }
        if (winCount == derivatives.size()) {
            loseBoards.add(board);
        }
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

}

