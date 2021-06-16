import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private static final int R = 256;        // extended ASCII

    private Node root;  // root of trie
    private int num;    // number of keys in trie

    /** R-way trie node. */
    private static class Node {
        private Node[] next = new Node[R];
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root.next = new Node[R];
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(root, key, 0) != null;
    }

    /** Helper function that get the Nodes with certain key. */
    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        } else {
            char c = key.charAt(d);
            return get(x.next[c], key, d + 1);
        }
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        root = add(root, key, 0);
    }

    /** Helper method that added key into node. */
    private Node add(Node x, String key, int d) {
        if (x == null) {
            x = new Node();
        }
        if (key.length() == d) {
            num++;
        } else {
            char c = key.charAt(d);
            x.next[c] = add(x.next[c], key, d + 1);
        }
        return x;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        ArrayList<String> results = new ArrayList<>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, ArrayList<String> results) {
        if (x == null) return;
        results.add(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
