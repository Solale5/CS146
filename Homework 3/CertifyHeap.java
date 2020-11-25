import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("rawtypes")
public class CertifyHeap {
    // Return true of v is less than w and false otherwise.
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Return true if a[] represents a maximum-ordered heap and false
    // otherwise. Remember to index from 1.
    private static boolean maxOrderedHeap(Comparable[] a) {
        for (int i = 1; i < a.length / 2; i++) {
            int j = 2 * i;
            int k = j + 1;

            if (less(a[i], a[j]) || less(a[i], a[k])) {
                return false;
            }

        }
        return true;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println(maxOrderedHeap(a));
    }
}
