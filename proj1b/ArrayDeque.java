/**
 * Array-based double ended queue, which accepts generic types.
 *
 * @Rule: All the method should follow "Deque API" described in
 * https://sp19.datastructur.es/materials/proj/proj1a/proj1a#the-deque-api
 * @Rule: The starting size of array should be 8.
 * @Rule: The amount of memory that this program uses at any given time must be
 * proportional to the number of items.
 * @Rule: For Arrays of length 16 or more, the usage factor should always be at
 * least 25%.
 */
public class ArrayDeque<T> implements Deque<T>{
    private static int initialCapacity = 8; // Starting size of the array.
    private static int minCapacity = 16; // The minimum capacity for contraction resizing
    private static int RFactor = 2; // Expand and Contract factor.
    private static double minUsageRatio = 0.25; // Usage factor.
    private int head;
    private int tail;
    private int size;
    private T[] myArrayDeque;

    /** Constructor that creates an empty ArrayDeque. */
    public ArrayDeque() {
        myArrayDeque = (T[]) new Object[initialCapacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] container = (T[]) new Object[capacity];
        if (head > tail) {
            int headToContainerBottom = myArrayDeque.length - head;
            /** Copies the [head - headToContainerBottom] to container starting from 0.*/
            System.arraycopy(myArrayDeque, head, container, 0, headToContainerBottom);
            /** Copies the rest [real head - nominated tail"headToContainerBottom"] part to the container starting from headToContainerBottom.*/
            System.arraycopy(myArrayDeque, 0, container, headToContainerBottom, size - headToContainerBottom);
        } else {
            System.arraycopy(myArrayDeque, head, container, 0, myArrayDeque.length);
        }
        myArrayDeque = container;
        /** Modify head and tail.*/
        head = 0;
        tail = size - 1;
    }

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @Rule: A single such operation must take “constant time”
     * @Rule: Treat array as circular: if front pointer is at position zero,
     * addFirst: the front pointer should loop back around to the end of the array,
     * so the new front item in the deque will be the last item in the underlying array.
     */
    @Override
    public void addFirst(T item) {
        if (size == myArrayDeque.length) {
            resize(size * RFactor);
        }
        if (isEmpty()) {
            head = 0;
            tail = 0;
            myArrayDeque[head] = item;
        } else {
            /* Adds to the index of insert, in the condition of size!=0, new first item is actually the last item.*/
            int insert = (head - 1 + myArrayDeque.length) % myArrayDeque.length;
            myArrayDeque[insert] = item;
            head = insert;
        }
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @Rule: A single such operation must take “constant time”
     */
    @Override
    public void addLast(T item) {
        if (size == myArrayDeque.length) {
            resize(size * RFactor);
        }
        if (isEmpty()) {
            head = 0;
            tail = 0;
            myArrayDeque[tail] = item;
        } else {
            int insert = (tail + 1 + myArrayDeque.length) % myArrayDeque.length;
            myArrayDeque[insert] = item;
            tail = insert;
        }
        size++;
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space. Once
     * all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(myArrayDeque[(i + head) % myArrayDeque.length] + " ");
        }
        System.out.println();
    }

    /** Checks For Arrays of length 16 or more, the usage factor should always be at least 25%. */
    private boolean isLowUsage() {
        /* @Source: Cast problem double https://stackoverflow.com/questions/3144610/integer-division-how-do-you-produce-a-double*/
        double rate = (double) size / myArrayDeque.length;
        return rate < minUsageRatio && myArrayDeque.length >= minCapacity;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item
     * exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T temp = myArrayDeque[head];
        myArrayDeque[head] = null;
        head = (head + 1) % myArrayDeque.length;
        size--;
        if (isLowUsage()) {
            resize(myArrayDeque.length / RFactor);
        }
        return temp;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T temp = myArrayDeque[tail];
        myArrayDeque[tail] = null;
        tail = (tail - 1) % myArrayDeque.length;
        size--;
        if (isLowUsage()) {
            resize(myArrayDeque.length / RFactor);
        }
        return temp;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     *
     * @Rule: get must take constant time.
     */
    @Override
    public T get(int index) {
        if ( index > size - 1) {
            return null;
        } else {
            return myArrayDeque[(index + head) % myArrayDeque.length];
        }
    }
}