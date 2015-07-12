/*
 * Author:  Param Singh <paramsingh258@gmail.com>
 * Date:    10/07/2015
 */
import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int numPoints = in.readInt();
        Point[] list = new Point[numPoints];
        Point[] aux = new Point[numPoints];

        for (int i = 0; i < numPoints; i++) {
            int x = in.readInt();
            int y = in.readInt();
            list[i] = new Point(x, y);
            aux[i] = new Point(x, y);
        }

        for (int i = 0; i < numPoints; i++) {
            Arrays.sort(aux, list[i].SLOPE_ORDER);
            int consecutive = 1;
            for (int j = 2; j < numPoints; j++) {
                if (list[i].slopeTo(aux[j]) == list[i].slopeTo(aux[j-1]))
                    consecutive++;
                else {
                    if (consecutive >= 3) {
                        System.out.print(list[i].toString() + " -> ");
                        for (int k = j - consecutive; k < j-1; k++)
                            System.out.print(aux[k].toString() + " -> ");
                        System.out.println(aux[j-1].toString());
                    }
                    consecutive = 1;
                }
            }
            // if the consecutive list falls through then have to print one
            // last time
            if (consecutive >= 3) {
                System.out.print(list[i].toString() + " -> ");
                for (int k = numPoints-consecutive; k < numPoints-1; k++)
                    System.out.print(aux[k].toString() + " -> ");
                System.out.println(aux[numPoints-1].toString());
            }
        }
    }
}

