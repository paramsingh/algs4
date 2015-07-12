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
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        int numPoints = in.readInt();
        Point[] list = new Point[numPoints];
        Point[] aux = new Point[numPoints];

        for (int i = 0; i < numPoints; i++) {
            int x = in.readInt();
            int y = in.readInt();
            list[i] = new Point(x, y);
            list[i].draw();
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
                        for (int k = 0; k < line.size()-1; k++)
                            l = l + line.get(k).toString() + " -> ";
                        l = l + line.get(line.size()-1);
                        if (printed.containsKey(l))
                            break if1;
                        line.get(0).drawTo(line.get(line.size()-1));
                        System.out.println(l);
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
                for(int k = 0; k < line.size() - 1; k++)
                    l += line.get(k).toString() + " -> ";
                l += line.get(line.size() - 1);
                if (printed.containsKey(l))
                    break if2;
                line.get(0).drawTo(line.get(line.size()-1));
                System.out.println(l);
                printed.put(l, true);
            }
        }
    }
}

