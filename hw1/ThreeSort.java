// ThreeSort.java: Takes three integers as command-line arguments and writes
// them in ascending order, separated by spaces.

import edu.princeton.cs.algs4.StdOut;

public class ThreeSort {
    public static void main(String[] args) {
        int firstRead = Integer.parseInt(args[0]);
        int secondRead = Integer.parseInt(args[1]);
        int thirdRead = Integer.parseInt(args[2]);

        int LargestOfFirstTwo = Math.max(firstRead, secondRead);
        int smallestOfFirstTwo = Math.min(firstRead, secondRead);
        if (thirdRead < smallestOfFirstTwo) {
            StdOut.print(thirdRead);
            StdOut.print(smallestOfFirstTwo);
            StdOut.println(LargestOfFirstTwo);
        } else if (thirdRead > LargestOfFirstTwo) {
            StdOut.print(smallestOfFirstTwo);
            StdOut.print(LargestOfFirstTwo);
            StdOut.println(thirdRead);
        } else {
            StdOut.print(smallestOfFirstTwo);
            StdOut.print(thirdRead);
            StdOut.println(LargestOfFirstTwo);
        }

    }

}

