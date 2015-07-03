public class TestDeque {
    private Deque<Integer> d;

    public TestDeque() {
        d = new Deque<Integer>();
    }

    public void addAndRemove() {
        int i;
        int[] a = new int[10];
        for (i = 0; i < 10; i++) {
            int x = StdRandom.uniform(0, 100);
            a[i] = x;
            d.addLast(x);
        }

        assert (d.size() == 10);
        for (i = 0; i < 10; i++) {
            int x = d.removeLast();
            assert x == a[10-i-1];
        }

        for (i = 0; i < 10; i++) {
            int x = StdRandom.uniform(0, 100);
            a[i] = x;
            d.addFirst(x);
        }

        for (i = 0; i < 10; i++) {
            int x = d.removeFirst();
            assert x == a[10-i-1];
        }

        for (i = 0; i < 10; i++) {
            int x = StdRandom.uniform(0, 100);
            a[i] = x;
            d.addLast(x);
        }

        for (i = 0; i < 10; i++) {
            int x = d.removeFirst();
            assert x == a[i];
        }

        for (i = 0; i < 10; i++) {
            int x = StdRandom.uniform(0, 100);
            a[i] = x;
            d.addFirst(x);
        }

        for (i = 0; i < 10; i++) {
            int x = d.removeLast();
            assert x == a[i];
        }

        assert d.size() == 0;
    }

    public void iteration() {
        int i;
        int[] a = new int[10];

        for (i = 0; i < 10; i++) {
            int x = StdRandom.uniform(0, 100);
            a[i] = x;
            d.addLast(x);
        }


        i = 0;
        for (int x: d)
            assert x == a[i++];
    }

    public static void main(String[] args) {
        TestDeque td = new TestDeque();
        StdOut.println("Testing addition and removal...");
        td.addAndRemove();
        StdOut.println("Testing iteration...");
        td.iteration();
    }
}
