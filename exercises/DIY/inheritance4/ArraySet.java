import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;


public class ArraySet<T> {
    private T[] items;
    private int size;

    /** Constructor for ArraySet */
    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /*
     * Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Associates the specified value with the specified key in this map. Throws an
     * IllegalArgumentException if the key is null.
     */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("Can't add null");
        }
        // With only one copy in set.
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /** Returns an iterator (a.k.a. seer) into ME. */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }
    private class ArraySetIterator implements Iterator<T>{
        private int WizardPosition;
        public ArraySetIterator(){
            WizardPosition=0;
        }
        public boolean hasNext() {
            return WizardPosition<size;
        }

        public T next() {
            T returnItem=items[WizardPosition];
            WizardPosition++;
            return returnItem;
        }
    }

    public static void main(String[] args) {
        Set<Integer> javaset = new HashSet<>();
        javaset.add(5);
        javaset.add(23);
        javaset.add(42);
        /**
         for (int i : javaset) {
         System.out.println(i);
         }
         */
        Iterator<Integer> seer = javaset.iterator();
        while (seer.hasNext()) {
            System.out.println(seer.next());
        }

        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);
        /**
         for (int i : aset) {
         System.out.println(i);
         }
         */
        Iterator<Integer> aseer = aset.iterator();
        while (aseer.hasNext()) {
            System.out.println(aseer.next());
        }

        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());
    }

    /*
     * Also to do: 1. Make ArraySet implement the Iterable<T> interface. 2.
     * Implement a toString method. 3. Implement an equals() method.
     */
}
