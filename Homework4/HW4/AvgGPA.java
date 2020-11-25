// AvgGPA.java: Reads from standard input a list of letter grades and computes
// and prints the average GPA (the average of the numbers corresponding to
// the grades).

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class AvgGPA {
    public static void main(String[] args) {
        ArrayST<String, Double> st = new ArrayST<String, Double>();
        String[] al;
        al = StdIn.readAllStrings();
        st.put("A+", 4.33);
        st.put("A", 4.0);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3.0);
        st.put("B-", 2.67);
        st.put("C+", 2.33);
        st.put("C", 2.0);
        st.put("C-", 1.67);
        st.put("D", 1.0);
        st.put("F", 0.0);

        double sum = 0;
        for (int i = 0; i < al.length; i++) {
            sum += st.get(al[i]);
        }
        StdOut.println(sum / al.length);
    }
}
