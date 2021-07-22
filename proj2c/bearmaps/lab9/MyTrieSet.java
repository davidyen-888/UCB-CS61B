package bearmaps.lab9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Modified version of TrieSet from lab9. */
public class MyTrieSet {
    private Node root;

    private static class Node {
        private boolean iskey;
        private HashMap<Character, Node> map;

        Node(boolean b) {
            this.iskey = b;
            this.map = new HashMap<>();
        }
    }

    public MyTrieSet() {
        root = new Node(false);
    }

    /** Clears all items out of Trie */
    public void clear() {
        root = new Node(false);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        Node n = get(key);
        return n != null && n.iskey;
    }

    /** Helper function that get the Nodes with certain key. */
    private Node get(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return null;
            }
            curr = curr.map.get(c);
        }
        return curr;
    }

    /** Inserts string KEY into Trie */
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        addHelper(key);
    }

    /** Helper method that added key into node. */
    private Node addHelper(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.iskey = true;
        return curr;
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        Node n = get(prefix);
        collect(n, prefix, results);
        return results;
    }

    private void collect(Node n, String prefix, List<String> results) {
        if (n == null) return;
        if (n.iskey) {
            results.add(prefix);
        }
        for (char c : n.map.keySet()) {
            collect(n.map.get(c), prefix + c, results);
        }
    }

    /**
     * Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
