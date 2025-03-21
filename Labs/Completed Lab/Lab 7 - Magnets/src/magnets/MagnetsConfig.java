package magnets;

import backtracking.Configuration;
import test.IMagnetTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a magnet configuration, including the ability
 * to backtrack and also give information to the JUnit tester.
 * <p>
 * This implements a more optimal pruning strategy in isValid():
 * - Pair checked each time a new cell is populated
 * - Polarity checked each time a new cell is populated
 * - When last column or row is populated, the pos/neg counts are checked
 *
 * @author RIT CS
 */
public class MagnetsConfig implements Configuration, IMagnetTest {
    /**
     * a cell that has not been assigned a value yet
     */
    private final static char EMPTY = '.';
    /**
     * a blank cell
     */
    private final static char BLANK = 'X';
    /**
     * a positive cell
     */
    private final static char POS = '+';
    /**
     * a negative cell
     */
    private final static char NEG = '-';
    /**
     * left pair value
     */
    private final static char LEFT = 'L';
    /**
     * right pair value
     */
    private final static char RIGHT = 'R';
    /**
     * top pair value
     */
    private final static char TOP = 'T';
    /**
     * bottom pair value
     */
    private final static char BOTTOM = 'B';
    /**
     * and ignored count for pos/neg row/col
     */
    private final static int IGNORED = -1;

    private static final List<String> VALID_PAIRINGS = List.of("+-", "-+", "XX");
    // TODO
    // add private state here
    private final char[][] board;
    private final char[][] pairing;
    private final int[] posRow;
    private final int[] posCol;
    private final int[] negRow;
    private final int[] negCol;
    private int cursorRow = 0;
    private int cursorCol = -1;

    /**
     * Read in the magnet puzzle from the filename.  After reading in, it should display:
     * - the filename
     * - the number of rows and columns
     * - the grid of pairs
     * - the initial config with all empty cells
     *
     * @param filename the name of the file
     * @throws java.io.IOException thrown if there is a problem opening or reading the file
     */
    public MagnetsConfig(String filename) throws IOException {
        try ( BufferedReader in = new BufferedReader(new FileReader(filename)) ) {
            // read first line: rows cols
            String[] fields = in.readLine().split("\\s+");

            // TODO: Finish implementing the constructor
            int rowLen = Integer.parseInt(fields[0]);
            int colLen = Integer.parseInt(fields[1]);

            this.board = new char[rowLen][colLen];
            this.pairing = new char[rowLen][colLen];

            this.posRow = MagnetsConfig.parseCounts(in.readLine().split("\\s+"));
            this.posCol = MagnetsConfig.parseCounts(in.readLine().split("\\s+"));
            this.negRow = MagnetsConfig.parseCounts(in.readLine().split("\\s+"));
            this.negCol = MagnetsConfig.parseCounts(in.readLine().split("\\s+"));

            for ( int i = 0; i < rowLen; i++ )
                for ( int j = 0; j < colLen; j++ )
                    this.board[i][j] = MagnetsConfig.EMPTY;

            String line;
            for ( int i = 0; (line = in.readLine()) != null; i++ )
                this.pairing[i] = String.join("", line.split("\\s+")).toCharArray(); // Copied from Lab 6

        } // <3 Jim
    }

    /**
     * The copy constructor which advances the cursor, creates a new grid,
     * and populates the grid at the cursor location with val
     *
     * @param other the board to copy
     * @param val   the value to store at new cursor location
     */
    private MagnetsConfig(MagnetsConfig other, char val) {
        this.board = other.board.clone();
        for ( int i = 0; i < this.board.length; i++ )
            this.board[i] = this.board[i].clone();

        this.pairing = other.pairing;

        this.posRow = other.posRow;
        this.posCol = other.posCol;
        this.negRow = other.negRow;
        this.negCol = other.negCol;

        this.cursorCol = other.cursorCol + 1;
        this.cursorRow = other.cursorRow;

        if ( this.cursorCol >= this.getCols() ) {
            this.cursorCol = 0;
            this.cursorRow += 1;
        }

        this.board[this.cursorRow][this.cursorCol] = val;
    }

    /**
     * Generate the successor configs.  For minimal pruning, this should be
     * done in the order: +, - and X.
     *
     * @return the collection of successors
     */
    @Override
    public List<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();

        successors.add(new MagnetsConfig(this, MagnetsConfig.POS));
        successors.add(new MagnetsConfig(this, MagnetsConfig.NEG));
        successors.add(new MagnetsConfig(this, MagnetsConfig.BLANK));

