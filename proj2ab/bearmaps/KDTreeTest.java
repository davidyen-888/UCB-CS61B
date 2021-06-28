package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;


public class KDTreeTest {
    /* @Source: https://docs.google.com/presentation/d/1WW56RnFa3g6UJEquuIBymMcu9k2nqLrOE1ZlnTYFebg/edit#slide=id.g54b6045b73_0_38 */
    private static KDTree buildLectureTree() {
        Point A = new Point(2, 3);
        Point B = new Point(4, 2);
        Point C = new Point(4, 5);
        Point D = new Point(3, 3);
        Point E = new Point(1, 5);
        Point F = new Point(4, 4);

        KDTree kd = new KDTree(List.of(A, B, C, D, E, F));
        return kd;
    }

    private static void buildTreeWithDoubles() {
        Point A = new Point(2, 3);
        Point B = new Point(2, 3);

        KDTree kd = new KDTree(List.of(A, B));
    }

    @Test
    /* @Source: https://docs.google.com/presentation/d/1DNunK22t-4OU_9c-OBgKkMAdly9aZQkWuv_tBkDg1G4/edit#slide=id.g54b6045cf5_150_2806 */
    public void testNearest() {
        KDTree kd = buildLectureTree();
        Point actual = kd.nearest(0, 7);
        Point expected = new Point(1, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void testNearestRandom() {
        Random r = new Random(500);
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            points.add(new Point(r.nextDouble(), r.nextDouble()));
        }
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kd = new KDTree(points);
        long time1 = 0;
        long time2 = 0;
        for (int i = 0; i < 200; i++) {
            double rX = r.nextDouble();
            double rY = r.nextDouble();
            long start1 = System.currentTimeMillis();
            Point pNPS = nps.nearest(rX, rY);
            long end1 = System.currentTimeMillis();
            time1 += end1 - start1;
            long start2 = System.currentTimeMillis();
            Point pKD = kd.nearest(rX, rY);
            long end2 = System.currentTimeMillis();
            time2 += end2 - start2;
            assertEquals(pNPS.getX(), pKD.getX(),0.01);
            assertEquals(pNPS.getY(), pKD.getY(),0.01);
        }
        System.out.println("Total time elapsed of NaivePointSet: " + time1 / 1000.0 + " seconds.");
        System.out.println("Total time elapsed of KDTree: " + time2 / 1000.0 + " seconds.");
    }
}
