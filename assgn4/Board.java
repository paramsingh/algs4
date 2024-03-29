/*
 * Author: Param Singh <paramsingh258@gmail.com>
 * Time: Friday, 24 July 2015 03:13:03 AM
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private int[][] board;
    private int len;

    private int[][] blocks() {
        int[][] copy = new int[len][len];
        int i, j;
        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    public Board(int[][] blocks) {
        len = blocks.length;
        int cur = 1;
        int i, j;
        board = new int[len][len];
        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return len;
    }

    public int hamming() {
        int score = 0;
        int i, j;

        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                if (board[i][j] == 0)
                    continue;
                int x = (board[i][j] - 1) / len;
                int y = (board[i][j] - 1) % len;
                if ( x != i || y != j)
                    score++;
            }
        }

        return score;
    }

    public int manhattan() {
        int score = 0;
        int i, j;

        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                if (board[i][j] == 0) continue;
                int x = (board[i][j] - 1) / len;
                int y = (board[i][j] - 1) % len;
                score += Math.abs(i - x) + Math.abs(j - y);
            }
        }
        return score;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        int i, j;
        int[][] copy = this.blocks();
        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                if (j != len - 1 && copy[i][j] != 0 && copy[i][j+1] != 0) {
                    int tmp = copy[i][j];
                    copy[i][j] = copy[i][j+1];
                    copy[i][j+1] = tmp;
                    return new Board(copy);
                }
            }
        }

        // control will never reach here
        return null;
    }

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        if (that.dimension() != len) return false;
        int i;
        for (i = 0; i < len; i++) {
            if (!Arrays.equals(this.board[i], that.board[i]))
                return false;
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        List<Board> boards = new ArrayList<Board>();
        int i, j;
        int x, y; // position of zero
        x = y = -1;
        outer: for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                if (board[i][j] == 0) {
                    x = i;
                    y = j;
                    break outer;
                }
            }
        }

        if (y + 1 != len) {
            int[][] copy = this.blocks();
            int tmp = copy[x][y+1];
            copy[x][y+1] = copy[x][y];
            copy[x][y] = tmp;
            boards.add(new Board(copy));
        }

        if (y - 1 != -1) {
            int[][] copy = this.blocks();
            int tmp = copy[x][y-1];
            copy[x][y-1] = copy[x][y];
            copy[x][y] = tmp;
            boards.add(new Board(copy));
        }

        if (x + 1 != len) {
            int[][] copy = this.blocks();
            int tmp = copy[x+1][y];
            copy[x+1][y] = copy[x][y];
            copy[x][y] = tmp;
            boards.add(new Board(copy));
        }

        if (x - 1 != -1) {
            int[][] copy = this.blocks();
            int tmp = copy[x-1][y];
            copy[x-1][y] = copy[x][y];
            copy[x][y] = tmp;
            boards.add(new Board(copy));
        }

        return boards;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(len + "\n");
        int i, j;
        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        int[][] goal   = new int[n][n];
        int cur = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
                goal[i][j] = cur++;
            }
        }
        goal[n-1][n-1] = 0;
        Board b = new Board(blocks);
        Board g = new Board(goal);

        // testing toString()
        StdOut.println(b);
        StdOut.println(g);

        // testing neighbors()
        for(Board board: b.neighbors())
            StdOut.println(board);

        // testing equals()
        Board b2 = new Board(blocks);
        System.out.println(b.equals(b2));
        System.out.println(b.equals(g));

        // testing twin()
        StdOut.println(b.twin());

        // testing manhattan()
        StdOut.println(b.manhattan());
        StdOut.println(g.manhattan());

        // testing hamming()
        StdOut.println("Hamming: "+b.hamming());

        // testing isGoal()
        StdOut.println("B is goal: " + b.isGoal());
        StdOut.println("goal is goal: " + g.isGoal());
    }
}

