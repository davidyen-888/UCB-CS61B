import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private static final int INIT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private ArrayList[] alHashtable; // ArrayList that stores hash table.
    private int capacity;   // HashMap capacity.
    private int pairNumber; // Number of pairs in the HashMap.
    private double loadFactor;  // Load factor of myHashMap.
    private HashSet<K> allKeys; // Set of keys in myHashMap.


    /** An empty map with default initial capacity and load factor 0.75. */
    public MyHashMap() {
        this(INIT_CAPACITY, LOAD_FACTOR);
    }

    /** An empty map with initialSize initial size and load factor 0.75. */
    public MyHashMap(int initialSize) {
        this(initialSize, LOAD_FACTOR);
    }

    private class pairKV {
        private K key;
        private V value;

        private pairKV(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * A new, empty mapping using a hash table that initially has
     * initialSize initial size, and maintains a load factor <= LOAD_FACTOR.
     */
    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize < 1 || loadFactor <= 0.0) {
            throw new IllegalArgumentException();
        }
        capacity = initialSize;
        loadFactor = loadFactor;
        pairNumber = 0;
        allKeys = new HashSet<K>();
        alHashtable = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            alHashtable[i] = new ArrayList<pairKV>();
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        pairNumber = 0;
        allKeys = new HashSet<K>();
        alHashtable = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            alHashtable[i] = new ArrayList<pairKV>();
        }
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return allKeys.contains(key);
    }

    /** Hash function for keys - returns value between 0 and capacity-1. */
    private int hashIndex(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        ArrayList<pairKV> arrayList = alHashtable[hashIndex(key)];
        if (arrayList != null) {
            for (pairKV x : arrayList) {
                if (x.key.equals(key)) {
                    return x.value;
                }
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return pairNumber;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        ArrayList<pairKV> arrayList = alHashtable[hashIndex(key)];
        if(!containsKey(key)){
            arrayList.add(new pairKV(key, value));
            allKeys.add(key);
            pairNumber++;
        }else {
            for(pairKV x:arrayList){
                if(x.key==key){
                    x.value=value;
                }
            }
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return allKeys;
    }

    @Override
    public Iterator<K> iterator() {
        return allKeys.iterator();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. Throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. Throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
