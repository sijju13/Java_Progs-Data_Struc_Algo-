// ThreeDice.java: Writes the sum of three random integers between 1 and 6, such
// as you might get when rolling three dice.

import edu.princeton.cs.algs4.StdOut;


public class ThreeDice {
    public static void main(String[] args) {
        int a = (int) (Math.random()* 6)+1;
        int b = (int) (Math.random()* 6)+1;
        int c = (int) (Math.random()* 6)+1;
        int sum = a + b + c;
        StdOut.println(sum);
    }
      }
