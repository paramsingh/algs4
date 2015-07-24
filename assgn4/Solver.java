/*
 * Author: Param Singh <paramsingh258@gmail.com>
 * Time: Friday, 24 July 2015 04:42:56 AM
 */
import java.util.List;
import java.util.ArrayList;

public class Solver {
    private SearchNode sol;
    private boolean solvable;
    private int moves;

    private class SearchNode implements Comparable<SearchNode> {
        int moves;
        Board board;
        SearchNode prev;

        public SearchNode(int moves, Board cur, SearchNode prev) {
            this.moves = moves;
            this.board = cur;
            this.prev  = prev;
        }

        public int compareTo(SearchNode that) {
            return (this.board.manhattan() - that.board.manhattan())
                 + (this.moves - that.moves);
        }
    }

    public Solver(Board initial) {
        if (initial == null)
            throw new NullPointerException();

        MinPQ<SearchNode> q = new MinPQ<SearchNode>();
        SearchNode cur = new SearchNode(0, initial, null);
        q.insert(cur);

        MinPQ<SearchNode> nq = new MinPQ<SearchNode>();
        SearchNode cur2 = new SearchNode(0, initial.twin(), null);
        nq.insert(cur2);

        SearchNode solution = null;
        while (true) {
            SearchNode min  = q.delMin();
            SearchNode min2 = nq.delMin();

            if (min.board.isGoal()) {
                solvable = true;
                solution = min;
                break;
            }

            if (min2.board.isGoal()) {
                solvable = false;
                solution = min2;
                break;
            }

            for (Board neighbor: min.board.neighbors()) {
                if (min.prev == null || !neighbor.equals(min.prev.board)) {
                    cur = new SearchNode(min.moves + 1, neighbor, min);
                    q.insert(cur);
                }
            }

            for (Board neighbor: min2.board.neighbors()) {
                if (min.prev == null || !neighbor.equals(min2.prev.board)) {
                    cur2 = new SearchNode(min2.moves + 1, neighbor, min);
                    nq.insert(cur2);
                }
            }
        }

        if (solvable) {
            this.moves = solution.moves;
            this.sol = solution;
        }
        else {
            this.moves = -1;
            this.sol = null;
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        List<Board> list = new ArrayList<Board>();
        SearchNode cur = this.sol;
        while (cur.prev != null) {
            list.add(0, cur.board);
            cur = cur.prev;
        }

        return list;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board: solver.solution())
                StdOut.println(board);
        }
    }
}
