import java.util.Objects;

/**
 * Class representing a node in a graph - to be written.
 * The node contains data of the given generic type. Neighbors are
 * stored internally in a collection.
 * Initially left empty to be implemented by students.
 *
 * @author RIT CS
 * @author Knei
 */
public class Node<T> {
    // TODO instance variables
    private final Node<T>[] neighbours;
    private final T data;

    // TODO constructor
    @SuppressWarnings("unchecked")
    public Node(T data) {
        this.neighbours = (Node<T>[]) new Node[Directions.values().length];

        this.data = data;
    }

    // TODO accessors
    public T getData() {
        return this.data;
    }

    public Node<T> getNodeAtDirection(Directions dir) {
        Objects.requireNonNull(dir, "Direction cannot be null");

        return switch ( dir ) {
            case NORTH -> this.neighbours[0];
            case EAST  -> this.neighbours[1];
            case SOUTH -> this.neighbours[2];
            case WEST  -> this.neighbours[3];
        };
    }

    // TODO function to add a neighbor
    public void setNodeAtDirection(Directions dir, Node<T> newNeighbourNode) {
        Objects.requireNonNull(dir, "Direction cannot be null");

        switch ( dir ) {
            case NORTH -> this.neighbours[0] = newNeighbourNode;
            case EAST  -> this.neighbours[1] = newNeighbourNode;
            case SOUTH -> this.neighbours[2] = newNeighbourNode;
            case WEST  -> this.neighbours[3] = newNeighbourNode;
        }
    }

    public enum Directions {
        NORTH, EAST, SOUTH, WEST
    }
}
