package es.datastructur.synthesizer;
import java.util.Iterator;

/**
 * The BoundedQueue is similar to our Deque from Project 1, but with a more limited API.
 * Specifically, items can only be enqueued at the back of the queue, and can only be
 * dequeued from the front of the queue. Unlike our Deque, the BoundedQueue has a fixed capacity,
 * and nothing is allowed to enqueue if the queue is full.
 */
public interface BoundedQueue<T> extends Iterable<T> {
    /** Return size of the buffer */
    int capacity();

    /** Return number of items currently in the buffer */
    int fillCount();

    /** Add item x to the end */
    void enqueue(T x);

    /** Delete and return item from the front */
    T dequeue();

    /** Return (but do not delete) item from the front */
    T peek();

    /** Is the buffer empty (fillCount equals zero)? */
    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /** Is the buffer full (fillCount is same as capacity)? */
    default boolean isFull() {
        if (fillCount() == capacity()) {
            return true;
        } else {
            return false;
        }
    }
    /* Return the iterator of the queue */
    @Override
    Iterator<T> iterator();
}