        return successors;
    }

    /**
     * Checks to make sure a successor is valid or not.  For minimal pruning,
     * each newly placed cell at the cursor needs to make sure its pair
     * is valid, and there is no polarity violation.  When the last cell is
     * populated, all row/col pos/negative counts are checked.
     *
     * @return whether this config is valid or not
     */
    @Override
    public boolean isValid() {
        // Check top, and left for matching val... That is basically all you need to check
        char pairType = this.getPair(this.cursorRow, this.cursorCol);
        char self = this.getVal(this.cursorRow, this.cursorCol);

        char lVal = this.getVal(this.cursorRow, this.cursorCol - 1);
        char tVal = this.getVal(this.cursorRow - 1, this.cursorCol);

        boolean valid = true;

        if ( pairType == MagnetsConfig.RIGHT ) {
            valid = ((self != lVal && lVal != MagnetsConfig.EMPTY) || self == MagnetsConfig.BLANK) && MagnetsConfig.VALID_PAIRINGS.contains("" + self + lVal);
        } else if ( pairType == MagnetsConfig.BOTTOM ) {
            valid = ((self != tVal && tVal != MagnetsConfig.EMPTY) || self == MagnetsConfig.BLANK) && MagnetsConfig.VALID_PAIRINGS.contains("" + self + tVal);
        } else if ( self == lVal || self == tVal )
            valid = self == MagnetsConfig.BLANK;

        if ( !this.isGoal() || !valid )
            return valid;

        int[] posRowc = this.posRow.clone(),
                posColc = this.posCol.clone(),
                negRowc = this.negRow.clone(),
                negColc = this.negCol.clone();

        for ( int i = 0; i < this.getRows(); i++ )
            for ( int j = 0; j < this.getCols(); j++ )
                switch ( this.getVal(i, j) ) {
                    case MagnetsConfig.POS -> {
                        posRowc[i] -= 1;
                        posColc[j] -= 1;
                    }
                    case MagnetsConfig.NEG -> {
                        negRowc[i] -= 1;
                        negColc[j] -= 1;
                    }
                }

        for ( int i = 0, j = 0; i < this.getRows(); i++, j++ )
            if ( (this.posRow[i] != -1 && posRowc[i] != 0) || (this.negRow[i] != -1 && negRowc[i] != 0) ) return false;


        for ( int i = 0; i < this.getCols(); i++ )
            if ( (this.posCol[i] != -1 && posColc[i] != 0) || (this.negCol[i] != -1 && negColc[i] != 0) ) return false;

        return true;
    }

    @Override
    public boolean isGoal() {
        return this.cursorRow == this.getRows() - 1 && this.cursorCol == this.getCols() - 1;
    }

    /**
     * Returns a string representation of the puzzle including all necessary info.
     *
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        // top row
        result.append("+ ");
        for ( int col = 0; col < getCols(); ++col ) {
            result.append(getPosColCount(col) != IGNORED ? getPosColCount(col) : " ");
            if ( col < getCols() - 1 ) {
                result.append(" ");
            }
        }
        result.append(System.lineSeparator());
        result.append("  ");
        for ( int col = 0; col < getCols(); ++col ) {
            if ( col != getCols() - 1 ) {
                result.append("--");
            } else {
                result.append("-");
            }
        }
        result.append(System.lineSeparator());

        // middle rows
        for ( int row = 0; row < getRows(); ++row ) {
            result.append(getPosRowCount(row) != IGNORED ? getPosRowCount(row) : " ").append("|");
            for ( int col = 0; col < getCols(); ++col ) {
                result.append(getVal(row, col));
                if ( col < getCols() - 1 ) {
                    result.append(" ");
                }
            }
            result.append("|").append(getNegRowCount(row) != IGNORED ? getNegRowCount(row) : " ");
            result.append(System.lineSeparator());
        }

        // bottom row
        result.append("  ");
        for ( int col = 0; col < getCols(); ++col ) {
            if ( col != getCols() - 1 ) {
                result.append("--");
            } else {
                result.append("-");
            }
        }
        result.append(System.lineSeparator());

        result.append("  ");
        for ( int col = 0; col < getCols(); ++col ) {
            result.append(getNegColCount(col) != IGNORED ? getNegColCount(col) : " ").append(" ");
        }
        result.append(" -").append(System.lineSeparator());
        return result.toString();
    }

    // IMagnetTest

    // NOTE #getRows and #getCols use any row or col array length will suffice as their sizes are dependent of each other

    @Override
    public int getRows() {
        return this.posRow.length;
    }

    @Override
    public int getCols() {
        return this.posCol.length;
    }

    @Override
    public int getPosRowCount(int row) {
        return this.posRow[row];
    }

    @Override
    public int getPosColCount(int col) {
        return this.posCol[col];
    }

    @Override
    public int getNegRowCount(int row) {
        return this.negRow[row];
    }

    @Override
    public int getNegColCount(int col) {
        return this.negCol[col];
    }

    @Override
    public char getPair(int row, int col) {
        return this.pairing[row][col];
    }

    @Override
    public char getVal(int row, int col) {
        if ( row < 0 || col < 0 || this.getRows() <= row || this.getCols() <= col )
            return MagnetsConfig.EMPTY; // Kind of cheating but whatever, solves a lot of complicated copy & pasting for #isValid()

        return this.board[row][col];
    }

    @Override
    public int getCursorRow() {
        return this.cursorRow;
    }

    @Override
    public int getCursorCol() {
        return this.cursorCol;
    }

    private static int[] parseCounts(String[] stringCounts) {
        int[] counts = new int[stringCounts.length];

        for ( int i = 0; i < stringCounts.length; i++ )
            counts[i] = Integer.parseInt(stringCounts[i]);

        return counts;
    }
}
