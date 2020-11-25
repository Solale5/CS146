// Spell.java: Takes a command-line argument specifying the name of the file
// containing common misspellings (a line-oriented file with each
// comma-separated line containing a misspelled word and the correct spelling),
// then reads text from standard input and prints out the misspelled words in
// the text along with the line numbers where they occurred and their correct
// spellings.

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

public class Spell {
    public static void main(String[] args) {
        // create an in object
        In in = new In(args[0]);
        // create hashtable
        SeparateChainingHashST<String, String> sch = new SeparateChainingHashST<String, String>();

        //loop to enter things into our hashtable
        while (in.hasNextLine()) {
            //store first line of misspellings.txt
            String mispellings = in.readLine();
            //split lines into an array of size two,  [misspellings, correct spellings]
            String[] keyAndVal = mispellings.split(",");
            // put keyAndVal into a hashTable
            sch.put(keyAndVal[0], keyAndVal[1]);
        }

        In in2 = new In(args[1]);
        int lineCount = 1;
        //loop to read war_and_peace.txt
        while (in2.hasNextLine()) {
            //read a line from wap txt
            String line = in2.readLine();
            //split the lines by a space to isolate words
            String[] lines = line.split(" ");
            // loop through the words and if it is a key in the hashtable(if it is mispelled)
            // print out the word and the line number
            for (String s : lines) {

                if (sch.contains(s)) {
                    StdOut.println(s + ":" + lineCount + " " + "->" + sch.get(s));
                }

            }
            lineCount++;

        }
    }
}
