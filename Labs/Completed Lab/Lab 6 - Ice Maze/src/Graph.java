import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


/**
 *
 * @author Knei
 */
public class Graph<T> implements IGraph<T> {
    private final Collection<Node<T>> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    /**
     * Get all the nodes currently in this graph.
     *
     * @return a {@link java.util.Collection} of the graph's nodes.
     */
    @Override
    public Collection<Node<T>> getNodes() {
        return this.nodes;
    }

    /**
     * Check if a node with given data is in the graph.
     *
     * @param nodeData data of a node
     * @return true iff the graph contains a node with that data
     */
    @Override
    public boolean hasNode(T nodeData) {
        for (Node<T> node : this.nodes)
            if (node.getData().equals(nodeData)) return true;

        return false;
    }

    /**
     * Look up a node in the graph by its data.
     *
     * @param nodeData the sought node's value
     * @return the Node object with the given value or null if doesn't exist
     */
    @Override
    public Node<T> getNode(T nodeData) {
        for (Node<T> node : this.nodes)
            if ( Objects.equals(node.getData(), nodeData) ) return node;
        return null;
    }

    /**
     * Hook up two more nodes with an edge. Edges are directional, so if
     * you desire a two-way connection, addNeighbor must be called twice,
     * the second time with the arguments reversed.
     * Creates nodes with the given data if they do not already exist.
     *
     * @param from the value of the source node for the edge
     * @param to   the value of the destination node for the edge
     */
    @Override
    public void addEdge(T from, T to) {
        Node<T> f = this.getNode(from);
        Node<T> t = this.getNode(to);

        if ( f == null || t == null || f == t ) return;
        f.addEdge(t.getData());
     }

    /**
     * Create a new node for this graph.
     *
     * @param data the value of the new node
     * @return the Node object created
     */
    @Override
    public Node<T> addNode(T data) {
        Node<T> newNode = new Node<>(data);

        if (this.nodes.add(newNode)) return newNode;

        return null;
    }
}
