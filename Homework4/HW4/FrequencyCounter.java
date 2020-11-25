// FrequencyCounter.java: Reads in a command-line integer and sequence of words
// from standard input and prints out all the words (whose length exceeds the
// threshold) that occur most frequently to standard output. It also prints out
// the number of words whose length exceeds the threshold and the number of
// distinct such words.

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {
    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<String, Integer>();
        String[] al;
        al = StdIn.readAllStrings();

        for (String s : al) {

            if (st.contains(s)) {
                int frequencyVal = st.get(s);
                frequencyVal++;
                st.put(s, frequencyVal);

            } else {
                st.put(s, 1);
            }
        }
        int biggestVal = 0;

        for (String s : st.keys()) {
            int test = st.get(s);
            if (test > biggestVal) {
                biggestVal = test;
            }
        }

        for (String s : st.keys()) {
            if (st.get(s) == biggestVal) {
                StdOut.print(s + " ");
            }
        }
        StdOut.print(biggestVal + "\n");
        StdOut.println("distinct = " + st.size());
        StdOut.println("distinct = " + al.length);

    }
}
