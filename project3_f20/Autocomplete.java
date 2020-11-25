import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

// A data type that provides autocomplete functionality for a given set of
// string and weights, using Term and BinarySearchDeluxe.
public class Autocomplete {
    Term[] theTerms;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        int n = terms.length;
        theTerms = new Term[n];
        for (int i = 0; i < n; i++) {
            theTerms[i] = terms[i];
        }
        Arrays.sort(theTerms);
    }

    ;

    // All terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
        int k = BinarySearchDeluxe.firstIndexOf(theTerms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
        int b = BinarySearchDeluxe.lastIndexOf(theTerms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
        Term[] matchedTerms = new Term[1 + b - k];
        for (int i = 0; i < matchedTerms.length; i++)
            matchedTerms[i] = theTerms[k++];

        Arrays.sort(matchedTerms, Term.byReverseWeightOrder());

        return matchedTerms;
    }

    // The number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        int k = BinarySearchDeluxe.firstIndexOf(theTerms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
        int b = BinarySearchDeluxe.lastIndexOf(theTerms, new Term(prefix), Term.byPrefixOrder(prefix.length()));

        int difference = b - k;
        return difference;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println(results[i]);
            }
        }
    }
}
