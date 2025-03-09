import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The main IceMaze class. Used to represent the overall maze.
 * BFS should be implemented within this class.
 * Also contains code for reading in the file and creating an
 * IceMaze object based on the file contents.
 *
 * @author RIT CS
 * @author Knei
 */
public class IceMaze {
    public static final char BLOCK = '*';
    public static final char ICE = '.';

    private final int exitRow;
    private final Graph<Coord> graph;
    private final char[][] maze;

    public IceMaze(BufferedReader bufferedReader) throws IOException {
        this.graph = new Graph<>();

        String[] info = bufferedReader.readLine().split("\\s+");
        this.maze = new char[Integer.parseInt(info[0])][Integer.parseInt(info[1])];
        this.exitRow = Integer.parseInt(info[2]);


        String line;
        for ( int i = 0; (line = bufferedReader.readLine()) != null; i++ ) {
            this.maze[i] = String.join("", line.strip().split("\\s+")).toCharArray(); // This is a crime of a line
        }
    }

    /**
     * A function to build the graph based on the loaded grid.
     */
    public void buildGraph() throws InvalidMazeException {
        int col = this.maze[0].length;

        for ( int i = 0; i < this.maze.length; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                switch ( this.maze[i][j] ) {
                    case IceMaze.ICE -> this.graph.addNode(new Coord(i, j));
                    case IceMaze.BLOCK -> { }

                    default -> throw new InvalidMazeException("Invalid Maze character: `" + this.maze[i][j] + "`");
                }
            }
        }

        this.graph.addNode(new Coord(exitRow, col - 1));
    }

    /**
     * A function to print the graph of the maze.
     */
    public void printGraph() {
        for ( Node<Coord> node : this.graph.getNodes() ) {
            System.out.println(node.getData() + ":");
            // TODO
        }
    }

    /**
     * Reads in a file and creates an IceMaze object to hold the associated graph.
     *
     * @param filename Name of ice maze file
     * @return New IceMaze object
     * @throws InvalidMazeException in case of file errors
     */
    public static IceMaze readFile(String filename) throws InvalidMazeException {
        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)) ) {
            return new IceMaze(bufferedReader);
        } catch ( Throwable e ) {
            throw new InvalidMazeException("Error reading file: " + filename);
        }
    }

    /**
     * Main method - add to this as you go!
     *
     * @param args Command line arguments, should contain maze file name
     */
    public static void main(String[] args) {
        if ( args.length != 1 ) {
            System.out.println("Usage: java IceMaze <filename>");
        }
        try {
            IceMaze maze = readFile(args[0]);
            maze.buildGraph();
            maze.printGraph();
            // TODO BFS search algorithm
        } catch ( InvalidMazeException e ) {
            System.out.println("Invalid maze file!");
            System.out.println(e.getMessage());
        }
    }
}
