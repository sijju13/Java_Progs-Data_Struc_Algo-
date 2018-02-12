import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {
    ...

    // Helper doubly-linked list class.
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // Construct an empty deque.
    public LinkedDeque() {
        ...
    }

    // Is the dequeue empty?
    public boolean isEmpty() {
        ...
    }

    // The number of items on the deque.
    public int size() {
        ...
    }

    // Add item to the front of the deque.
    public void addFirst(Item item) {
        ...
    }

    // Add item to the end of the deque.
    public void addLast(Item item) {
        ...
    }

    // Remove and return item from the front of the deque.
    public Item removeFirst() {
        ...
    }

    // Remove and return item from the end of the deque.
    public Item removeLast() {
        ...
    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<Item> iterator() {
        ...
    }
    
    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {
        ...
        
        DequeIterator() {
            ...
        }

        public boolean hasNext()  { ... }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            ...
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }
    
    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its " 
            + "several powers, having been originally breathed into a few " 
            + "forms or into one; and that, whilst this planet has gone " 
            + "cycling on according to the fixed law of gravity, from so " 
            + "simple a beginning endless forms most beautiful and most " 
            + "wonderful have been, and are being, evolved. ~ " 
            + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            }
            else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
