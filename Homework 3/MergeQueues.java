import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

@SuppressWarnings("rawtypes")
public class MergeQueues {
    // Return true if v is less than w and false otherwise.
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // Merge and return the two sorted queues as a single sorted queue.
    @SuppressWarnings("rawtypes")
    private static Queue<Comparable> merge(Queue<Comparable> q1,
                                           Queue<Comparable> q2) {
        Queue<Comparable> merged = new Queue<>();
        Queue<Comparable> tempq1 = new Queue<>();
        Queue<Comparable> tempq2 = new Queue<>();

        for (Comparable s : q1) {
            tempq1.enqueue(s);

        }
        for (Comparable s : q2) {
            tempq2.enqueue(s);

        }
        while (!tempq1.isEmpty() && !tempq2.isEmpty()) {
            Comparable one = tempq1.peek();
            Comparable two = tempq2.peek();

            if (less(one, two)) {
                merged.enqueue(tempq1.dequeue());
            } else {
                merged.enqueue(tempq2.dequeue());
            }

        }
        while (!tempq1.isEmpty()) {
            merged.enqueue(tempq1.dequeue());
        }
        while (!tempq2.isEmpty()) {
            merged.enqueue(tempq2.dequeue());
        }

        return merged;

    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Queue<Comparable> q1 = new Queue<Comparable>();
        Queue<Comparable> q2 = new Queue<Comparable>();
        for (String s : a) {
            if (StdRandom.bernoulli(0.5)) {
                q1.enqueue(s);
            } else {
                q2.enqueue(s);
            }
        }
        int s1 = q1.size(), s2 = q2.size();
        StdOut.println(merge(q1, q2));
        assert q1.size() == s1 && q2.size() == s2;
    }
}
