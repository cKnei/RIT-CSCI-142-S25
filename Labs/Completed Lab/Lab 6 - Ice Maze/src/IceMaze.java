import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The main IceMaze class. Used to represent the overall maze.
 * BFS should be implemented within this class.
 * Also contains code for reading in the file and creating an
 * IceMaze object based on the file contents.
 * 
 * @author RIT CS
 * @author <<YOUR NAME HERE>>
 */
public class IceMaze {

    /**
     * Reads in a file and creates an IceMaze object to hold the associated graph.
     * @param filename Name of ice maze file
     * @return New IceMaze object
     * @throws InvalidMazeException in case of file errors
     */
    public static IceMaze readFile(String filename) throws InvalidMazeException {
        return null;
    }
    
    /**
     * A function to build the graph based on the loaded grid.
     */
    public void buildGraph() {
        // TODO
    }

    /**
     * A function to print the graph of the maze.
     */
    public void printGraph() {

    }
    
    /**
     * Main method - add to this as you go!
     * @param args Command line arguments, should contain maze file name
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java IceMaze <filename>");
        } else {
            try {
                IceMaze maze = readFile(args[0]);
                maze.buildGraph();
                maze.printGraph();
                // TODO BFS search algorithm
            } catch (InvalidMazeException e) {
                System.out.println("Invalid maze file!");
                System.out.println(e.getMessage());
            }
        }
    }
}
