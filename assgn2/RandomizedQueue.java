import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;
    private int capacity;


    public RandomizedQueue() {
        size = 0;
        queue = null;
        capacity = 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("Can't add null to queue");

        if (size == capacity)
            doubleSize();

        queue[size++] = item;
    }

    public Item dequeue() {
        if(isEmpty())
            throw new NoSuchElementException("Empty queue");

        int index = StdRandom.uniform(size);
        Item val = queue[index];
        queue[index] = queue[size-1];
        queue[size] = null;
        size--;

        if (size < (capacity / 4))
            halveSize();

        return val;
    }

    private void doubleSize() {
        capacity *= 2;

        Item[] qnew = (Item[]) new Object[capacity];
        int i;
        for (i = 0; i < size; i++) {
            qnew[i] = queue[i];
        }
        queue = qnew;
    }

    private void halveSize() {
        capacity /= 2;

        Item[] qnew = (Item[]) new Object[capacity];
        int i;
        for (i = 0; i < size; i++) {
            qnew[i] = queue[i];
        }

        queue = qnew;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Empty queue");

        int index = StdRandom.uniform(size);
        return queue[index];
    }

    public Iterator<Item> iterator() {
        return new RQIterator();
    }

    private class RQIterator implements Iterator<Item> {
        private int i;
        public RQIterator() {
            i = 0;
            StdRandom.shuffle(queue, 0, size-1);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return i == size;
        }

        public Item next() {
            Item val = queue[i];
            i++;
            return val;
        }
    }
}
