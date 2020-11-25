// Ramanujan.java: Prints the integers <= N (command-line argument) that can be
// expressed as the sum of two distinct cubes.

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Ramanujan {
    // A data type that encapsulates a pair of numbers (i, j)
    // and the sum of their cubes, ie, i^3 + j^3.
    private static class Pair implements Comparable<Pair> {
        private int i;          // first element of the pair
        private int j;          // second element of the pair
        private int sumOfCubes; // i^3 + j^3

        // Construct a pair (i, j).
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
            sumOfCubes = i * i * i + j * j * j;
        }

        // Compare this pair to the other by sumOfCubes.
        public int compareTo(Pair other) {
            return sumOfCubes - other.sumOfCubes;
        }
    }

    public static void main(String[] args) {
        MinPQ<Pair> m = new MinPQ<>();
        double N = Double.parseDouble(args[0]);
        double ncuberoot = Math.cbrt(N);
        int cubrootOfN = (int) ncuberoot;

        for (int i = 1; i < cubrootOfN; i++) {
            m.insert(new Pair(i, i + 1));
        }


        while (!m.isEmpty()) {

            Pair r = m.delMin();
            if (m.isEmpty()) {
                break;
            }
            Pair l = m.min();


            if (l.compareTo(r) == 0 && l.sumOfCubes <= N) {
                StdOut.println(l.sumOfCubes + " = " + l.i + "^3 " + l.j + "^3 " + " =" + r.i + "^3 " + r.j + "^3 ");
            }
            if (l.j < cubrootOfN) {
                Pair temp = new Pair(r.i, r.j + 1);
                m.insert(temp);
            }


        }


    }
}
