/*
 * Name: Param Singh <paramsingh258@gmail.com>
 * Date: 09/07/2015
 */
import java.util.Comparator;

public class Point implements Comparable<Point> {
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        if (this.y < that.y || (this.y == that.y && this.x < that.x))
            return -1;
        else if (this.y == that.y && this.x == that.x)
            return 0;
        else
            return 1;
    }

    public double slopeTo(Point that) {
        // Check if both points are the same
        if (this.compareTo(that) == 0)
            return Double.NEGATIVE_INFINITY;

        // Check if the line is vertical
        if (this.x == that.x)
            return Double.POSITIVE_INFINITY;

        // Check if line is horizontal
        if (this.y == that.y)
            return 0.0;

        double slope = ((double) (this.y - that.y)) / (this.x - that.x);
        return slope;
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point one, Point two) {
            double slope1 = Point.this.slopeTo(one);
            double slope2 = Point.this.slopeTo(two);
            if (slope1 < slope2)
                return -1;
            else if (slope1 > slope2)
                return 1;
            else
                return 0;
        }
    }

    public static void main(String[] args) {
        Point a = new Point(6000, 4000);
        Point b = new Point(0, 1000);
        System.out.println(a.slopeTo(b));
    }
}
