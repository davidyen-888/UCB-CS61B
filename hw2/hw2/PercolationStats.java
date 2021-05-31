/******************************************************************************
 * Monte Carlo simulation. To estimate the percolation threshold, consider the following computational experiment:
 *   #Initialize all sites to be blocked.
 *   #Repeat the following until the system percolates:
 *      > Choose a site uniformly at random among all blocked sites.
 *      > Open the site.
 *      > The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.
 ******************************************************************************/
package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] percolationNum;

    /** Perform T independent experiments on an N-by-N grid. */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T should be positive");
        }
        /* Monte Carlo simulation. */
        percolationNum = new double[T];
        for (int i = 0; i < T; i++) {   // T times experiments
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            /* Percolation threshold */
            percolationNum[i] = (double) p.numberOfOpenSites() / (N * N);
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
        double mean = mean();
        double stdev = stddev();
        return mean - (1.96 * stdev) / Math.pow(percolationNum.length, 0.5);
    }

    /** High endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        double mean = mean();
        double stdev = stddev();
        return mean + (1.96 * stdev) / Math.pow(percolationNum.length, 0.5);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        int N = 20;
        int T = 1000;
        PercolationStats ps = new PercolationStats(N, T, pf);
        System.out.println("N = " + N + ", T = " + T);
        System.out.println("Estimated percolation threshold = " + ps.mean());
        System.out.println("Estimated standard deviation = " + ps.stddev());
        System.out.println("95% confidence interval = " + "[" + ps.confidenceLow() + ", " + ps.confidenceHigh() + "]");
    }
}
