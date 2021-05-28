package hw2;

public class Percolation {
    private int gridN;  // Length of the grid.

    private boolean isValid(int row, int col) {
        if (row < 0 || col < 0 || row >= gridN || col >= gridN) {
            return false;
        }
        return true;
    }

    /** Creates N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N<=0){
            throw new IllegalArgumentException("N must be positive")
        }
    }

    /** Opens the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (!isValid(row, col)){
            throw new IndexOutOfBoundsException("Outside of the grid")
        }

    }

    /** Is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)){
            throw new IndexOutOfBoundsException("Outside of the grid")
        }

    }

    /** Is the site (row, col) full? */
    /** Check for a connection to top site. */
    public boolean isFull(int row, int col) {
        if (!isValid(row, col)){
            throw new IndexOutOfBoundsException("Outside of the grid")
        }

    }

    /** Number of open sites */
    public int numberOfOpenSites() {

    }

    /** Does the system percolate? */
    /** Check for connection between top and bottom site. */
    public boolean percolates() {

    }

    /** Translate 2 dimensional array to integer. */
    private int xyTo1D(int row, int col) {
        return row * gridN + col;
    }

    /** Use for unit testing (not required, but keep this here for the autograder) */
    public static void main(String[] args) {

    }
}
