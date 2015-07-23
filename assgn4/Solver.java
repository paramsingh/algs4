/*
 * Author: Param Singh <paramsingh258@gmail.com>
 * Time: Friday, 24 July 2015 04:42:56 AM
 */
public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        int moves;
        Board cur;
        Board prev;

        public SearchNode(int moves, Board cur, Board prev) {
            this.moves = moves;
            this.cur   = cur;
            this.prev  = prev;
        }

        public int compareTo(SearchNode that) {
            return (this.cur.manhattan() - that.cur.manhattan())
                 + (this.moves - that.moves);
        }
    }

    public Solver(Board initial) {
        MinPQ<SearchNode> q = new MinPQ<SearchNode>();
        SearchNode first = new SearchNode(0, initial, null);
    }
}
