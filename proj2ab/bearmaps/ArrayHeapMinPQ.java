package bearmaps;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    /**
     * Adds an item of type T with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null.
     *
     * @param item
     * @param priority
     */
    @Override
    public void add(T item, double priority) {

    }

    /**
     * Returns true if the PQ contains the given item.
     *
     * @param item
     */
    @Override
    public boolean contains(T item) {
        return false;
    }

    /** Returns the item with smallest priority. If no items exist, throw a NoSuchElementException. */
    @Override
    public T getSmallest() {
        return null;
    }

    /** Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        return null;
    }

    /** Returns the number of items in the PQ. */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist.
     *
     * @param item
     * @param priority
     */
    @Override
    public void changePriority(T item, double priority) {

    }
}
