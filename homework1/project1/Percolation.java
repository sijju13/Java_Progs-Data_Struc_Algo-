import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// Models an N-by-N percolation system.
public class Percolation {
   private int N;
   private boolean[][] grid; 
   private int openSites;
   private WeightedQuickUnionUF uf; 
   private WeightedQuickUnionUF uf2;

    
    // Create an N-by-N grid, with all sites blocked.
    public Percolation(int N) {
        this.N = N;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF((N*N)+2);
        uf2 = new WeightedQuickUnionUF((N*N)+1);
        
        if (N < 1) {
            throw new IllegalArgumentException();
        }
            
        for (int i = 0; i < N + 1; i++) {
            uf.union(0, i);
        }
        
        for (int i = N*N - N+1; i <= N*N; i++) {
            uf.union(N*N+1, i);
        }
        
        for (int i = 0; i < N+1; i++) {
            uf2.union(0, i);
        }
    }

    // Open site (i, j) if it is not open already.
    public void open(int i, int j) {
        //checks if the arguments passed are valid index values. 
        if (i < 0 || i > this.N-1 || j < 0 || j > this.N-1) {
            throw new IndexOutOfBoundsException("ERROR");
        }
        
        if (isOpen(i, j)) {
            return; 
        }
        
        //open the box
        if (!grid[i][j]) {
            grid[i][j] = true;
            openSites += 1;
        }
        
        //checking if the i,j value doesn't lie in bottom row 
        if (i != this.N - 1) {
            if (isOpen(i + 1, j)) {
                uf.union(encode(i, j), encode(i+1, j));
                uf2.union(encode(i, j), encode(i+1, j));
            }           
        }
        
        if (i != 0) {
            if (isOpen(i - 1, j)) {
                uf.union(encode(i, j), encode(i - 1, j));
                uf2.union(encode(i, j), encode(i - 1, j));                
            }
        }
        
        if (j != 0) {
            if (isOpen(i, j - 1)) {
                uf.union(encode(i, j), encode(i, j - 1));
                uf2.union(encode(i, j), encode(i, j - 1));
            }           
        }
        
        if (j != this.N - 1) {
            if (isOpen(i, j + 1)) {
                uf.union(encode(i, j), encode(i, j + 1));
                uf2.union(encode(i, j), encode(i, j + 1));
            }
        }
    }

    // Is site (i, j) open?
    public boolean isOpen(int i, int j) {
        if (i < 0 || i > this.N-1 || j < 0 || j > this.N-1) {
            throw new IndexOutOfBoundsException("ERROR");
        }
        
        return grid[i][j];
    }

    // Is site (i, j) full?
    public boolean isFull(int i, int j) {
         if (i < 0 || i > this.N-1 || j < 0 || j > this.N-1) {
            throw new IndexOutOfBoundsException("ERROR");
        }
        return isOpen(i, j) && uf2.connected(encode(i, j), 0); 
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        return openSites; 
    }

    // Does the system percolate?
    public boolean percolates() {
         return uf.connected(0, (this.N * this.N +1));
    }

    // An integer ID (1...N) for site (i, j).
    private int encode(int i, int j) {
        return (i*this.N+j+1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        }
        else {
            StdOut.println("does not percolate");
        }
        
        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}
