import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {

    private final double[] temp;

    // Perform T independent experiments (Monte Carlo simulations) on an 
    // N-by-N grid.
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        
        temp = new double[T];
    
    for (int k = 0; k < T; k++) {
            Percolation p = new Percolation(N);
            
            int openSites = 0;
            while (!p.percolates()) {
                int i = StdRandom.uniform(N);
                int j = StdRandom.uniform(N);
                //if the side is already the control goes back to loop
                if (p.isOpen(i, j)) {
                    continue;
                }
                //else just open the site
                p.open(i, j);
                openSites++;
            }
            temp[k] = (openSites * 1.0) / (N * N);
        }

    }
    
    // Sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(temp);
    }

    // Sample standard deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(temp);
    }

    // Low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.pow(temp.length, 0.5);
    }

    // High endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.pow(temp.length, 0.5);
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
