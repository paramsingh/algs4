/*
 * Author:  Param Singh
 * Date:    03/07/2015
 */
import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {
       first = null;
       last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {

        if (item == null)
            throw new NullPointerException("Can't add null item");

        Node x = new Node();
        x.next = first;
        x.item = item;
        if (first == null)
            last = x;
        else
            first.prev = x;
        first = x;

        size += 1;
    }

    public void addLast(Item item) {

        if (item == null)
            throw new NullPointerException("Can't add null item");

        Node x = new Node();
        x.item = item;
        if (last == null)
            first = x;
        else
            last.next = x;
        x.prev = last;
        last = x;

        size += 1;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Can't remove from an empty deque");

        Item val = first.item;

        if (first == last) {
            first.item = null;
            first.next = null;
            first.prev = null;
            first = null;
            last = null;
        }
        else {
            first.item = null;
            Node nxt = first.next;
            first.next = null;
            nxt.prev = null;
            first = nxt;
        }

        size -= 1;

        return val;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Can't remove from an empty deque");

        Item val = last.item;

        if (first == last) {
            first.item = null;
            first.next = null;
            first.prev = null;
            first = null;
            last = null;
        }
        else {
            last.item = null;
            Node prv = last.prev;
            last.prev = null;
            prv.next = null;
            last = prv;
        }

        size -= 1;

        return val;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node cur;
        public DequeIterator() {
            cur = first;
        }

        public boolean hasNext() {
            return cur != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item val = cur.item;
            cur = cur.next;
            return val;
        }
    }
}
