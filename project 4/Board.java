import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

// Models a board in the 8-puzzle game or its generalization.
public class Board {
    private int[][] board;

    private int N;
    int hamming;
    int manhattan;
    private static final int SPACE = 0;
    // space coordinate x
    private int x;

    // space coordinate y
    private int y;

    // Construct a board from an N-by-N array of tiles, where
    // tiles[i][j] = tile at row i and column j, and 0 represents the blank
    // square.
    public Board(int[][] tiles) {
        N = tiles.length;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tiles[i][j];

                if (board[i][j] == 0) {
                    x = i;
                    y = j;
                } else {
                    int gx = (board[i][j] - 1) / N;
                    int gy = (board[i][j] - 1) % N;
                    manhattan += Math.abs(i - gx) + Math.abs(j - gy);

                    int pos = i * N + j;
                    if ((pos + 1) != board[i][j]) {
                        hamming++;
                    }
                }
            }
        }

    }


    // Tile at row i and column j.
    public int tileAt(int i, int j) {
        return board[i][j];
    }

    // Size of this board.
    public int size() {
        return (N * N);
    }

    // Number of tiles out of place.
    public int hamming() {
        return hamming;
    }

    // Sum of Manhattan distances between tiles and goal.
    public int manhattan() {
        return manhattan;
    }

    // Is this board the goal board?
    public boolean isGoal() {
        return manhattan == 0;
    }

    // Is this board solvable?
    public boolean isSolvable() {
        int inv = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == x && j == y) {
                    continue;
                }
                int num = tileAt(i, j);
                for (int ci = i; ci < N; ci++) {
                    for (int cj = 0; cj < N; cj++) {
                        if (ci == i && cj <= j) {
                            continue;
                        }
                        if (ci == x && cj == y) {
                            continue;
                        }
                        if (num > tileAt(ci, cj)) {
                            inv++;
                        }
                    }
                }
            }
        }
        if (N % 2 == 0)
            return (inv + x) % 2 == 1;
        else
            return inv % 2 == 0;
    }

    // Does this board equal that?
    public boolean equals(Board that) {

        if (that == null) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != that.board[i][j]) {
                    return false;
                }
            }
        }
        return true;

    }

    // All neighboring boards.
    public Iterable<Board> neighbors() {
        Queue<Board> q = new Queue<Board>();
        if (x > 0) {
            int temporary = board[x - 1][y];
            board[x - 1][y] = 0;
            board[x][y] = temporary;
            q.enqueue(new Board(board));
            board[x - 1][y] = temporary;
            board[x][y] = 0;
        }
        if (x < (N - 1)) {
            int temporary = board[x + 1][y];
            board[x + 1][y] = 0;
            board[x][y] = temporary;
            q.enqueue(new Board(board));
            board[x + 1][y] = temporary;
            board[x][y] = 0;
        }
        if (y > 0) {
            int temporary = board[x][y - 1];
            board[x][y - 1] = 0;
            board[x][y] = temporary;
            q.enqueue(new Board(board));
            board[x][y - 1] = temporary;
            board[x][y] = 0;
        }
        if (y < (N - 1)) {
            int temporary = board[x][y + 1];
            board[x][y + 1] = 0;
            board[x][y] = temporary;
            q.enqueue(new Board(board));
            board[x][y + 1] = temporary;
            board[x][y] = 0;
        }
        return q;
    }

    // String representation of this board.
    public String toString() {
        String s = N + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s += String.format("%2d", board[i][j]);
                if (j < N - 1) {
                    s += " ";
                }
            }
            if (i < N - 1) {
                s += "\n";
            }
        }
        return s;
    }


    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board board = new Board(tiles);
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());
        StdOut.println(board.isGoal());
        StdOut.println(board.isSolvable());
        for (Board neighbor : board.neighbors()) {
            StdOut.println(neighbor);
        }
    }
}
