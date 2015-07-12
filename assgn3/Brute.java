/*
 * Author:  Param Singh <paramsingh258@gmail.com>
 * Date:    10/07/2015
 */
import java.util.Arrays;
public class Brute {
    public static void main(String[] args) {
        In in = new In(args[0]);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        int numPoints = in.readInt();
        Point[] list = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            int x = in.readInt();
            int y = in.readInt();
            list[i] = new Point(x, y);
            list[i].draw();
        }

        Arrays.sort(list);
        for (int i = 0; i < numPoints; i++) {
            for (int j = i+1; j < numPoints; j++) {
                for (int k = j+1; k < numPoints; k++) {
                    for (int l = k+1; l < numPoints; l++) {
                        double slope1 = list[i].slopeTo(list[j]);
                        double slope2 = list[i].slopeTo(list[k]);
                        double slope3 = list[i].slopeTo(list[l]);
                        if (slope1 == slope2 && slope2 == slope3) {
                            System.out.println(list[i].toString() + " -> "
                                             + list[j].toString() + " -> "
                                             + list[k].toString() + " -> "
                                             + list[l].toString());
                            list[i].drawTo(list[l]);
                        }
                    }
                }
            }
        }
    }
}



