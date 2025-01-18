package tripods;

import sort.QuickSort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A program that finds the optimal placement of a number of tripods in an
 * N * M grid of numbers.  A tripod can touch three adjacent cells, based on orientation,
 * e.g. a north facing tripod touches the east, south and west cells.
 * <p>
 * The goal is to find the placement of a number of tripods, such that the
 * total sums of the cells that all combined tripods touch is maximum.
 *
 * @author RIT CS
 * @author Knei
 */
public class Tripods {
    /**
     * there are 4 corners where a tripod cannot be placed in any direction
     */
    public final static int CORNERS = 4;
    /**
     * when the number of cells exceeds this number it won't be displayed to standard output
     */
    public final static int MAX_CELLS_TO_DISPLAY = 10000;

    /**
     * Read the 2-D number grid into a 2 dimension native array. This method should
     * print the following to standard output:
     * <p>
     * Rows: #, Columns: #
     *
     * @param filename the file with the number grid
     * @return the newly created 2-D native array of numbers
     * @throws IOException if the file cannot be found or there is an error reading
     */
    public static int[][] loadGrid(String filename) throws IOException {
        File file = new File(filename);
        if ( !file.exists() ) {
            file = new File(".\\data\\" + filename);
        }

        Scanner dataFile = new Scanner(file);

        int[][] grid = new int[0][0];
        int index = -1;

        while ( dataFile.hasNextLine() ) {
            String line = dataFile.nextLine();
            String[] tokens = line.split(" ");

            if ( index == -1 ) {
                grid = new int[Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])];
                System.out.println("Rows: " + tokens[0] + ", Columns: " + tokens[1]);
            } else {
                for ( int i = 0; i < tokens.length; i++ ) {
                    grid[index][i] = Integer.parseInt(tokens[i]);
                }
            }

