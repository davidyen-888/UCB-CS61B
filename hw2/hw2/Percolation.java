package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int gridN;  // Length of the grid.
    private int numberOfOpenSites;
    private boolean[] isOpen;
    private WeightedQuickUnionUF uf;    // A set that checks whether the water can reach to bottom.
    private int indexOfVirtualTop;
    private int indexOfVirtualBottom;

    /** Translate 2 dimensional array to integer. */
    private int xyTo1D(int row, int col) {
        return row * gridN + col;
    }

    private boolean isValid(int row, int col) {
        if (row < 0 || col < 0 || row >= gridN || col >= gridN) {
            return false;
        }
        return true;
    }

    /** Creates N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be positive");
        }
        gridN = N;
        uf = new WeightedQuickUnionUF(N * N + 2); // Includes virtual top and bottom sites.
        isOpen = new boolean[N * N];
        indexOfVirtualTop = N * N;
        indexOfVirtualBottom = N * N + 1;
        numberOfOpenSites = 0;  // All sites are initially blocked.
    }

    /** Opens the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException("Outside of the grid");
        }
        if (!isOpen[xyTo1D(row, col)]) {

        }

    }

    /** Is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException("Outside of the grid");
        }
        return isOpen[xyTo1D(row, col)];
    }

    /** Is the site (row, col) full? */
    /** Check for a connection to top site. */
    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException("Outside of the grid");
        }
        return uf.connected(indexOfVirtualTop, xyTo1D(row, col));
    }

    /** Number of open sites */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /** Does the system percolate? */
    /** Check for connection between top and bottom site. */
    public boolean percolates() {
        return uf.connected(indexOfVirtualTop, indexOfVirtualBottom);
    }

    /** Use for unit testing (not required, but keep this here for the autograder) */
    public static void main(String[] args) {

    }
}
