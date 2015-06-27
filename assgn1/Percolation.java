/*
 * Author: Param Singh
 * Date: 25/06/2015
 */
public class Percolation {

    private boolean[][] id;

    private WeightedQuickUnionUF qf;
    private WeightedQuickUnionUF nf;

    private int size;
    private int top;
    private int bottom;

    public Percolation(int N) {
        // N cannot be less than or equal to zero
        if (N <= 0) {
            throw new IllegalArgumentException("N should be greater than 0");
        }
        // quickfind structure for storing connections
        // contains n*n + 2 values because I am using
        // 2 extra spots to see if connected to the top
        // and bottom
        qf = new WeightedQuickUnionUF(N*N + 2);

        nf = new WeightedQuickUnionUF(N*N + 1);

        id = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                id[i][j] = false;
        }

        size = N;
        top = size * size;
        bottom = size * size + 1;

    }

    private void validate(int x, int y) {
        if (x < 1 || x > size || y < 1 || y > size)
            throw new IndexOutOfBoundsException();
    }

    private int convert(int i, int j) {
        return i*size + j;
    }

    public void open(int i, int j) {
        validate(i, j);

        int x = i-1;
        int y = j-1;
        id[x][y] = true;
        int current = convert(x, y);

        if (size == 1) {
            qf.union(current, top);
            nf.union(current, top);
            qf.union(current, bottom);
            return;
        }

        // union the cells above and below
        if (x == 0) {
            if (isOpen(x+2, y+1)) {
                qf.union(current, convert(x+1, y));
                nf.union(current, convert(x+1, y));
            }
            qf.union(current, top);
            nf.union(current, top);
        }
        else if (x == size-1) {
            if (isOpen(x, y+1)) {
                qf.union(current, convert(x-1, y));
                nf.union(current, convert(x-1, y));
            }
            qf.union(current, bottom);
        }
        else {
            if (isOpen(x, y+1)) {
                qf.union(current, convert(x-1, y));
                nf.union(current, convert(x-1, y));
            }
            if (isOpen(x+2, y+1)) {
                nf.union(current, convert(x+1, y));
                qf.union(current, convert(x+1, y));
            }
        }

        // union the cells left and right
        if (y != size-1 && y != 0) {
            if (isOpen(x+1, y)) {
                nf.union(current, convert(x, y-1));
                qf.union(current, convert(x, y-1));
            }
            if (isOpen(x+1, y+2)) {
                nf.union(current, convert(x, y+1));
                qf.union(current, convert(x, y+1));
            }
        }
        else if (y == size - 1) {
            if (isOpen(x+1, y)) {
                nf.union(current, convert(x, y-1));
                qf.union(current, convert(x, y-1));
            }
        }
        else if (y == 0) {
            if (isOpen(x+1, y+2)) {
                nf.union(current, convert(x, y+1));
                qf.union(current, convert(x, y+1));
            }
        }
    }

    public boolean isOpen(int i, int j) {
        validate(i, j);
        return id[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        validate(i, j);
        return nf.connected(convert(i-1, j-1), top);
    }

    public boolean percolates() {
        return qf.connected(top, bottom);
    }
}