            index++;
        }

        return grid;
    }

    /**
     * Get the number of rows in the grid.
     *
     * @param grid the 2-D grid of numbers
     * @return number of rows
     */
    public static int getNumRows(int[][] grid) {
        return grid.length;
    }

    /**
     * Get the number of columns in the grid.
     *
     * @param grid the 2-D grid of numbers
     * @return number of columns
     */
    public static int getNumColumns(int[][] grid) {
        return grid[0].length;
    }

    /**
     * Get the maximum number of tripods that could be placed on the board.
     * A tripod cannot be placed in any of the four corners of the grid in
     * any orientation.
     *
     * @param grid the 2-D grid of numbers
     * @return the maximum number of tripods that can placed in the grid
     */
    public static int getMaxTripods(int[][] grid) {
        int row = Tripods.getNumRows(grid);
        int col = Tripods.getNumColumns(grid);

        return row * col - Tripods.CORNERS;
    }

    /**
     * Display the grid to standard output, only if the number of cells is less
     * than or equal to MAX_CELLS_TO_DISPLAY.  If that size exceeds, print
     * "Too many cells to display" instead.  For example with tripods-3.txt:
     * <p>
     * 0 3 7 9
     * 2 5 1 4
     * 3 3 2 1
     * 4 6 8 4
     * 4 1 2 0
     *
     * @param grid the 2-D grid of numbers
     */
    public static void displayGrid(int[][] grid) {
        int row = Tripods.getNumRows(grid);
        int column = Tripods.getNumColumns(grid);

        if ( row * column > Tripods.MAX_CELLS_TO_DISPLAY ) {
            System.out.println("Too many cells to display");
            return;
        }

        for ( int i = 0; i < row; i++ ) {
            StringBuilder toPrint = new StringBuilder();
            for ( int j = 0; j < column; j++ ) {
                toPrint.append(grid[i][j]).append(" ");
            }
            System.out.println(toPrint.substring(0, toPrint.length() - 1));
        }
    }

    /**
     * A spot is valid if the tripod can be placed in the cell at (row, col),
     * taking into account the direction of the tripod and the location of its
     * three legs.
     *
     * @param grid the 2-D grid of numbers
     * @param row  tripod's row (centered)
     * @param col  tripod's column (centered)
     * @param dir  the direction the tripod is facing
     * @return whether the tripod can be placed in the spot or not
     */
    public static boolean isSpotValid(int[][] grid, int row, int col, Direction dir) {
        int rowMax = Tripods.getNumRows(grid);
        int colMax = Tripods.getNumColumns(grid);

        // N | r - 1; c - 1; c + 1;
        if ( dir == Direction.NORTH ) {
            return (row - 1 >= 0) && (col - 1 >= 0) && (col + 1 < colMax);
        }
        // W | r - 1; r + 1; c - 1;
        if ( dir == Direction.WEST ) {
            return (row - 1 >= 0) && (row + 1 < rowMax) && (col - 1 >= 0);
        }
        // E | r - 1; r + 1; c + 1;
        if ( dir == Direction.EAST ) {
            return (row - 1 >= 0) && (row + 1 < rowMax) && (col + 1 < colMax);
        }
        // S | r + 1; c - 1; c + 1;
        if ( dir == Direction.SOUTH ) {
            return (row + 1 < rowMax) && (col - 1 >= 0) && (col + 1 < colMax);
        }

        // It should never get here... But I bet someone will find a way... By cheating ofc
        return false;
    }

    /**
     * Given a tripod at a location in the grid and facing a certain direction,
     * sum the numbers in the grid that the three legs of the tripod stand on.
     *
     * @param grid the 2-D grid of numbers
     * @param row  tripod's row (centered)
     * @param col  tripod's column (centered)
     * @param dir  the direction the tripod is facing
     * @return the sum of the tripods legs
     * @rit.pre spot must be valid
     */
    public static int getSum(int[][] grid, int row, int col, Direction dir) {
        if ( !Tripods.isSpotValid(grid, row, col, dir) ) {
            return 0;
        }

        // N | r - 1; c - 1; c + 1;
        if ( dir == Direction.NORTH ) {
            return grid[row - 1][col] + grid[row][col - 1] + grid[row][col + 1];
        }
        // W | r - 1; r + 1; c - 1;
        if ( dir == Direction.WEST ) {
            return grid[row - 1][col] + grid[row + 1][col] + grid[row][col - 1];
        }
        // E | r - 1; r + 1; c + 1;
        if ( dir == Direction.EAST ) {
            return grid[row - 1][col] + grid[row + 1][col] + grid[row][col + 1];
        }
        // S | r + 1; c - 1; c + 1;
        if ( dir == Direction.SOUTH ) {
            return grid[row + 1][col] + grid[row][col - 1] + grid[row][col + 1];
        }

        // Safety net is what I best call this
        return 0;
    }

    /**
     * Generate all possible locations and directions that a tripod can validly be placed into the grid.
     *
     * @param grid the 2-D grid of numbers
     * @return the full collection of valid tripod locations
     */
    public static ArrayList<Tripod> generateSums(int[][] grid) {
        int rowMax = Tripods.getNumRows(grid);
        int colMax = Tripods.getNumColumns(grid);

        ArrayList<Tripod> sums = new ArrayList<>();

        for ( int i = 0; i < rowMax; i++ ) {
            for ( int j = 0; j < colMax; j++ ) {
                // Base value, so comparison can be made with lower if statements, in case Direction.NORTH is invalid
                Tripod toAdd =  new Tripod(0, 0, Direction.NORTH, 0);

                for ( Direction d : Direction.values() ) {
                    if ( Tripods.isSpotValid(grid, i, j, d) ) {
                        int sum = Tripods.getSum(grid, i, j, d);
                        if ( toAdd.sum() < sum ) {
                            toAdd = new Tripod(i, j, d, sum);
                        }
                    }
                }

                sums.add(toAdd);
            }
        }

        return sums;
    }

    /**
     * Assuming all the optimal tripods have been sorted by descending sums, display them in order
     * from largest to smallest based on the number of tripods that the user desired. In the end
     * display the total sum of them.
     * <p>
     * For example:
     * <ol>
     *     <li>location: (1,1), direction: NORTH, sum: 21</li>
     *     <li>location: (2,1), direction: NORTH, sum: 14</li>
     *     <li>location: (1,2), direction: WEST, sum: 14</li>
     *     <li>location: (1,0), direction: EAST, sum: 11</li>
     *     <li>location: (0,1), direction: SOUTH, sum: 11</li>
     * </ol>
     * Total sum: 71
     *
     * @param tripods    the sorted collection of optimal tripods by descending sum
     * @param numTripods the number of tripod the user wanted
     */
    public static void displayOptimalPlacements(ArrayList<Tripod> tripods, int numTripods) {
        int sum = 0;

        System.out.println("Optimal Placement(s):");
        for ( int i = 0; i < numTripods; i++ ) {
            Tripod tripod = tripods.get(i);
            System.out.printf("  %6d. Location: (%4d,%4d), Direction: %5s, Sum: %2d\r\n", i + 1, tripod.row(), tripod.col(), tripod.dir(), tripod.sum());
            sum += tripod.sum();
        }
        System.out.println("Total sum: " + sum);
    }

    /**
     * The main program takes the file name from the command line. It then
     * loads the file into a 2-D native array which is then displayed.
     * Next the user is prompted for how many tripods they want for
     * the optimal sum. If the number of tripods does not exceed the
     * total number that can be placed, the optimal tripods by location
     * are generated, then sorted by descending sum and then displayed
     * to the user.
     *
     * @param args command line arguments (don't use, and don't change the first 4 lines of this method)
     */
    public static void main(String[] args) {
        if ( args.length != 1 ) {
            System.out.println("Usage: java Tripods filename");
            return;
        }

        int[][] grid;

        try {
            grid = loadGrid(args[0]);
        } catch ( IOException e ) {
            System.err.println(e.getMessage());
            return;
        }

        Tripods.displayGrid(grid);

        System.out.print("How many tripods are being used?\n> ");

        Scanner stdin = new Scanner(System.in);
        int using = stdin.nextInt();

        stdin.close();

        if ( using < 0 ) {
            System.out.println("Yeah fire me an email on how to display a negative amount of tripods.");
            System.out.println("Email: huangkenneth94@gmail.com");
            return;
        }

        if ( using == 0 ) {
            System.out.println("Below are the 0 tripods you requested.\n");
            System.out.println("As you can see there is nothing there.");
            return;
        }

        if ( using > Tripods.getMaxTripods(grid) ) {
            System.out.println("Too many Tripods are being used!");
            return;
        }

        ArrayList<Tripod> sums = Tripods.generateSums(grid);
        ArrayList<Tripod> sortedSums = QuickSort.quickSort(sums);

        Collections.reverse(sortedSums);

        Tripods.displayOptimalPlacements(sortedSums, using);
    }
}
