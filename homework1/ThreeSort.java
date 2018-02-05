// ThreeSort.java: Takes three integers as command-line arguments and writes
// them in ascending order, separated by spaces.

import edu.princeton.cs.algs4.StdOut;

public class ThreeSort {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        int min = Math.min(a, Math.min(b, c));
        int max = Math.max(a, Math.max(b, c));
        int median = (a + b + c)/ 3;
        //int median2= Math.min(median, c); 
        StdOut.println(min + " " + median + " " + max);

            
            
        }
        
        
        
        
        
    }

