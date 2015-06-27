public class PercolationStats {

    private double mean;
    private double stddev;
    private double interval1, interval2;
    private double[] x;
    private int t;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();

        int i;

        x = new double[T];
        t = T;
        for (i = 0; i < T; i++) {
            double count = 0;
            Percolation p = new Percolation(N);
            while ( !p.percolates() ) {
                int x = StdRandom.uniform(N) + 1;
                int y = StdRandom.uniform(N) + 1;
                if ( p.isOpen(x, y) )
                    continue;
                else {
                    p.open(x, y);
                    count++;
                }
            }
            x[i] = count / (N*N);
        }
    }

    public double mean() {
        return StdStats.mean(x);
    }

    public double stddev() {
       return StdStats.stddev(x);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(t) );
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(t) );
    }

    public static void main( String[] args ) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, t);

        StdOut.printf("mean\t= %f\n", ps.mean());
        StdOut.printf("stddev\t= %f\n", ps.stddev());
        System.out.println("95% confidence interval\t= "+ps.confidenceLo()+", "+ps.confidenceHi());
    }
}
