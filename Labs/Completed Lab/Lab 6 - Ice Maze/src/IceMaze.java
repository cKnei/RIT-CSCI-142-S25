import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private final Coord exit;
    private final Graph<Coord> graph;
    private final char[][] maze;

    public IceMaze(BufferedReader bufferedReader) throws IOException, InvalidMazeException {
        this.graph = new Graph<>();

        String[] info = bufferedReader.readLine().split("\\s+");
        this.maze = new char[Integer.parseInt(info[0])][Integer.parseInt(info[1])];

        int exitRow = Integer.parseInt(info[2]);
        if ( exitRow < 0 || this.maze.length <= exitRow )
            throw new InvalidMazeException("Invalid exit row: " + exitRow);

        this.exit = new Coord(exitRow, Integer.parseInt(info[1]));


        String line;
        for ( int i = 0; (line = bufferedReader.readLine()) != null; i++ ) {
            this.maze[i] = String.join("", line.strip().split("\\s+")).toCharArray(); // This is a crime of a line
        }
    }

    /**
     * A function to build the graph based on the loaded grid.
     */
    public void buildGraph() throws InvalidMazeException {
        int maxCol = this.maze[0].length;

        for ( int row = 0; row < this.maze.length; row++ )
            for ( int col = 0; col < maxCol; col++ )
                switch ( this.maze[row][col] ) {
                    case IceMaze.ICE -> this.graph.addNode(new Coord(row, col));
                    case IceMaze.BLOCK -> {
                    }

                    default -> throw new InvalidMazeException("Invalid Maze character: `" + this.maze[row][col] + "`");
                }

        this.findAndSetEdges();
    }

    /**
     * A function to print the graph of the maze.
     */
    public void printGraph() {
        for ( Node<Coord> node : this.graph.getNodes() ) {
            StringBuilder s = new StringBuilder(node.getData() + ":  ");

            for ( Coord edgeNode : node.getEdgeData() )
                s.append(edgeNode.toString()).append(", ");

            System.out.println(s.delete(s.length() - 2, s.length()));
        }

        System.out.println(exit);
        System.out.println();
    }

    private void faseLoop(int start, int end, int step, int staticIndex, boolean row) {
        if ( step == 0 ) throw new IllegalArgumentException("Step cannot be 0");

        Coord extreme = null;
        for ( int i = start; (step > 0 && i < end) || (step < 0 && i > end); i += step ) {
            Coord coord;
            if ( row ) coord = new Coord(staticIndex, i);
            else coord = new Coord(i, staticIndex);

            if ( this.maze[coord.r()][coord.c()] == IceMaze.BLOCK ) {
                extreme = null;
            } else if ( extreme == null ) {
                extreme = coord;
            } else {
                this.graph.getNode(coord).addEdge(extreme);
            }
        }
    }

    private void findAndSetEdges() {
        int maxRow = this.maze.length,
                maxCol = this.maze[0].length;

        // East West
        for ( int curRow = 0; curRow < maxRow; curRow++ ) {
            this.faseLoop(0, maxCol, +1, curRow, true);
            this.faseLoop(maxCol - 1, -1, -1, curRow, true);
        }
        // North South
        for ( int curCol = 0; curCol < maxCol; curCol++ ) {
            this.faseLoop(0, maxRow, +1, curCol, false);
            this.faseLoop(maxRow - 1, -1, -1, curCol, false);
        }

        Coord e = new Coord(this.exit.r(), this.exit.c() - 1);
        for ( int col = 0; col < this.maze[0].length; col++ ) {
            Coord coord = new Coord(this.exit.r(), col);
            Node<Coord> node = this.graph.getNode(coord);

            if ( node == null ) continue;

            if ( node.getData().equals(e) ) {
                node.addEdge(this.exit);
            } else if ( node.getEdgeData().contains(e) ) {
                node.replaceEdge(e, this.exit);
            }
        }
    }

    public void breadthFirstSearch() {
        ArrayList<ArrayList<Coord>> solutions = new ArrayList<>();

        ArrayList<Coord> queue = new ArrayList<>(List.of(this.exit));
        ArrayList<Coord> visited = new ArrayList<>();

        int depth = 0;

        while (!queue.isEmpty()) {
            ArrayList<Coord> oldQueue = queue;
            queue = new ArrayList<>();

            solutions.add(new ArrayList<>());

            while (!oldQueue.isEmpty()) {
                Coord lookFor = oldQueue.removeFirst();

                for ( Node<Coord> node : this.graph.getNodes() ) {
                    if ( visited.contains(node.getData()) || !node.getEdgeData().contains(lookFor) ) continue;

                    Coord data = node.getData();

                    queue.add(data);
                    visited.add(data);
                    solutions.get(depth).add(data);
                }
            }

            depth++;
        }

        for ( int i = 0; i < solutions.size() - 1; i++ ) {
            StringBuilder s = new StringBuilder((i+1) + ": ");
            for ( Coord c : solutions.get(i) )
                s.append(c).append(" ");

            System.out.println(s);
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
            maze.breadthFirstSearch();
        } catch ( InvalidMazeException e ) {
            System.out.println("Invalid maze file!");
            System.out.println(e.getMessage());
        }
    }
}
