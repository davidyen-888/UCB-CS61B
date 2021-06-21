package bearmaps;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private static final int INIT_CAPACITY = 100;

    private PriorityNode[] heap;    // store items at indices 1 to n.
    private HashMap<T, Integer> items;  // store items with Int indices in heap.
    private double loadfactorMin;
    private double loadfactorMax;

    /** Initializes an empty priority queue with the given initial capacity. */
    public ArrayHeapMinPQ() {
        heap = new ArrayHeapMinPQ.PriorityNode[INIT_CAPACITY];
        items = new HashMap<>();
        loadfactorMax = 0.75;
        loadfactorMin = 0.25;
    }

    /** PriorityNode class stores the item and priority, with comparability among priorities. */
    private class PriorityNode implements Comparable<PriorityNode> {
        T item;
        double priority;

        PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double p) {
            priority = p;
        }

        /**
         * If the priority of first object is smaller, return -1.
         * If priority of both object is same or the second object is null, return 0.
         * If the priority of first object is larger, return 1.
         */
        @Override
        public int compareTo(PriorityNode o) {
            if (o == null) {
                return 0;
            } else {
                return Double.compare(this.priority, o.priority);
            }
        }
    }

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
        if (contains(item)) {
            throw new IllegalArgumentException("item is already present");
        }
        if ((double) (size() / heap.length) > loadfactorMax) {
            resize(heap.length * 2);
        }
        int pos=size()+1;
        heap[pos]=new PriorityNode(item, priority);
        items.put(item, pos);
        swim(pos);
    }

    /**
     * Returns the index of the parent item.
     * If the index = 1(Root), returns its own index.
     */
    private int parent(int i) {
        if (i == 1) {
            return 1;
        }
        return i / 2;
    }

    /** Swap the index a and b. */
    private void swap(int a, int b) {
        if (a == b) {
            return;
        }
        PriorityNode temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
        items.put(heap[a].getItem(), a);
        items.put(heap[b].getItem(), b);
    }

    /** Swaps the item recursively if index i has lower priority than its parent. */
    private void swim(int i) {
        if (heap[i].compareTo(heap[parent(i)]) < 0) {   // child is smaller
            swap(i, parent(i));
            swim(parent(i));
        }
    }

    private void resize(int newCapacity) {
        PriorityNode[] temp = new ArrayHeapMinPQ.PriorityNode[newCapacity];
        for (int i = 0; i <= size(); i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    /**
     * Returns true if the PQ contains the given item.
     *
     * @param item
     */
    @Override
    public boolean contains(T item) {
        if (size()==0){
            return false;
        }
        return items.containsKey(item);
    }

    /** Returns the item with smallest priority.(Index = 1)
     *  If no items exist, throw a NoSuchElementException. */
    @Override
    public T getSmallest() {
        if (size()==0){
            throw new NoSuchElementException("empty PQ");
        }
        return heap[1].item;
    }

    /** Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        return null;
    }

    /** Returns the number of items in the PQ. */
    @Override
    public int size() {
        return items.size();
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
