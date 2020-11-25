import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


// Takes a command-line integer k; reads in a sequence of strings from
// standard input; and prints out exactly k of them, uniformly at random.
// Note that each item from the sequence is printed out at most once.
public class Subset {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
       
        ResizingArrayRandomQueue r = new ResizingArrayRandomQueue();

        while (!StdIn.isEmpty()) {
            r.enqueue(StdIn.readString());
        }

        while (k-- > 0)
            StdOut.println(r.dequeue());


    }
}
