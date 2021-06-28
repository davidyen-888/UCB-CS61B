package bearmaps;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


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
        Point actual=kd.nearest(0,7);
        Point expected = new Point(1,5);
        assertEquals(expected, actual);
    }
}
