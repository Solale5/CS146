// writes the wind chill w, calculated as w=35.74+0.6215t+(0.4275t-35.75)v^0.16.

import edu.princeton.cs.algs4.StdOut;

public class WindChill {
    public static void main(String[] args) {

        double t = Double.parseDouble(args[0]);
        double v = Double.parseDouble(args[1]);


        double w = ((0.4275 * t) - 35.75) * (Math.pow(v, 0.16)) + (0.6215 * t) + 35.74;
        StdOut.println(w);
    }

}
