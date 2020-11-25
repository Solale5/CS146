// ThreeDice.java: Writes the sum of three random integers between 1 and 6, such
// as you might get when rolling three dice.

public class ThreeDice {
    public static void main(String[] args) {
        int firstRoll = StdRandom.uniform(1, 7);
        int secondRoll = StdRandom.uniform(1, 7);
        int thirdRoll = StdRandom.uniform(1, 7);

        StdOut.print(firstRoll + secondRoll + thirdRoll);
    }
}
