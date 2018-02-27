import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Random queue implementation using a resizing array.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private Item[] tempQue; 
    private int N;

    
    // Construct an empty queue.
    public ResizingArrayRandomQueue() {
        this.tempQue = (Item[]) new Object[1];
       this.N = 0;
    }

    // Is the queue empty?
    public boolean isEmpty() {
        return this.N == 0;
    }

    // The number of items on the queue.
    public int size() {
        return this.N;
    }

    // Add item to the queue.
    public void enqueue(Item item) {
        if (item == null) { throw new NullPointerException(); }
        if (this.N == tempQue.length) { resize(2*tempQue.length); }
        this.tempQue[this.N++] = item;
    }

    // Remove and return a random item from the queue.
    public Item dequeue() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        int rb = StdRandom.uniform(this.N);
        Item item = this.tempQue[rb];
        if (rb != this.N-1) { this.tempQue[rb] = this.tempQue[this.N-1]; }
        this.tempQue[this.N-1] = null;
        this.N--;
        if (this.N > 0 && this.N == this.tempQue.length/4) 
        { resize(this.tempQue.length/2); }
        return item;
    }

    // Return a random item from the queue, but do not remove it.
    public Item sample() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        int rb = StdRandom.uniform(this.N);
        return this.tempQue[rb];
    }

    // An independent iterator over items in the queue in random order.
    public Iterator<Item> iterator() {
         return new RandomQueueIterator();
    }
    
    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] items = (Item[]) new Object[tempQue.length];
        private int current = N;
        
        RandomQueueIterator() {
             for (int i = 0; i < tempQue.length; i++) {
                items[i] = tempQue[i];
            }
        }
        
        public boolean hasNext()  {  return current != 0; }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int rb = StdRandom.uniform(current);
            Item item = items[rb];
            if (rb != current-1) {
                items[rb] = items[current-1];
            }
            items[current-1] = null;
            current--;
            return item;
        }
    }

    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Helper method for resizing the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            if (tempQue[i] != null) {
                temp[i] = tempQue[i];
            }
        }
        tempQue = temp;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = 
            new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}
