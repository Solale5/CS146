// A data type representing a six-sided die.

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Die implements Comparable<Die> {
    private int value; // face value

    /**
     * constructor for die object
     */
    public Die() {


        this.value = StdRandom.uniform(1, 7);
    }


    // Roll the die.
    public void roll() {
        value = StdRandom.uniform(1, 7);
    }

    // Face value of the die.
    public int value() {
        return value;
    }

    // Does the die have the same face value as that?
    public boolean equals(Die that) {
        return this.value == that.value;
    }

    // A negative integer, zero, or positive integer depending on whether this
    // die's value is less than, equal to, or greater than the that die's value.
    public int compareTo(Die that) {
        int valueComp = this.value - that.value;
        return valueComp;

    }

    // A string representation of the die giving the current face value.
    public String toString() {
        String s = "";
        if (this.value == 1) {
            s += "*" + "\n";
        }
        if (this.value == 2) {
            s += "* *" + "\n";
        }
        if (this.value == 3) {
            s += "*" + "\n" + " " + "*" + "\n" + "  " + "*" + "\n";
        }
        if (this.value == 4) {
            s += "* *" + "\n" + "* *" + "\n";
        }
        if (this.value == 5) {
            s += "* *" + "\n" + " *" + "\n" + "* *" + "\n";
        }
        if (this.value == 6) {
            s += "***" + "\n" + "***" + "\n";
        }
        return s;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);
        Die a = new Die();
        a.roll();
        while (a.value() != x) {
            a.roll();
        }
        Die b = new Die();
        b.roll();
        while (b.value() != y) {
            b.roll();
        }
        Die c = new Die();
        c.roll();
        while (c.value() != z) {
            c.roll();
        }

        StdOut.println(a.equals(b));
        StdOut.println(b.equals(c));
        StdOut.println(a.compareTo(b));
        StdOut.println(b.compareTo(c));
        StdOut.println(a);
        

    }
}
