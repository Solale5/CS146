import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {
    int T;
    double[] p;

    // Perform T independent experiments (Monte Carlo simulations) on an
    // N-by-N grid.
    public PercolationStats(int N, int T) {
        // if N and T are zero or Less throw exception
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.T = T;
        this.p = new double[T];

        // do T amount of experiments
        for (int i = 0; i < T; i++) {
            // declare new perc object for each experiment
            Percolation perc = new Percolation(N);
            //  s counts total # of sites open per experiment
            double s = 0;
            // open sites until perc object percolates
            while (!perc.percolates()) {
                //assign to random numbers between [0,n)
                int k = StdRandom.uniform(N);
                int j = StdRandom.uniform(N);
                //if that site at k,j isnt open, open it and increment s (which keeps count of open sites)
                if (!perc.isOpen(k, j)) {
                    perc.open(k, j);
                    s++;
                }
            }

            // at the end of each experiment divide the number of open sites by total sites in the grid and add it to array p
            p[i] = s / (N * N);

        }


    }

    // Sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(this.p);
    }

    // Sample standard deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(this.p);

    }

    // Low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / (Math.sqrt(T)));
    }

    // High endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / (Math.sqrt(T)));
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean           = %f\n", stats.mean());
        StdOut.printf("stddev         = %f\n", stats.stddev());
        StdOut.printf("confidenceLow  = %f\n", stats.confidenceLow());
        StdOut.printf("confidenceHigh = %f\n", stats.confidenceHigh());
    }
}
