package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int gridN;  // Length of the grid.
    private int numberOfOpenSites;  // Total open sites in the grid.
    private boolean[] isOpen;   // 1-D array to store boolean value of the index, checks if sites are open or not.
    private WeightedQuickUnionUF uf;    // A set that checks whether the water can reach to bottom.
    private WeightedQuickUnionUF preventBackWashuf;   // A set without including virtual bottom site.
    private int indexOfVirtualTop;
    private int indexOfVirtualBottom;

    /** Translate 2 dimensional array to integer index. */
    private int xyTo1D(int row, int col) {
        return row * gridN + col;
    }

    /** Checks if the index is valid or not. */
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
        preventBackWashuf = new WeightedQuickUnionUF(N * N + 1);    // Includes only virtual top site.
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
            isOpen[xyTo1D(row, col)] = true;
            numberOfOpenSites += 1;
            /** Checks if neighboring block is open or not, connects them if open. */
            //top
            if (isValid(row - 1, col) && isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                preventBackWashuf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
            //bottom
            if (isValid(row + 1, col) && isOpen(row + 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                preventBackWashuf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
            //right
            if (isValid(row, col + 1) && isOpen(row, col + 1)) {
                uf.union((xyTo1D(row, col)), xyTo1D(row, col + 1));
                preventBackWashuf.union((xyTo1D(row, col)), xyTo1D(row, col + 1));
            }
            //left
            if (isValid(row, col - 1) && isOpen(row, col - 1)) {
                uf.union((xyTo1D(row, col)), xyTo1D(row, col - 1));
                preventBackWashuf.union((xyTo1D(row, col)), xyTo1D(row, col - 1));
            }
            /** If the cell is at top or bottom, connect it to the virtual top or virtual bottom. */
            if (row == 0) {
                uf.union(xyTo1D(row, col), indexOfVirtualTop);
                preventBackWashuf.union(xyTo1D(row, col), indexOfVirtualTop);
            } else if (row == gridN - 1) {
                uf.union((xyTo1D(row, col)), indexOfVirtualBottom);
            }
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
        return preventBackWashuf.connected(indexOfVirtualTop, xyTo1D(row, col));
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
