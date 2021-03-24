/**
 * Linked-list based double ended queue, which accepts generic types.
 *
 * @Rule: All the method should follow "Deque API" described in
 * https://sp18.datastructur.es/materials/proj/proj1a/proj1a#the-deque-api
 * @Rule: The amount of memory that this program uses at any given time must be
 * proportional to the number of items.
 */
public class LinkedListDeque<T> {
    private TNode sentinel; // Circular sentinel.
    private int size = 0;

    /**
     * Constructor that creates an empty LinkedListDeque.
     */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Constructor
     */
    public LinkedListDeque(T item) {
        sentinel = new TNode(null, null, null);
        sentinel.next = new TNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @Rule: add and remove operations must not involve any looping or recursion. A
     * single such operation must take “constant time”
     */
    public void addFirst(T item) {
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @Rule: add and remove operations must not involve any looping or recursion. A
     * single such operation must take “constant time”
     */
    public void addLast(T item) {
        sentinel.prev = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else
            return false;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space. Once
     * all the items have been printed, print out a new line.
     */
    public void printDeque() {
        TNode pointer = sentinel;
        while (pointer.next != sentinel) { // Since pointer.item is always null, use pointer.next.
            System.out.print(pointer.next.item + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item
     * exists, returns null.
     */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T removedFirst_item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return removedFirst_item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T removedLast_item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return removedLast_item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     *
     * @Rule: get must use iteration, not recursion.
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        TNode pointer = sentinel.next;
        while (index != 0) {
            pointer = pointer.next;
            index--;
        }
        return pointer.item;
    }
    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     *
     * @Rule: get must use recursion, not iteration.
     */
    /**
     * Helper method for getRecursive. This is similar to line 128~132.
     */
    private T getRecursiveHelper(TNode pointer, int index) {
        if (index == 0) {
            return pointer.item;
        }
        return getRecursiveHelper(pointer.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    public class TNode {
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

}