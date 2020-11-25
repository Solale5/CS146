// Models an N-by-N percolation system.

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Percolation {
    //create 2d array of booleans
    private Boolean[][] grid;
    //2 union find objects created, one for testing percolation and the other for dealing with backwash that the bottom virtual site
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;


    private int openSites;
    private int N;
    private int virtualTopID;
    private int virtualBotID;


    // Creates an N-by-N grid, with all sites blocked.
    public Percolation(int N) {

        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.openSites = 0;
        this.grid = new Boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;

            }
        }

        // indexes of the virtual top and bottom spots in the union find array
        virtualTopID = 0;
        virtualBotID = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);


    }


    // Open site (row, col) if it is not open already.
    public void open(int row, int col) {
        // illegal arg if row and col are out of range of 0 - N-1
        if (row > N - 1 || row < 0 || col > N - 1 || col < 0) {
            throw new IllegalArgumentException();
        }
        // check if already open
        if (!isOpen(row, col)) {
            // open spot and increment open sites
            this.grid[row][col] = true;
            openSites++;
            //check for open neighbors and connect them
            connectNeighbors(row, col);
            // if the sites opened are in the top or bottom row, connect them to the virtual site
            if (row == 0) {
                int s = encode(row, col);
                uf.union(s, virtualTopID);
                uf2.union(s, virtualTopID);
            }
            if (row == N - 1) {
                int a = encode(row, col);
                uf.union(a, virtualBotID);
            }
        }
    }

    /**
     * helper method to test neighbors
     *
     * @param row row of original point that was opened
     * @param col column of original point that was opened
     */
    public void connectNeighbors(int row, int col) {
        if (row - 1 >= 0 && isOpen(row - 1, col)) {

            // assign uf array index to two ints representing the spots
            int ogSpot = encode(row, col);
            int topSpot = encode(row - 1, col);
            // create union between those two points
            uf.union(ogSpot, topSpot);
            uf2.union(ogSpot, topSpot);

        }
        //repeat top portion three more times to test the 4 neighbors
        if (row + 1 <= N - 1 && isOpen(row + 1, col)) {

            int ogSpot = encode(row, col);
            int botSpot = encode(row + 1, col);
            uf.union(ogSpot, botSpot);
            uf2.union(ogSpot, botSpot);

        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {

            int ogSpot = encode(row, col);
            int leftSpot = encode(row, col - 1);
            uf.union(ogSpot, leftSpot);
            uf2.union(ogSpot, leftSpot);

        }
        if (col + 1 <= N - 1 && isOpen(row, col + 1)) {
            int ogSpot = encode(row, col);
            int rightSpot = encode(row, col + 1);
            uf.union(ogSpot, rightSpot);
            uf2.union(ogSpot, rightSpot);


        }
    }

    // Is site (row, col) open?
    public boolean isOpen(int row, int col) {
        // illegal arg if row and col are out of range of 0 - N-1
        if (row > N - 1 || row < 0 || col > N - 1 || col < 0) {
            throw new IllegalArgumentException();
        }
        return this.grid[row][col] == true;
    }

    // Is site (row, col) full?
    public boolean isFull(int row, int col) {
        // illegal arg if row and col are out of range of 0 - N-1
        if (row > N - 1 || row < 0 || col > N - 1 || col < 0) {
            throw new IllegalArgumentException();
        }
        // get the 1D coordinates
        int i = encode(row, col);
        int j = virtualTopID;
        // check  connection to the virtual top node
        return uf2.find(i) == uf2.find(j) && isOpen(row, col);
    }

    // Number of open sites.
    public int numberOfOpenSites() {

        return openSites;
    }

    // Does the system percolate?
    public boolean percolates() {
        // if the top and bottom site have the same parent they are connected
        return uf.find(virtualTopID) == uf.find(virtualBotID);

    }


    // An integer ID (1...N) for site (row, col).
    private int encode(int row, int col) {

        int n = this.grid.length;

        return (n * row) + col + 1;

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
        } else {
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
