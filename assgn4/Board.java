/*
 * Author: Param Singh <paramsingh258@gmail.com>
 * Time: Friday, 24 July 2015 03:13:03 AM
 */
import java.util.Stack;

public class Board {
    private int[][] board;
    private int len;
    private int[][] goal;

    public Board(int[][] blocks) {
        len = blocks.length;
        int cur = 1;
        int i, j;
        board = new int[len][len];
        goal = new int[len][len];
        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                board[i][j] = blocks[i][j];
                goal[i][j]  = cur++;
            }
        }
        goal[len-1][len-1] = 0;
    }

    public int dimension() {
        return len;
    }

    public int hamming() {
        int cur = 1;
        int score = 0;
        int i, j;
        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                if (board[i][j] != cur)
                    score++;
                cur++;
            }
        }
        return score;
    }

    public int manhattan() {
        int score = 0;
        int i, j;

        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                int x = board[i][j] / len;
                int y = board[i][j] % len;

                score += (i + j) - (x + y);
            }
        }

        return score;
    }

    public boolean isGoal() {
        int cur = 1;
        int i, j;
        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                if (board[i][j] != cur)
                    return false;
                cur++;
            }
        }
        return true;
    }

    public Board twin() {
        int i, j;
        int[][] copy = new int[len][len];
        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                copy[i][j] = board[i][j];
            }
        }

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
    }

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        if (that.dimension() != len) return false;

        return this.toString().equals(that.toString());
    }
}
