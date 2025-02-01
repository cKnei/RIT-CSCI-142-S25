package rit.stu;

/**
 * A generic doubly linked node that holds a type {@code T} as its data.
 *
 * @author RIT CS
 * @author Knei
 */
public class DLNode<T> {
    /**
     * User data
     */
    private T data;

    /**
     * Previous node link
     */
    private DLNode<T> prev;

    /**
     * Next node link
     */
    private DLNode<T> next;

    /**
     * Create a new node with no predecessor or successor.
     *
     * @param data the user data to be stored
     */
    public DLNode(T data) {
        this(data, null, null);
    }

    /**
     * Construct a new node with pointers to the previous and next node.
     *
     * @param data The user data to be stored
     * @param prev The link to the previous node (null if none)
     * @param next The link to the next node (null if none)
     */
    public DLNode(T data, DLNode<T> prev, DLNode<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Get the predecessor node.
     *
     * @return the previous node (null if none)
     */
    public DLNode<T> getPrev() {
        return this.prev;
    }

    /**
     * Change the node's successor.
     *
     * @param prev the node's new next link
     */
    public void setPrev(DLNode<T> prev) {
        this.prev = prev;
    }

    /**
     * Get the successor node.
     *
     * @return the next node (null if none)
     */
    public DLNode<T> getNext() {
        return this.next;
    }

    /**
     * Change the node's successor.
     *
     * @param next the node's new next link
     */
    public void setNext(DLNode<T> next) {
        this.next = next;
    }

    /**
     * Get the Node's data.
     *
     * @return the user data
     */
    public T getData() {
        return this.data;
    }

    /**
     * Change the node's data.
     *
     * @param data the node's new data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns a string representation of the node in the format:
     * <pre>
     *     DLNode{data=XXX, prev=YYY, next=YYY}
     * </pre>
     * <ul>
     *     <li>
     *         XXX: The data element of the current node.
     *     </li>
     *     <li>
     *         YYY: The data element of the previous node.  If no node, "null".
     *     </li>
     *     <li>
     *         ZZZ: The data element of the next node.  If no node, "null".
     *     </li>
     * </ul>
     *
     * @return the string described above
     */
    @Override
    public String toString() {
        return "DLNode{" +
                "data='" + this.data + '\'' +
                ", prev=" + (this.prev != null ? this.prev.getData() : "null") +
                ", next=" + (this.next != null ? this.next.getData() : "null") +
                '}';
    }
}