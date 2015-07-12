/*
 * Author:  Param Singh <paramsingh258@gmail.com>
 * Date:    10/07/2015
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

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

        HashMap<String, Boolean> printed = new HashMap<String, Boolean>();

        for (int i = 0; i < numPoints; i++) {
            Arrays.sort(aux, list[i].SLOPE_ORDER);
            int consecutive = 1;
            List<Point> line = new ArrayList<Point>(numPoints);
            line.add(list[i]);
            for (int j = 2; j < numPoints; j++) {
                if (list[i].slopeTo(aux[j]) == list[i].slopeTo(aux[j-1])) {
                    consecutive++;
                    line.add(aux[j-1]);
                }
                else {
                    if1: if (consecutive >= 3) {
                        line.add(aux[j-1]);
                        Collections.sort(line);
                        String l = new String();
                        for (Point a: line)
                            l = l + a.toString() + " -> ";
                        if (printed.containsKey(l))
                            break if1;
                        System.out.print(list[i].toString() + " -> ");
                        for(int k = j-consecutive; k < j-1; k++)
                            System.out.print(aux[k].toString() + " -> ");
                        System.out.println(aux[j-1].toString());
                        printed.put(l, true);
                    }
                    consecutive = 1;
                    line = new ArrayList<Point>(numPoints);
                    line.add(list[i]);
                }
            }
            // if the consecutive list falls through then have to print one
            // last time
            if2: if (consecutive >= 3) {
                line.add(aux[numPoints-1]);
                Collections.sort(line);
                String l = new String();
                for (Point a: line)
                    l += a.toString() + " -> ";
                if (printed.containsKey(l))
                    break if2;
                System.out.print(list[i].toString() + " -> ");
                for (int k = numPoints-consecutive; k < numPoints-1; k++)
                    System.out.print(aux[k].toString() + " -> ");
                System.out.println(aux[numPoints-1].toString());
                printed.put(l, true);
            }
        }
    }
}

