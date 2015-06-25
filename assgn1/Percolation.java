public class Percolation {
    // boolean grid for all sites
    private boolean id[][];
    private WeightedQuickUnionUF qf;
    private int size;
    private int top;
    private int bottom;

    public Percolation(int N) throws IllegalArgumentException {
        // N cannot be less than or equal to zero
        if(N <= 0){
            throw new IllegalArgumentException("N should be greater than 0");
        }

        id = new boolean[N][N];

        // quickfind structure for storing connections
        // contains n*n + 2 values because I am using
        // 2 extra spots to see if connected to the top
        // and bottom
        qf = new WeightedQuickUnionUF(N*N + 2);
        
        size = N;
        top = size * size;
        bottom = size * size;

        int i, j;
        for(i = 0; i < N; i++)
            for(j = 0; j < N; j++)
                // All sites are blocked by default
                id[i][j] = false;
    }

    private void validate(int x, int y) throws IndexOutOfBoundsException {
        if(x <= 0 || y > size)
            throw new IndexOutOfBoundsException();
    }

    private int convert(int i, int j){
        return i*size + j;
    }

    public void open(int i, int j) {
        validate(i, j);
        i = i-1;
        j = j-1;
        id[i-1][j-1] = true;
        int current = convert(i, j);

        // union the cells above and below
        if(i==0) {
            if(isOpen(i+2, j+1))
                qf.union(current, convert(i+1, j));
            qf.union(current, top);
        }
        else if(i==size-1) {
            if(isOpen(i, j+1))            
                qf.union(current, convert(i-1, j));
            qf.union(current, bottom);
        }
        else {
            if(isOpen(i, j+1)) 
                qf.union(current, convert(i-1, j));
            if(isOpen(i+2, j+1))
                qf.union(current, convert(i+1, j));
        }

        // union the cells left and right
        if(j != size-1 && j != 0) {
            if(isOpen(i+1, j))
                qf.union(current, convert(i, j-1));
            if(isOpen(i+1, j+2))
                qf.union(current, convert(i, j+1));
        }
        else if(j == size - 1) {
            if(isOpen(i+1, j))
                qf.union(current, convert(i, j-1));
        }
        else if(j == 0) {
            if(isOpen(i+1, j+2))
                qf.union(current, convert(i, j+1));
        }
    }

    public boolean isOpen(int i, int j) {
        validate(i, j);
        return id[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        validate(i, j);
        return qf.connected(convert(i-1, j-1), top); 
    }

    public boolean percolates() {
        return qf.connected(top, bottom);
    }

    public static void main(String[] args) {
        System.out.println("Enter the size of the grid");
        int size = StdIn.readInt();
        Percolation p = new Percolation(size);
        while(true) {
            System.out.println("Enter a cell to open");
            int i = StdIn.readInt();
            int j = StdIn.readInt();
            p.open(i, j);
            if(p.percolates())
                System.out.println("Percolates");
            else
                System.out.println("Does not percolate");
        }
    }
}
