public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 0; i < k; i++){
            String s = StdIn.readString();
            rq.enqueue(s);
        }

        for(int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}
