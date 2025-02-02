package rit.stu;

import rit.cs.CircularList;

/**
 * An implementation of a cursor based circular list using the {@link DLNode doubly linked node}.
 *
 * @param <T> the data type of the elements in the list
 */
public class CircularListNode<T> implements CircularList<T> {
    /**
     * The "first" {@link DLNode Node}
     *
     * @implNote head value can change
     */
    private DLNode<T> head;
    /**
     * Currently selected {@link DLNode Node}
     */
    private DLNode<T> cursor;
    private int size;

    /**
     * Initialize the list to be empty. This means the head and cursor are both null
     * and the size is 0.
     */
    public CircularListNode() {
        this.cursor = null;
        this.head = null;
        this.size = 0;
    }

    /**
     * Appends a new element to the end of the doubly linked list.
     *
     * @param element the new element to append
     * @implNote This appends based on the current {@link #head head} of the {@link CircularList}
     */
    @Override
    public void append(T element) {
        DLNode<T> newNode = new DLNode<>(element);

        if ( size == 0 ) {
            newNode.setNext(newNode);
            newNode.setPrev(newNode);

            this.head = newNode;
            this.cursor = newNode;
        } else {
            // set "tail"
            this.head.getPrev().setNext(newNode);
            newNode.setPrev(this.head.getPrev());

            // set "head"
            newNode.setNext(this.head);
            this.head.setPrev(newNode);
        }

        this.size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean valid() {
        return this.cursor != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.cursor = this.head;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forward() {
        assert this.cursor != null : "can't forward cursor, the list is empty!";
        this.cursor = this.cursor.getNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void backward() {
        assert this.cursor != null : "can't backward cursor, the list is empty!";
        this.cursor = this.cursor.getPrev();
    }

    /**
     * Gets the {@link DLNode#getData() data} from the current {@link DLNode Node}.
     *
     * @return the {@link DLNode#getData() data} of the current node
     */
    @Override
    public T get() {
        assert this.cursor != null : "can't get, cursor is off the list!";
        return this.cursor.getData();
    }

    /**
     * Remove the element at the cursor and then advance it forward to the next element.
     *
     * @return the {@link DLNode#getData() data} of the removed {@link DLNode Node}
     */
    @Override
    public T removeForward() {
        assert this.cursor != null : "can't removeForward, cursor is off the list!";

        DLNode<T> removed = this.cursor;

        if ( this.size == 1 ) {
            this.cursor = null;
            this.head = null;
        } else {
            if ( removed == this.head ) {
                this.head = removed.getNext();
            }
            this.cursor = this.cursor.getNext();

            removed.getPrev().setNext(removed.getNext());
            removed.getNext().setPrev(removed.getPrev());
        }

        this.size--;
        return removed.getData();
    }

    /**
     * Remove the element at the cursor and then advance it backward to the previous element.
     *
     * @return the {@link DLNode#getData() data} of the removed {@link DLNode Node}
     */
    @Override
    public T removeBackward() {
        assert this.cursor != null : "can't removeBackward, cursor is off the list!";

        DLNode<T> removed = this.cursor;

        if ( this.size == 1 ) {
            this.cursor = null;
            this.head = null;
        } else {
            if ( removed == this.head ) {
                this.head = removed.getPrev();
            }

            this.cursor = this.cursor.getPrev();

            removed.getPrev().setNext(removed.getNext());
            removed.getNext().setPrev(removed.getPrev());
        }

        this.size--;
        return removed.getData();
    }

    /**
     * Returns a string in the format:
     * <pre>{@code
     *     Player <-- CURSOR
     *     Player
     *     Player
     *     ...
     * }</pre>
     * Where "<-- CURSOR" points to the player at the cursor. <br />
     * If the list is empty:
     * <pre>{@code
     *     Empty list!
     * }</pre>
     *
     * @implSpec Use System.lineSeparator() instead of "\n" when adding a new line!!!
     */
    @Override
    public String toString() {
        if ( this.size == 0 ) {
            return "Empty list!";
        }

        String lineSeparator = System.lineSeparator();
        StringBuilder builder = new StringBuilder();

        DLNode<T> pointer = this.head;

        while ( pointer != this.head || builder.isEmpty() ) {
            builder.append(pointer.getData());
            if ( pointer == this.cursor ) builder.append(" <-- CURSOR");
            builder.append(lineSeparator);

            pointer = pointer.getNext();
        }

        return builder.toString();
    }
}
