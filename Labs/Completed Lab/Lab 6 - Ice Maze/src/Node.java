import java.util.ArrayList;

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
    private final ArrayList<T> edgeData;
    private final T data;

    // TODO constructor
    public Node(T data) {
        this.edgeData = new ArrayList<>(4);
        this.data = data;
    }

    // TODO accessors
    public T getData() {
        return this.data;
    }

    public ArrayList<T> getEdgeData() {
        return this.edgeData;
    }

    public void addEdge(T newEdgeNode) {
        this.edgeData.add(newEdgeNode);
    }

    public void replaceEdge(T edge, T with) {
        for ( int i = 0; i < this.edgeData.size(); i++ ) {
            if ( this.edgeData.get(i).equals(edge) )
                this.edgeData.set(i, with);
        }
    }
}
