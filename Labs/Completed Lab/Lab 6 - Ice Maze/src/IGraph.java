import java.util.*;

/**
 * Interface for a directed graph. Nodes are connected by edges, but
 * there is no concept of an edge here, e.g., now edge "weights".
 * The nodes are identified with strings and contain no other internal data.
 *
 * @author RIT CS
 * @param <DataType> Type of data contained in the nodes of the graph
 */
public interface IGraph< DataType > {

    /**
     * Get all the nodes currently in this graph.
     * @return a {@link Collection} of the graph's nodes.
     */
    Collection<Node<DataType>> getNodes();

    /**
     * Check if a node with given data is in the graph.
     *
     * @param nodeData data of a node
     * @return true iff the graph contains a node with that data
     */
    public boolean hasNode( DataType nodeData );

    /**
     * Look up a node in the graph by its data.
     * @param nodeData the sought node's value
     * @return the Node object with the given value or null if doesn't exist
     */
    public Node<DataType> getNode(DataType nodeData );

    /**
     * Hook up two more nodes with an edge. Edges are directional, so if
     * you desire a two-way connection, addNeighbor must be called twice,
     * the second time with the arguments reversed.
     * Creates nodes with the given data if they do not already exist.
     * @param from the value of the source node for the edge
     * @param to the value of the destination node for the edge
     */
    public void addEdge(DataType from, DataType to);

    /**
     * Create a new node for this graph.
     * @param data the value of the new node
     * @return the Node object created
     */
    public Node<DataType> addNode(DataType data);

}
