public class UnionFind {

    // TODO - Add instance variables?
    private int[] set;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        set = new int[n];
        for (int i = 0; i < n; i++) {
            set[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int v1) {
        if (v1 < 0 || v1 > set.length - 1) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        return -parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return set[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        int v1Root = find(v1);
        int v2Root = find(v2);
        if (connected(v1Root, v2Root)) {
            return;
        }
        // Always link root of smaller tree to larger tree.
        if (sizeOf(v1Root) > sizeOf(v2Root)) {
            set[v1Root] -= sizeOf(v2Root);
            set[v2Root] = v1Root;
        } else {
            set[v2Root] -= sizeOf(v1Root);
            set[v1Root] = v2Root;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int v1) {
        // TODO
        /**while (set[v1] >= 0) {
         v1 = set[v1];
         }
         return v1;*/
        validate(v1);
        if (set[v1] < 0) {
            return v1;
        } else {
            set[v1] = find(parent(v1));
            return set[v1];
        }
    }

}