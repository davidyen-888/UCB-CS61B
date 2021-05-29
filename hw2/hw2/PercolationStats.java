/******************************************************************************
 * Monte Carlo simulation. To estimate the percolation threshold, consider the following computational experiment:
 *   #Initialize all sites to be blocked.
 *   #Repeat the following until the system percolates:
 *      > Choose a site uniformly at random among all blocked sites.
 *      > Open the site.
 *      > The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.
 ******************************************************************************/
package hw2;

import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private double[] percolationNum;

    /** Perform T independent experiments on an N-by-N grid. */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T should be positive");
        }
        percolationNum = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);

        }

    }

    /** Sample mean of percolation threshold. */
    public double mean() {
        return StdStats.mean(percolationNum);
    }

    /** Sample standard deviation of percolation threshold. */
    public double stddev() {
        return StdStats.stddev(percolationNum);
    }

    /** Low endpoint of 95% confidence interval. */
    public double confidenceLow() {
        return 0;
    }

    /** High endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        return 0;
    }
}
